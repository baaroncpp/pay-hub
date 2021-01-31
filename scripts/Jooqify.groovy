#!/usr/bin/env groovy
@groovy.lang.Grab('com.github.jsqlparser:jsqlparser:3.1')

/**
 * Taken from Ronald Kayando @awamo
 * **/
import groovy.cli.commons.CliBuilder
import net.sf.jsqlparser.parser.CCJSqlParserUtil
import net.sf.jsqlparser.statement.select.PlainSelect
import net.sf.jsqlparser.statement.select.Select
import net.sf.jsqlparser.statement.select.SelectExpressionItem
import net.sf.jsqlparser.statement.select.SelectItem
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection

class Jooqify {

    def query = '''
                    SELECT
                        td.id AS id_bigint,
                        tr.id AS 'request.id_bigint',
                        maw.id AS 'wallet.awamoWalletId_bigint',
                        mtw.id AS 'wallet.walletId_bigint',
                        mtw.account_no AS accountNumber_string,
                        td.amount AS amount_decimal,
                        tr.amount AS 'request.amount_decimal',
                        tr.tx_type  AS 'request.type_string',
                        td.tx_type  AS 'detail.type_string',
                        tr.tx_status  AS status
                    FROM
                        m_awamo_wallet maw
                    INNER JOIN m_tenant_wallet mtw ON
                        mtw.id = maw.wallet_id
                    INNER JOIN w_wallet_transaction_request tr ON
                        tr.wallet_id = mtw.id
                    INNER JOIN m_wallet_transaction_detail td ON
                        td.wallet_tx_request_id = tr.id
                    '''.stripIndent()

    String[] args

    def i = 0
    def k = 0

    static main(args) {
        new Jooqify(args: args).go()
    }

    def go() {
        def CLI = new CliBuilder().tap {
            c(longOpt: 'clipboard', "Copy From Clipboad")
            n(longOpt: 'classname', args: 1, "Specify Class Name")
            help("Help")
        }


        def options = CLI.parse(args)
        def ARG_HELP = options.help
        if (ARG_HELP) {
            CLI.usage(); return
        }

        def ARG_CLIPBOARD = options.c
        def ARG_CLASSNAME = options.n ?: 'JooqQuery'

        def queryToProcess = query
        if (ARG_CLIPBOARD) {
            queryToProcess = fromClipboard()
            query = queryToProcess
        }

        def s = CCJSqlParserUtil.parse(queryToProcess) as Select

        def plainSelect = s.selectBody as PlainSelect

        def fields = parseFields(plainSelect)

        def nestedClasses = createDataClasses(fields)

        def codePrinter = generateCode(ARG_CLASSNAME, fields, nestedClasses, plainSelect)

        def string = codePrinter.asString()

        println(string)
        if (ARG_CLIPBOARD) {
            copyToClipboard(string)
            sleep(25_000)
        } else {
            new File(ARG_CLASSNAME + '.java').text = string
        }

    }


    private static List<SqlField> parseFields(PlainSelect body) {
        return body.selectItems.collect { parseSqlField(it) }
    }

    private static SqlField parseSqlField(SelectItem it) {
        def sei = it as SelectExpressionItem
        def stringExpr = sei.expression.toString()
        def rawAlias = sei.alias.name.replaceAll(/^'|'$/, "")


        //println("Alias [$alias] Expression: [$stringExpr] #${sei.expression.getClass().simpleName}")
        def field = new SqlField(alias: rawAlias, fieldName: rawAlias, expr: stringExpr, javaType: 'Object', comparator: null, constant: constantName(rawAlias))


        if (rawAlias.contains('_')) {

            def split = rawAlias.split('_')
            def partsSize = split.size()

            //noinspection GroovyFallthrough
            switch (partsSize) {
                case { it >= 3 }:
                    field.comparator = split[2].toUpperCase()
                default:
                    field.alias = split[0]
                    field.fieldName = split[0]
                    field.constant = constantName(field.alias)
                    field.javaType = resolveType(split[1])
            }
        }


        if (field.alias.contains('.')) {
            field.with {
                (className, fieldName) = alias.split(/\./)
                className = className.capitalize()
            }
        }

        return field
    }

    static List<NestedClass> createDataClasses(List<SqlField> fields) {

        def classNameByFields = fields.groupBy {
            it.isNested() ? it.className : 'NONE'
        }

        return classNameByFields
                .keySet()
                .findAll { it != 'NONE' }
                .collect { new NestedClass(name: it, fields: classNameByFields[it]) }

    }

    static String constantName(String s) {
        'FIELD_' + toSnakeCase(s)
    }

    static String toSnakeCase(String s) {
        s.replaceAll(/([a-z])([A-Z]+)/, '$1_$2')
                .toUpperCase()
                .replaceAll(/([A-Z])\.([A-Z])/, '$1_$2')

    }


    static void copyToClipboard(final String contents) {
        try {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection(contents), null)
            println "Copied to clipboard"
        } catch (x) {
            println "Failed to copy to clipboard: $x.message"
        }
    }

    static String fromClipboard() {
        try {
            println "================================="
            println "   READING DATA FROM CLIPBOARD"
            println "=================================="
            def data = Toolkit.defaultToolkit
                    .systemClipboard
                    .getData(DataFlavor.stringFlavor)
            println "Clipboard:\n $data"
            return data
        } catch (x) {
            println "Failed to copy to clipboard: $x.message"
            throw x
        }
    }

    static String resolveType(typeHint) {
        def lowCaseHint = typeHint.toString().toLowerCase()
        return [int    : 'Integer',
                long   : 'Long',
                decimal: 'BigDecimal',
                bigint : 'BigInteger',
                string : 'String',
                date   : 'Date',
                time   : 'Timestamp',
                boolean: 'Boolean',
                object : 'Object'].get(lowCaseHint)
    }

    def String fieldsCodeBlock(fields){
        i = fields.size()
        k = 0
        fields.each { f ->

            writeln("""builder.append("" ${f.fullFieldName()}""")

            if(k !=i ){
                writeln("""builder.append(",")""")
            }

            k++

        }
    }

    IndentHelper generateCode(className, List<SqlField> fields, List<NestedClass> classes, body) {
        IndentHelper p = new IndentHelper()

        p.with {

            writeln("""
            //region Begin Code
            /*
            This file has been generated by Jooqify.groovy [${new Date()}]
            */
             import com.fasterxml.jackson.annotation.JsonInclude;
            import com.jajjamind.payvault.core.jpa.models.RecordList;
            import com.jajjamind.payvault.core.repository.JooqFilter;
            import com.jajjamind.payvault.core.repository.QueryResultPicker;
            import groovy.lang.Tuple2;
            import org.jooq.*;
            import org.jooq.impl.DSL;
            import org.springframework.beans.factory.annotation.Autowired;
            import org.springframework.stereotype.Service;
            import org.springframework.util.MultiValueMap;
            
            import java.util.List;
            
            import static com.jajjamind.payvault.core.repository.DbField.dbField;

           """.stripIndent())

            //sql in comment block
            writeln('/*')
            writeln(query.trim())
            writeln '*/'

            //class declaration
            writeln("@Service")
            codeBlock("public class $className implements QueryResultPicker") {

                br()
                writeln("@Autowired")
                writeln("public DSLContext context;")
                br()
                //static fields
                fields.each {
                    writeln("""public static final String ${it.constant} = "${it.alias}";""")
                }
                br()


                //java fields
                fields.each { f ->

                    writeln("""private final Field<${f.javaType}> ${f.fullFieldName()} = DSL.field("${f.expr}", ${f.javaType}.class);""")
                }


                br()

                //core table field
                writeln("""private final Table<?> coreTable = DSL.table("${body.fromItem.toString()}\\n" """) {
                    body.joins.each {
                        writeln(""" + "${it.toString()}\\n" """)
                    }
                    writeln(');')
                }
                br()

                writeln("""private JooqFilter jooqFilter;""")
                br()


                //constructor
                codeBlock("public $className()") {
                    writeln('initFilterer();')
                }


                //query method
                codeBlock('public Select<Record> query(MultiValueMap params)') {

                    writeln('final Tuple2<Integer, Integer> limitAndOffset = jooqFilter.paginationParams(params);')


                    writeln('return DSL.select(jooqFilter.selectFields(params))')
                    writeln('.from(coreTable)')
                    writeln('.where(') {
                        writeln('jooqFilter.whereClause(params))')
                    }
                    writeln('.orderBy(jooqFilter.sortFields(params))')
                    writeln('.limit(limitAndOffset.getFirst(), limitAndOffset.getSecond());')
                }


                //init jooq filter method
                codeBlock('private void initFilterer()') {
                    writeln('jooqFilter = new JooqFilter()') {
                        writeln('.fields(') {
                            write(
                                    fields.collect { f ->
                                        if (f.comparator) {
                                            """${currentIndent()}dbField(${f.fullFieldName()}, $f.constant, ${f.comparator})"""
                                        } else {
                                            """${currentIndent()}dbField(${f.fullFieldName()}, $f.constant)"""
                                        }
                                    }.join(",\n")
                            )
                            br()
                        }
                        writeln(');')
                    }
                }


                def lombokAnnotations = {
                    writeln('@lombok.Data')
                    writeln('@lombok.NoArgsConstructor')
                    writeln('@JsonInclude(JsonInclude.Include.NON_NULL)')
                }

                def renderField = { SqlField f ->
                    if (f.javaType == 'Timestamp') {
                        writeln '@RenderInTimeZone'
                    }
                    writeln("private ${f.javaType} $f.fieldName;")
                }

                lombokAnnotations()
                codeBlock('public static class Result') {
                    fields.findAll { !it.isNested() }.each renderField

                    //nested class fields
                    br()
                    classes.each {
                        writeln("private $it.name ${it.name.uncapitalize()} = new $it.name();")
                    }


                    // the nested classed
                    br()
                    classes.each { c ->
                        lombokAnnotations()
                        codeBlock("public static class ${c.name}") {
                            c.fields.each renderField
                        }
                    }

                }

                codeBlock('public RecordList<Result> listAndCount(MultiValueMap<String,?> map)') {
                    writeln 'return new RecordList<Result>(count(map),list(map));'
                }

                codeBlock('public List<Result> list(MultiValueMap<String,?> map)') {
                    writeln "return context.fetch(this.query(map)).into(Result.class);"
                }

                codeBlock('public Long count(MultiValueMap<String,?> map)') {
                    writeln "return context.fetchOne(this.query(addCountParams(map))).getValue(0,Long.class);"
                }

                codeBlock('public String getAllFields()'){
                    writeln "final StringBuilder builder = new StringBuilder();"
                    i = fields.size()
                    k = 0
                    fields.each { f ->

                        writeln("""builder.append($f.constant);""")
                        k++
                        if(k !=i ){
                            writeln("""builder.append(",");""")
                        }else{
                            writeln "return builder.toString();"
                        }

                    }

                }

            }
            writeln('//endregion')
        }

        p
    }


    static class IndentHelper {
        public static final String INDENT = '    '
        def w = new StringWriter()
        def ip = new IndentPrinter(w, INDENT, true, true)

        def indent(@DelegatesTo(IndentHelper) Closure c) {
            ip.incrementIndent()
            c.delegate = this
            c.call(this)
            ip.decrementIndent()
        }

        def write(String str, @DelegatesTo(IndentHelper) Closure c = Closure.IDENTITY) {
            ip.print(str)
            indent c
        }

        def writeln(String str, @DelegatesTo(IndentHelper) Closure c = Closure.IDENTITY) {
            ip.println(str)
            indent c
        }

        def codeBlock(String str, @DelegatesTo(IndentHelper) Closure c = Closure.IDENTITY) {
            ip.println("${str}{")
            indent c
            ip.println('}')
            br()
        }


        String currentIndent() {
            INDENT * ip.indentLevel
        }

        def br() {
            write('\n')
        }

        String asString() {
            w.toString()
        }

    }

    static class NestedClass {
        String name
        List<SqlField> fields = []
    }


    static class SqlField {
        String alias
        String expr
        String javaType
        String comparator
        String constant
        String className
        String fieldName


        def isNested() {
            return className != null
        }

        def fullFieldName() {
            return className ?
                    "${className.uncapitalize()}${fieldName.capitalize()}"
                    : fieldName
        }
    }

}


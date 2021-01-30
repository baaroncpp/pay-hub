package com.jajjamind.payvault.core.repository

import com.jajjamind.commons.time.DateTimeUtil;
import groovy.transform.PackageScope
import org.apache.commons.lang3.StringUtils
import org.jooq.*
import org.jooq.impl.DSL
import org.jooq.impl.SQLDataType
import org.springframework.util.MultiValueMap

/*
Taken from Ronald Kayondo @awamo
 */
class JooqFilter {

    public static final String FILTER_OVERRIDE_PREFIX = '_ftype_'
    Map<String, DbField> fieldMap = [:]
    final List<String> RESERVED_PROPERTIES = ['limit', 'offset', 'requiredColumns', 'showList', 'sortBy', 'sortOrder']
    Map<String, List<String>> followerMap = [:]

    JooqFilter fields(DbField... fields) {
        fields.each {
            if (fieldMap.containsKey(it.name)) throw new IllegalArgumentException("Duplicate Field[$it.name] Found")
            fieldMap[it.name] = it
        }
        return this
    }


    List<Field<?>> selectFields(MultiValueMap map) {

        if (!isShowList(map)) {
            return [DSL.count()]
        }

        def names = map.getFirst('requiredColumns')?.toString()?.trim()

        def fieldNames = names ? StringUtils
                .splitPreserveAllTokens(names.toString(), ',')
                .collect { it.trim() } : fieldMap.keySet()

        return fieldNames.collect {
            def thisField = getFieldByName(it).aliasedField
            def followers = getFollowers(it).collect { it.aliasedField }
            return [thisField, *followers]
        }.flatten() as List<Field<?>>

    }


    private List<DbField> getFollowers(String fieldName) {

        if (!followerMap.containsKey(fieldName)) return Collections.emptyList()

        return followerMap[fieldName].collect { getFieldByName(it) }
    }

    JooqFilter follower(String parentKey, String... followers) {

        assertKeyExists(parentKey)

        assert followers.size() > 0, "followers cannot be null"

        followers.each { assertKeyExists(it) }

        followerMap[parentKey] = followers as List

        return this
    }

    private void assertKeyExists(String parentKey) {
        def parentField = getFieldByName(parentKey)
        assert parentField != null
    }

    private static boolean isShowList(MultiValueMap map) {
        def showList = map.getFirst("showList") ?: "true"
        def showListBool = showList.toString().toLowerCase() == 'true'
        showListBool
    }


    Condition whereClause(MultiValueMap map) {
        def filterKeys = map.keySet() as List
        filterKeys.removeAll(RESERVED_PROPERTIES)
        filterKeys.removeAll { it?.toString()?.startsWith(FILTER_OVERRIDE_PREFIX) }


        return filterKeys
                .collect { createConditionForField(it, map) }
                .inject(DSL.trueCondition()) { acc, val -> acc.and(val) }
    }

    private static FilterType resolveFilterType(DbField field, MultiValueMap map) {

        def code = map.getFirst("_ftype_${field.name}" as String)?.toString()
        if (code == null)
            return field.filterType

        def filterType = FilterType.values()
                .find { it.name().equalsIgnoreCase(code ) } ?: field.filterType

        return filterType
    }

    private Condition createConditionForField(rawFieldName, MultiValueMap<String, Object> map) {

        def fieldName = rawFieldName.toString()
        def isArrayParam = fieldName.endsWith('[]')
        if (isArrayParam) {
            fieldName = fieldName.replace('[]', '')
        }

        def field = getFieldByName(fieldName)
        def jooqField = field.field
        def values = map.get(rawFieldName)
        def filterType = resolveFilterType(field, map)

        if (isArrayParam) {
            values = [*StringUtils.split(values.first().toString(), ',')]
        }


        if (values.size() == 1) {
            return createCondition(filterType, jooqField, filterType in [FilterType.IN, FilterType.NIN] ? values : values.first())
        }

        switch (filterType) {
            case [FilterType.EQ, FilterType.IN]:
                return createCondition(FilterType.IN, jooqField, values)
            case [FilterType.NIN, FilterType.NEQ]:
                return createCondition(FilterType.NIN, jooqField, values)
            default:
                return values
                        .collect { createCondition(filterType, jooqField, it) }
                        .inject(DSL.noCondition()) { acc, val -> (acc | val) }
        }

    }


    final def static filterHandlers = [
            (FilterType.EQ)         : { Field field, Object value -> field.eq(DSL.val(value)) },

            (FilterType.GT)         : { Field field, Object value -> field.gt(DSL.val(value)) },

            (FilterType.GTE)        : { Field field, Object value -> field.greaterOrEqual(DSL.val(value)) },

            (FilterType.LT)         : { Field field, Object value -> field.lt(DSL.val(value)) },

            (FilterType.LTE)        : { Field field, Object value -> field.lessOrEqual(DSL.val(value)) },

            (FilterType.LIKE)       : { Field field, Object value -> field.like(contains(value)) },

            (FilterType.STARTS_WITH): { Field field, Object value -> field.like(beginsWith(value)) },

            (FilterType.IN)         : { Field field, Object value -> field.in(value.collect { DSL.val(it) }) },

            (FilterType.NIN)        : { Field field, Object value -> field.notIn(value.collect { DSL.val(it) }) }
    ]


    static String contains(Object value) {
        return "%$value%";
    }

    static String beginsWith(Object value) {
        return "$value%";
    }


    @PackageScope
    static Condition createCondition(FilterType type, Field field, value) {
        // SQLDataType.DECIMAL like  type#getSQLDataType()

        def finalValue = value
        def dataType = field.getDataType()

        switch (dataType) {
            case SQLDataType.DATE:
                finalValue = DateTimeUtil.getSqlDateFromString(value)
                break
            case SQLDataType.TIMESTAMP:
                finalValue = DateTimeUtil.getSqlDateFromString(value)
                field = DSL.date(field)
                break
            case [SQLDataType.BIT, SQLDataType.BOOLEAN]:
                if (type in [FilterType.EQ,FilterType.IN,FilterType.NEQ,FilterType.NIN]) {
                finalValue = value instanceof List ?
                        value.collect { Boolean.parseBoolean(it?.toString()) } :
                Boolean.parseBoolean(value?.toString())
            }
            break
        }

        def filterFn = filterHandlers[type]
        return filterFn(field, finalValue)
    }


    Collection<SortField<?>> sortFields(MultiValueMap map) {

        if (!isShowList(map)) {
            return [DSL.val(1).sort(SortOrder.ASC)]
        }

        def sortDir = map.getFirst('sortOrder')?.toString()?.toUpperCase() ?: 'ASC'

        def sortOrder = sortDir == 'DESC' ? SortOrder.DESC : SortOrder.ASC

        def sortByString = map.getFirst('sortBy')?.toString()?.trim()

        if (!sortByString) return Collections.emptyList()

        return StringUtils.split(sortByString, ',')
                .collect { getFieldByName(it).field }
                .collect { it.sort(sortOrder) }


    }

    Tuple2<Integer, Integer> paginationParams(MultiValueMap map) {
        def limit = map.getFirst("limit") ?: '50'
        def offset = map.getFirst("offset") ?: '0'

        return [offset as int, Math.max(limit as int,1)]
    }

    private DbField getFieldByName(String name) {
        def field = fieldMap.get(name)

        if (field == null) {
            throw new Exception("Field[$name] not supported")
        }

        return field
    }

}

enum FilterType {
    EQ, LT, GT, GTE, LTE, LIKE, IN, STARTS_WITH, NIN, NEQ
}


class DbField {
    String name
    Field field
    FilterType filterType

    Field getAliasedField() {
        return field.as(name)
    }

    static DbField dbField(Field field, String fieldName) {
        return dbField(field, fieldName, inferSuitableOperator(field.getDataType()))
    }

    static DbField dbField(Field f, String fieldName, FilterType operator) {
        DbField field = new DbField();
        field.setField(f)
        field.setName(fieldName)
        field.setFilterType(operator)
        return field;
    }

    static DbField dbField(String table, String column, String alias, DataType dataType, FilterType fileType = null) {
        Field<Object> field = jooqField(table, column, dataType)
        return dbField(field, alias, fileType ?: inferSuitableOperator(dataType))
    }

    private static Field<Object> jooqField(String table, String column, DataType dataType) {
        return DSL.field(DSL.name(table, column), dataType)
    }

    private static FilterType inferSuitableOperator(DataType type) {
        def javaType = type.type

        if (type.isDateTime()) {
            return FilterType.EQ
        }

        if (type.isNumeric() ||
                javaType == Boolean.class) {
            return FilterType.STARTS_WITH
        }


        return FilterType.LIKE
    }
}



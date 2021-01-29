
package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transactiontype.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="transactiontype">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DEPOSIT"/>
 *     &lt;enumeration value="WITHDRAWAL"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "transactiontype")
@XmlEnum
public enum Transactiontype {

    DEPOSIT,
    WITHDRAWAL,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static Transactiontype fromValue(String v) {
        return valueOf(v);
    }

}

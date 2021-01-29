
package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accounttype.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="accounttype">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MOBILE_MONEY"/>
 *     &lt;enumeration value="COMMISSIONING"/>
 *     &lt;enumeration value="LOYALTY_POINTS"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "accounttype")
@XmlEnum
public enum Accounttype {

    MOBILE_MONEY,
    COMMISSIONING,
    LOYALTY_POINTS,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static Accounttype fromValue(String v) {
        return valueOf(v);
    }

}


package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accountstatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="accountstatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="BLOCKED"/>
 *     &lt;enumeration value="CLOSED"/>
 *     &lt;enumeration value="UNAVAILABLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "accountstatus")
@XmlEnum
public enum Accountstatus {

    ACTIVE,
    BLOCKED,
    CLOSED,
    UNAVAILABLE;

    public String value() {
        return name();
    }

    public static Accountstatus fromValue(String v) {
        return valueOf(v);
    }

}

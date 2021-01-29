
package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accountholderstatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="accountholderstatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVE"/>
 *     &lt;enumeration value="REGISTERED"/>
 *     &lt;enumeration value="REGISTERED_BLOCKED"/>
 *     &lt;enumeration value="REGISTERED_CLOSED"/>
 *     &lt;enumeration value="BLOCKED"/>
 *     &lt;enumeration value="CLOSED"/>
 *     &lt;enumeration value="CREATED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "accountholderstatus")
@XmlEnum
public enum Accountholderstatus {

    ACTIVE,
    REGISTERED,
    REGISTERED_BLOCKED,
    REGISTERED_CLOSED,
    BLOCKED,
    CLOSED,
    CREATED;

    public String value() {
        return name();
    }

    public static Accountholderstatus fromValue(String v) {
        return valueOf(v);
    }

}

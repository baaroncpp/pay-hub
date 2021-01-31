
package com.payhub.mobilemoney.mtn.mtnModels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for acceptedtc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="acceptedtc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="acceptedtcversion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="acceptedtcdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acceptedtc", propOrder = {
    "acceptedtcversion",
    "acceptedtcdate"
})
public class Acceptedtc {

    protected String acceptedtcversion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar acceptedtcdate;

    /**
     * Gets the value of the acceptedtcversion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptedtcversion() {
        return acceptedtcversion;
    }

    /**
     * Sets the value of the acceptedtcversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptedtcversion(String value) {
        this.acceptedtcversion = value;
    }

    /**
     * Gets the value of the acceptedtcdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAcceptedtcdate() {
        return acceptedtcdate;
    }

    /**
     * Sets the value of the acceptedtcdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAcceptedtcdate(XMLGregorianCalendar value) {
        this.acceptedtcdate = value;
    }

}

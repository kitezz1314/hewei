
package com.demo.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cancle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cancle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="machineNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cancle", propOrder = {
    "machineNumber",
    "atr",
    "status"
})
public class Cancle {

    protected String machineNumber;
    protected String atr;
    protected String status;

    /**
     * Gets the value of the machineNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMachineNumber() {
        return machineNumber;
    }

    /**
     * Sets the value of the machineNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMachineNumber(String value) {
        this.machineNumber = value;
    }

    /**
     * Gets the value of the atr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtr() {
        return atr;
    }

    /**
     * Sets the value of the atr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtr(String value) {
        this.atr = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}

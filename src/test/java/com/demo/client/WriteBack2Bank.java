
package com.demo.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for writeBack2Bank complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="writeBack2Bank">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="machineNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "writeBack2Bank", propOrder = {
    "machineNumber",
    "requestData"
})
public class WriteBack2Bank {

    protected String machineNumber;
    protected String requestData;

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
     * Gets the value of the requestData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestData() {
        return requestData;
    }

    /**
     * Sets the value of the requestData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestData(String value) {
        this.requestData = value;
    }

}

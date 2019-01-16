
package com.demo.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Invoker", targetNamespace = "http://cxfservice.boyasafe.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Invoker {


    /**
     * 
     * @param status
     * @param atr
     * @param machineNumber
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "cancle", targetNamespace = "http://cxfservice.boyasafe.com/", className = "com.demo.client.Cancle")
    @ResponseWrapper(localName = "cancleResponse", targetNamespace = "http://cxfservice.boyasafe.com/", className = "com.demo.client.CancleResponse")
    public String cancle(
        @WebParam(name = "machineNumber", targetNamespace = "")
        String machineNumber,
        @WebParam(name = "atr", targetNamespace = "")
        String atr,
        @WebParam(name = "status", targetNamespace = "")
        String status);

    /**
     * 
     * @param machineNumber
     * @param requestData
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "writeBack2Social", targetNamespace = "http://cxfservice.boyasafe.com/", className = "com.demo.client.WriteBack2Social")
    @ResponseWrapper(localName = "writeBack2SocialResponse", targetNamespace = "http://cxfservice.boyasafe.com/", className = "com.demo.client.WriteBack2SocialResponse")
    public String writeBack2Social(
        @WebParam(name = "machineNumber", targetNamespace = "")
        String machineNumber,
        @WebParam(name = "requestData", targetNamespace = "")
        String requestData);

    /**
     * 
     * @param requestData
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getSecret", targetNamespace = "http://cxfservice.boyasafe.com/", className = "com.demo.client.GetSecret")
    @ResponseWrapper(localName = "getSecretResponse", targetNamespace = "http://cxfservice.boyasafe.com/", className = "com.demo.client.GetSecretResponse")
    public String getSecret(
        @WebParam(name = "requestData", targetNamespace = "")
        String requestData);

    /**
     * 
     * @param machineNumber
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getData", targetNamespace = "http://cxfservice.boyasafe.com/", className = "com.demo.client.GetData")
    @ResponseWrapper(localName = "getDataResponse", targetNamespace = "http://cxfservice.boyasafe.com/", className = "com.demo.client.GetDataResponse")
    public String getData(
        @WebParam(name = "machineNumber", targetNamespace = "")
        String machineNumber);

    /**
     * 
     * @param machineNumber
     * @param requestData
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "writeBack2Bank", targetNamespace = "http://cxfservice.boyasafe.com/", className = "com.demo.client.WriteBack2Bank")
    @ResponseWrapper(localName = "writeBack2BankResponse", targetNamespace = "http://cxfservice.boyasafe.com/", className = "com.demo.client.WriteBack2BankResponse")
    public String writeBack2Bank(
        @WebParam(name = "machineNumber", targetNamespace = "")
        String machineNumber,
        @WebParam(name = "requestData", targetNamespace = "")
        String requestData);

}
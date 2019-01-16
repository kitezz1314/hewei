
package com.demo.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "InvokerImplService", targetNamespace = "http://cxfservice.webservice.com/", wsdlLocation = "http://139.129.205.59:7071/sendcard/services/Invoker?wsdl")
public class InvokerImplService
    extends Service
{

    private final static URL INVOKERIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException INVOKERIMPLSERVICE_EXCEPTION;
    private final static QName INVOKERIMPLSERVICE_QNAME = new QName("http://cxfservice.webservice.com/", "InvokerImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://139.129.205.59:7071/sendcard/services/Invoker?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        INVOKERIMPLSERVICE_WSDL_LOCATION = url;
        INVOKERIMPLSERVICE_EXCEPTION = e;
    }

    public InvokerImplService() {
        super(__getWsdlLocation(), INVOKERIMPLSERVICE_QNAME);
    }

    public InvokerImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), INVOKERIMPLSERVICE_QNAME, features);
    }

    public InvokerImplService(URL wsdlLocation) {
        super(wsdlLocation, INVOKERIMPLSERVICE_QNAME);
    }

    public InvokerImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, INVOKERIMPLSERVICE_QNAME, features);
    }

    public InvokerImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public InvokerImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Invoker
     */
    @WebEndpoint(name = "InvokerImplPort")
    public Invoker getInvokerImplPort() {
        return super.getPort(new QName("http://cxfservice.webservice.com/", "InvokerImplPort"), Invoker.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Invoker
     */
    @WebEndpoint(name = "InvokerImplPort")
    public Invoker getInvokerImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://cxfservice.webservice.com/", "InvokerImplPort"), Invoker.class, features);
    }

    private static URL __getWsdlLocation() {
        if (INVOKERIMPLSERVICE_EXCEPTION!= null) {
            throw INVOKERIMPLSERVICE_EXCEPTION;
        }
        return INVOKERIMPLSERVICE_WSDL_LOCATION;
    }

}

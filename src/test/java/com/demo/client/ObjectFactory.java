
package com.demo.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.demo.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WriteBack2BankResponse_QNAME = new QName("http://cxfservice.boyasafe.com/", "writeBack2BankResponse");
    private final static QName _Cancle_QNAME = new QName("http://cxfservice.boyasafe.com/", "cancle");
    private final static QName _GetSecretResponse_QNAME = new QName("http://cxfservice.boyasafe.com/", "getSecretResponse");
    private final static QName _WriteBack2Bank_QNAME = new QName("http://cxfservice.boyasafe.com/", "writeBack2Bank");
    private final static QName _GetDataResponse_QNAME = new QName("http://cxfservice.boyasafe.com/", "getDataResponse");
    private final static QName _GetData_QNAME = new QName("http://cxfservice.boyasafe.com/", "getData");
    private final static QName _CancleResponse_QNAME = new QName("http://cxfservice.boyasafe.com/", "cancleResponse");
    private final static QName _GetSecret_QNAME = new QName("http://cxfservice.boyasafe.com/", "getSecret");
    private final static QName _WriteBack2Social_QNAME = new QName("http://cxfservice.boyasafe.com/", "writeBack2Social");
    private final static QName _WriteBack2SocialResponse_QNAME = new QName("http://cxfservice.boyasafe.com/", "writeBack2SocialResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.demo.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WriteBack2SocialResponse }
     * 
     */
    public WriteBack2SocialResponse createWriteBack2SocialResponse() {
        return new WriteBack2SocialResponse();
    }

    /**
     * Create an instance of {@link WriteBack2Social }
     * 
     */
    public WriteBack2Social createWriteBack2Social() {
        return new WriteBack2Social();
    }

    /**
     * Create an instance of {@link GetSecret }
     * 
     */
    public GetSecret createGetSecret() {
        return new GetSecret();
    }

    /**
     * Create an instance of {@link CancleResponse }
     * 
     */
    public CancleResponse createCancleResponse() {
        return new CancleResponse();
    }

    /**
     * Create an instance of {@link GetData }
     * 
     */
    public GetData createGetData() {
        return new GetData();
    }

    /**
     * Create an instance of {@link GetDataResponse }
     * 
     */
    public GetDataResponse createGetDataResponse() {
        return new GetDataResponse();
    }

    /**
     * Create an instance of {@link WriteBack2BankResponse }
     * 
     */
    public WriteBack2BankResponse createWriteBack2BankResponse() {
        return new WriteBack2BankResponse();
    }

    /**
     * Create an instance of {@link WriteBack2Bank }
     * 
     */
    public WriteBack2Bank createWriteBack2Bank() {
        return new WriteBack2Bank();
    }

    /**
     * Create an instance of {@link GetSecretResponse }
     * 
     */
    public GetSecretResponse createGetSecretResponse() {
        return new GetSecretResponse();
    }

    /**
     * Create an instance of {@link Cancle }
     * 
     */
    public Cancle createCancle() {
        return new Cancle();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteBack2BankResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfservice.boyasafe.com/", name = "writeBack2BankResponse")
    public JAXBElement<WriteBack2BankResponse> createWriteBack2BankResponse(WriteBack2BankResponse value) {
        return new JAXBElement<WriteBack2BankResponse>(_WriteBack2BankResponse_QNAME, WriteBack2BankResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cancle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfservice.boyasafe.com/", name = "cancle")
    public JAXBElement<Cancle> createCancle(Cancle value) {
        return new JAXBElement<Cancle>(_Cancle_QNAME, Cancle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSecretResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfservice.boyasafe.com/", name = "getSecretResponse")
    public JAXBElement<GetSecretResponse> createGetSecretResponse(GetSecretResponse value) {
        return new JAXBElement<GetSecretResponse>(_GetSecretResponse_QNAME, GetSecretResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteBack2Bank }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfservice.boyasafe.com/", name = "writeBack2Bank")
    public JAXBElement<WriteBack2Bank> createWriteBack2Bank(WriteBack2Bank value) {
        return new JAXBElement<WriteBack2Bank>(_WriteBack2Bank_QNAME, WriteBack2Bank.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfservice.boyasafe.com/", name = "getDataResponse")
    public JAXBElement<GetDataResponse> createGetDataResponse(GetDataResponse value) {
        return new JAXBElement<GetDataResponse>(_GetDataResponse_QNAME, GetDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfservice.boyasafe.com/", name = "getData")
    public JAXBElement<GetData> createGetData(GetData value) {
        return new JAXBElement<GetData>(_GetData_QNAME, GetData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfservice.boyasafe.com/", name = "cancleResponse")
    public JAXBElement<CancleResponse> createCancleResponse(CancleResponse value) {
        return new JAXBElement<CancleResponse>(_CancleResponse_QNAME, CancleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSecret }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfservice.boyasafe.com/", name = "getSecret")
    public JAXBElement<GetSecret> createGetSecret(GetSecret value) {
        return new JAXBElement<GetSecret>(_GetSecret_QNAME, GetSecret.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteBack2Social }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfservice.boyasafe.com/", name = "writeBack2Social")
    public JAXBElement<WriteBack2Social> createWriteBack2Social(WriteBack2Social value) {
        return new JAXBElement<WriteBack2Social>(_WriteBack2Social_QNAME, WriteBack2Social.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WriteBack2SocialResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfservice.boyasafe.com/", name = "writeBack2SocialResponse")
    public JAXBElement<WriteBack2SocialResponse> createWriteBack2SocialResponse(WriteBack2SocialResponse value) {
        return new JAXBElement<WriteBack2SocialResponse>(_WriteBack2SocialResponse_QNAME, WriteBack2SocialResponse.class, null, value);
    }

}

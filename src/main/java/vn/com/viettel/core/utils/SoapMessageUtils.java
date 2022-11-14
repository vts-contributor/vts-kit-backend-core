package vn.com.viettel.core.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.soap.*;

import vn.com.viettel.core.rest.SoapMessageDTO;


public class SoapMessageUtils {

    // Get example soap message
    private SOAPMessage getSOAPMessageExample() throws Exception {

        SoapMessageDTO soapMessageDTO = new SoapMessageDTO("vdtc", "$18ac#75%@", "VDTC", "VTSHOP", "84981651642", "Test 4sms 123", "0");
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
         
 /*        	Request:
        	 "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:impl=\"http://impl.bulkSms.ws/\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<impl:wsCpMt>"
                + "<User>" + username + "</User>"
                + "<Password>" + password + "</Password>"
                + "<CPCode>" + cpCode + "</CPCode>"
                + "<RequestID>" + "1" + "</RequestID>"
                + "<UserID>" + receiver + "</UserID>"
                + "<ReceiverID>" + receiver + "</ReceiverID>"
                + "<ServiceID>" + sender + "</ServiceID>"
                + "<CommandCode>" + "bulksms" + "</CommandCode>"
                + "<Content>" + content + "</Content>"
                + "<ContentType>" + contentType + "</ContentType>"
                + "</impl:wsCpMt>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>"; */

         // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("impl", "http://impl.bulkSms.ws/");

         // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElement = soapBody.addChildElement("wsCpMt", "impl");

        SOAPElement soapUser = soapBodyElement.addChildElement("User");
        soapUser.addTextNode(soapMessageDTO.getUsername());

        SOAPElement soapPassword = soapBodyElement.addChildElement("Password");
        soapPassword.addTextNode(soapMessageDTO.getPassword());

        SOAPElement soapCpCode = soapBodyElement.addChildElement("CPCode");
        soapCpCode.addTextNode(soapMessageDTO.getCpCode());

        SOAPElement soapRequestId = soapBodyElement.addChildElement("RequestID");
        soapRequestId.addTextNode("1");

        SOAPElement soapUserId = soapBodyElement.addChildElement("UserID");
        soapUserId.addTextNode(soapMessageDTO.getReceiver());

        SOAPElement soapReceiverId = soapBodyElement.addChildElement("ReceiverID");
        soapReceiverId.addTextNode(soapMessageDTO.getReceiver());

        SOAPElement soapServiceId = soapBodyElement.addChildElement("ServiceID");
        soapServiceId.addTextNode(soapMessageDTO.getSender());

        SOAPElement soapCommandCode = soapBodyElement.addChildElement("CommandCode");
        soapCommandCode.addTextNode("bulksms");

        SOAPElement soapContent = soapBodyElement.addChildElement("Content");
        soapContent.addTextNode(soapMessageDTO.getContent());

        SOAPElement soapContentType = soapBodyElement.addChildElement("ContentType");
        soapContentType.addTextNode(soapMessageDTO.getContentType());

        soapMessage.saveChanges();
        return soapMessage;

	}

    public static SOAPMessage parseStringToSOAPMessage(String request) throws Exception {
        InputStream is = new ByteArrayInputStream(request.getBytes());
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage(null, is);
        soapMessage.saveChanges();
        return soapMessage;
    }
	
}

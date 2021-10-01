/**
 * 
 */
package it.andreascanzani.example.wssecurity.cxf.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import it.andreascanzani.sumservice.schema.GetSumRequest;
import it.andreascanzani.sumservice.schema.GetSumResponse;

/**
 * @author dj0985
 *
 */
@Service
public class ClientSoapClient {
	@Autowired
    private Jaxb2Marshaller jaxb2Marshaller;

    private WebServiceTemplate webServiceTemplate;
	
	public GetSumResponse getItemInfo(GetSumRequest itemRequest){
        webServiceTemplate = new WebServiceTemplate(jaxb2Marshaller);
        return (GetSumResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:8080/services/soap/SumService",itemRequest);
    }
	
}

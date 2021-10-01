/**
 * 
 */
package it.andreascanzani.example.wssecurity.cxf.client;

import it.andreascanzani.example.wssecurity.cxf.client.ClientSoapClient;
import it.andreascanzani.sumservice.schema.GetSumRequest;
import it.andreascanzani.sumservice.schema.GetSumResponse;
import it.andreascanzani.sumservice.wsdl.SumService;
import it.andreascanzani.sumservice.wsdl.SumServicePort;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.message.Message;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    ClientSoapClient soapClient;

    @PostMapping("/sumitem")
    public GetSumResponse item(@RequestBody GetSumRequest itemRequest){
    	
    	SumService sumService = new SumService();
		SumServicePort sumServiceSOAPBinding = sumService.getSumServiceSOAPBinding();

		Client client = ClientProxy.getClient(sumServiceSOAPBinding);
		Endpoint endpoint = client.getEndpoint();

		client.getRequestContext().put(Message.ENDPOINT_ADDRESS, "http://localhost:8080/services/soap/SumService");
		
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		props.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
		props.put(WSHandlerConstants.PW_CALLBACK_CLASS, UsernameTokenClientCallbackHandler.class.getName());
		props.put(WSHandlerConstants.USER, "admin");

		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(props);
		endpoint.getOutInterceptors().add(wssOut);
	
		GetSumRequest parameters = new GetSumRequest();
		System.out.println("item 1 is:::::"+itemRequest.getAddendOne());
		System.out.println("item 2 is:::::"+itemRequest.getAddendTwo());
		parameters.setAddendOne(itemRequest.getAddendOne());
		parameters.setAddendTwo(itemRequest.getAddendTwo());
		System.out.println("the sum is::::"+ sumServiceSOAPBinding.getSum(parameters).getResult());
		GetSumResponse res = new GetSumResponse();
		res.setResult(sumServiceSOAPBinding.getSum(parameters).getResult());
        return res;
    }
}


# SoapProducerConsumer

Producer testing
URL --> http://localhost:8080/services/soap/SumService?wsdl
output :
	<wsdl:definitions xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:tns="http://impl.cxf.wssecurity.example.andreascanzani.it/" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:ns3="http://schemas.xmlsoap.org/soap/http" xmlns:ns1="http://andreascanzani.it/SumService/wsdl" name="SumServiceImplService" targetNamespace="http://impl.cxf.wssecurity.example.andreascanzani.it/">
	<wsdl:import location="http://localhost:8080/services/soap/SumService?wsdl=SumServicePort.wsdl" namespace="http://andreascanzani.it/SumService/wsdl"> </wsdl:import>
	<wsdl:binding name="SumServiceImplServiceSoapBinding" type="ns1:SumServicePort">
	<soap12:binding style="document" transport="http://schemas.xmlsoap.org/soap/http"/>
	<wsdl:operation name="GetSum">
	<soap12:operation soapAction="http://andreascanzani.it/SumService/GetSum" style="document"/>
	<wsdl:input name="GetSum">
	<soap12:body use="literal"/>
	</wsdl:input>
	<wsdl:output name="GetSumResponse">
	<soap12:body use="literal"/>
	</wsdl:output>
	</wsdl:operation>
	</wsdl:binding>
	<wsdl:service name="SumServiceImplService">
	<wsdl:port binding="tns:SumServiceImplServiceSoapBinding" name="SumServiceImplPort">
	<soap12:address location="http://localhost:8080/services/soap/SumService"/>
	</wsdl:port>
	</wsdl:service>
	</wsdl:definitions>
	
	

Consumer Testing:

In postman, hit http://localhost:8081/sumitem with POST request with below payload:
{
    "addendOne" : 112,
    "addendTwo" : 10
}

Output:
{
    "result": 122
}



 

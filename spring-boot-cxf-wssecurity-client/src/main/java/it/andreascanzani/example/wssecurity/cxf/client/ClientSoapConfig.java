package it.andreascanzani.example.wssecurity.cxf.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientSoapConfig {
	@Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan("it.andreascanzani.example.wssecurity.cxf.client");
        return jaxb2Marshaller;
    }
}

package com.tameme.cobranza.common.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.tameme.cobranza.common.entity.Cfdi;
import com.tameme.cobranza.common.entity.SatResponse;
import com.tameme.cobranza.common.service.interfaz.IEstatusService;

@Service
public class EstatusService implements IEstatusService {
	
	@Bean
	public RestTemplate restTemplate() {
		return (new RestTemplateBuilder()).build();
	}	
	
	@Override
	public SatResponse estado(Cfdi cfdi) throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("SOAPAction", "http://tempuri.org/IConsultaCFDIService/Consulta");
		headers.setContentType(MediaType.TEXT_XML);
		
		String s = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n"
				+ "    <soapenv:Header/>\n"
				+ "    <soapenv:Body>\n"
				+ "        <tem:Consulta>\n"
				+ "            <!--Optional:-->\n"
				+ "            <tem:expresionImpresa>\n"
				+ "                <![CDATA[?re=TBT8606096Q3&rr=" + cfdi.getSubcte().getCliente().getRfc() 
				+ "&tt=" + cfdi.getTotal() + "&id=" + cfdi.getUuid() + "]]>\n"
				+ "         \n"
				+ "            </tem:expresionImpresa>\n"
				+ "        </tem:Consulta>\n"
				+ "    </soapenv:Body>\n"
				+ "</soapenv:Envelope>";

		
		HttpEntity<String> entity = new HttpEntity<>(s, headers);
		
		ResponseEntity<String> response = restTemplate().postForEntity(
				"https://consultaqr.facturaelectronica.sat.gob.mx/ConsultaCFDIService.svc", entity, String.class);
	
		XmlMapper mapper = new XmlMapper();
		JsonNode node = mapper.readTree(response.getBody()).findValue("ConsultaResult");
		
		System.out.println(node);
		
	    SatResponse estado = mapper.treeToValue(node, SatResponse.class);
	    
		return estado;
	}

}

package com.tameme.cobranza.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tameme.cobranza.common.entity.Cfdi;
import com.tameme.cobranza.common.service.interfaz.ICfdiService;

@RestController
public class CfdiController {
	
	@Autowired
	private ICfdiService cfdiService;
	
	@GetMapping("/user/cfdi/carga")
	public List<Cfdi> carga() {
		return cfdiService.carga();
	}
	
	@GetMapping("/user/cfdi/porpagar/{clienteId}")
	public List<Cfdi> porpagar(@PathVariable("clienteId")Long clienteId) {
		return cfdiService.porpagar(clienteId);
	}

}

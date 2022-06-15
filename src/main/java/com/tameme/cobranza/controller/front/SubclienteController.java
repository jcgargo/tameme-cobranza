package com.tameme.cobranza.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tameme.cobranza.common.entity.Subcliente;
import com.tameme.cobranza.common.service.interfaz.ISubclienteService;

@RestController
public class SubclienteController {
	
	@Autowired
	private ISubclienteService subcteService;
	
	@GetMapping("/user/subcliente/carga")
	public List<Subcliente> carga() {
		return subcteService.carga();
	}

}

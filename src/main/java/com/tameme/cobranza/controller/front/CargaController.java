package com.tameme.cobranza.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tameme.cobranza.common.service.interfaz.ICargaService;


@RestController
public class CargaController {
	
	@Autowired
	ICargaService cargaService;

	@GetMapping("/user/ejecutaCarga/{folder}")
	public Long getOrganiza(@PathVariable("folder") String folder) {
		return cargaService.extrae(folder);
	}
}

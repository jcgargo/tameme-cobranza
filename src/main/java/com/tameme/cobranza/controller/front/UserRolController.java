package com.tameme.cobranza.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserRolController {
	
	@GetMapping("/user/index")
	public String indexUser() {
		return "user/index";
	}
	
	@GetMapping(value = "user/carga")
	public String carga() {
		return "user/carga";
	}
	
	@GetMapping("/user/clientes")
	public String cliente() {
		return "user/clientes";
	}	
	
	@GetMapping("/user/subclientes")
	public String subcliente() {
		return "user/subclientes";
	}	
	
	@GetMapping("/user/cfdis")
	public String cfdis() {
		return "user/cfdis";
	}	
	
	@GetMapping("/user/ordenpago/genera")
	public String ordenpagoGenera() {
		return "user/ordenpago_genera";
	}		
	
	@GetMapping("/user/ordenpago/consulta")
	public String ordenpagoConsulta() {
		return "user/ordenpago_consulta";
	}			
	
}

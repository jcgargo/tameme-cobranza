package com.tameme.cobranza.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminRolController {

	@GetMapping("/admin/index")
	public String indexAdmin() {
		return "admin/index";
	}

	
}

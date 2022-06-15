package com.tameme.cobranza.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tameme.cobranza.common.entity.Config;
import com.tameme.cobranza.common.service.interfaz.IConfigService;

@Controller
public class ConfigController {

	@Autowired
	IConfigService service;
	
	@GetMapping("/admin/cargaConfig")
	public List<Config> cargaMonitor() {
		return service.getConfig();
	}
	
	@PostMapping("/admin/update/{id}")
	public String updateConfig(@PathVariable("id") long id, @Validated Config config, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        config.setId(id);
	        return "admin/config";
	    }
	        
	    service.guardaConfig(config);
	    return "admin/config";
	}	
	
	@GetMapping("/admin/editConfig/{id}")
	public String showConfig(@PathVariable("id") long id, Model model) {
	    Config config = service.cargaConfig(id);
	    
	    model.addAttribute("config", config);
	    return "admin/config";
	}	
}

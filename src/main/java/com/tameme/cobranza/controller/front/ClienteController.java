package com.tameme.cobranza.controller.front;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tameme.cobranza.common.entity.Cliente;
import com.tameme.cobranza.common.entity.view.EnvolturaCombo;
import com.tameme.cobranza.common.entity.view.IDatoCombo;
import com.tameme.cobranza.common.service.interfaz.IClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	private IClienteService cteService;

	@GetMapping("/user/cliente/carga")
	public List<Cliente> carga() {
		return cteService.carga();
	}
	
	@GetMapping("user/cliente/combo_cliente")
	public EnvolturaCombo<IDatoCombo> filtaCliente() {
		EnvolturaCombo<IDatoCombo> resp = new EnvolturaCombo<>();
		resp.setResults(cteService.filtroCliente());
		return resp;
	}
	
	@GetMapping("/user/cliente/{rfc}")
	public Cliente buscaporRfc(@PathVariable("rfc")String rfc) {
		return cteService.buscaporRfc(rfc);
	}	
}

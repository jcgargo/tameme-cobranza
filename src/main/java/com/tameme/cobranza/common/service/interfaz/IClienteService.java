package com.tameme.cobranza.common.service.interfaz;

import java.util.List;

import com.tameme.cobranza.common.entity.Cliente;
import com.tameme.cobranza.common.entity.view.IDatoCombo;

public interface IClienteService {
	
	public Cliente busca(Long clienteId);
	
	public Cliente buscaporRfc(String rfc);
	
	public void guardar(Cliente cliente);
	
	public List<Cliente> carga();
	
	public List<IDatoCombo> filtroCliente();

}

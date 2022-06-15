package com.tameme.cobranza.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tameme.cobranza.common.dao.IClienteDao;
import com.tameme.cobranza.common.entity.Cliente;
import com.tameme.cobranza.common.entity.view.IDatoCombo;
import com.tameme.cobranza.common.service.interfaz.IClienteService;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	IClienteDao dao;

	@Override
	public Cliente busca(Long clienteId) {
		Optional<Cliente> cte = dao.findById(clienteId);
		return cte.isPresent() ? cte.get() : null;
	}	
	@Override
	public Cliente buscaporRfc(String rfc) {
		Optional<Cliente> cte = dao.findByRfc(rfc);
		return cte.isPresent() ? cte.get() : null;
	}

	@Override
	public void guardar(Cliente cliente) {
		dao.save(cliente);
	}

	@Override
	public List<Cliente> carga() {
		return (List<Cliente>)dao.findAll();
	}

	@Override
	public List<IDatoCombo> filtroCliente() {
		return (List<IDatoCombo>)dao.filtroCliente();
	}


}

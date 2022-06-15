package com.tameme.cobranza.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tameme.cobranza.common.dao.ISubclienteDao;
import com.tameme.cobranza.common.entity.Subcliente;
import com.tameme.cobranza.common.service.interfaz.ISubclienteService;

@Service
public class SubclienteService implements ISubclienteService {

	@Autowired
	ISubclienteDao dao;
	
	@Override
	public Subcliente buscar(String nombre) {
		Optional<Subcliente> subcte = dao.findByNombre(nombre);
		return subcte.isPresent() ? subcte.get() : null;
	}

	@Override
	public List<Subcliente> carga() {
		return (List<Subcliente>)dao.findAll();
	}

	@Override
	public void guarda(Subcliente subcte) {
		dao.save(subcte);
	}
	
	

}

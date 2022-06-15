package com.tameme.cobranza.common.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tameme.cobranza.common.dao.ICfdiDao;
import com.tameme.cobranza.common.entity.Cfdi;
import com.tameme.cobranza.common.service.interfaz.ICfdiService;

@Service
public class CfdiService implements ICfdiService {
	
	@Autowired
	ICfdiDao dao;
	
	@Autowired
	EntityManager em;

	@Override
	public Cfdi busca(Long cfdiId) {
		Optional<Cfdi> cfdi = dao.findById(cfdiId);
		return cfdi.isPresent() ? cfdi.get() : null;
	}
		@Override
	public List<Cfdi> carga() {
		return (List<Cfdi>)dao.findAll();
	}

	@Override
	public List<Cfdi> porpagar(Long clienteId) {
		return (List<Cfdi>)dao.porpagar(clienteId);
	}
	
	@Override
	public void quitaOrdenpago(Long ordenpagoId) {
		dao.quitaOrdenpago(ordenpagoId);
	}
	@Override
	public void registraPago(Long ordenpagoId) {
		dao.registraPago(ordenpagoId);
	}
	@Override
	public void guarda(Cfdi cfdi) {
		dao.save(cfdi);
	}
	@Override
	public List<Cfdi> buscaSerieYFolio(String serie, String folio) {
		return dao.findBySerieAndFolio(serie, folio);
	}


}

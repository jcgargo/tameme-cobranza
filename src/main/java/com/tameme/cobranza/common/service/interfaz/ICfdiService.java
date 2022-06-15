package com.tameme.cobranza.common.service.interfaz;

import java.util.List;

import com.tameme.cobranza.common.entity.Cfdi;

public interface ICfdiService {
	
	public Cfdi busca(Long cfdiId);
	
	public List<Cfdi> buscaSerieYFolio(String serie, String folio);
	
	public void guarda(Cfdi cfdi);
	
	public List<Cfdi> carga();
	
	public List<Cfdi> porpagar(Long clienteId);
	
	public void quitaOrdenpago(Long ordenpagoId);
	
	public void registraPago(Long ordenpagoId);
}

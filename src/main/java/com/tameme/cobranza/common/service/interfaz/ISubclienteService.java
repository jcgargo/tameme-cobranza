package com.tameme.cobranza.common.service.interfaz;

import java.util.List;

import com.tameme.cobranza.common.entity.Subcliente;

public interface ISubclienteService {
	
	public Subcliente buscar(String nombre);
	
	public void guarda(Subcliente subcte);
	
	public List<Subcliente> carga();

}

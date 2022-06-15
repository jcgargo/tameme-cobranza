package com.tameme.cobranza.common.entity.view;

import java.util.ArrayList;
import java.util.List;

import com.tameme.cobranza.common.entity.Cfdi;
import com.tameme.cobranza.common.entity.OrdenPago;

public class RespuestaOrdenPago {
	
	private String mensaje;
	private OrdenPago ordenpago; 
	private List<Cfdi> agregados = new ArrayList<Cfdi>();
	private List<Cfdi> cancelados = new ArrayList<Cfdi>();
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public OrdenPago getOrdenpago() {
		return ordenpago;
	}
	public void setOrdenpago(OrdenPago ordenpago) {
		this.ordenpago = ordenpago;
	}
	public List<Cfdi> getAgregados() {
		return agregados;
	}
	public void setAgregados(List<Cfdi> agregados) {
		this.agregados = agregados;
	}
	public List<Cfdi> getCancelados() {
		return cancelados;
	}
	public void setCancelados(List<Cfdi> cancelados) {
		this.cancelados = cancelados;
	}

	
}

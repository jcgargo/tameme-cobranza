package com.tameme.cobranza.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ConsultaResult")
public class SatResponse {
	
	@JsonProperty("CodigoEstatus")
	private String codigoEstatus;
	@JsonProperty("EsCancelable")	
	private String esCancelable;
	@JsonProperty("Estado")
	private String estado;
	@JsonProperty("EstatusCancelacion")	
	private String estatusCancelacion;
	@JsonProperty("ValidacionEFOS")	
	private String validacionEFOS;
	
	
	public String getCodigoEstatus() {
		return codigoEstatus;
	}
	public void setCodigoEstatus(String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}
	public String getEsCancelable() {
		return esCancelable;
	}
	public void setEsCancelable(String esCancelable) {
		this.esCancelable = esCancelable;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstatusCancelacion() {
		return estatusCancelacion;
	}
	public void setEstatusCancelacion(String estatusCancelacion) {
		this.estatusCancelacion = estatusCancelacion;
	}
	public String getValidacionEFOS() {
		return validacionEFOS;
	}
	public void setValidacionEFOS(String validacionEFOS) {
		this.validacionEFOS = validacionEFOS;
	}

}

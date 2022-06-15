package com.tameme.cobranza.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "cfdi:Receptor")
public class Archivo {
	
	@JsonProperty("Rfc")	
	private String rfc; 
	@JsonProperty("Nombre")	
	private String nombre;
	@JsonProperty("UsoCFDI")	
	private String usoCFDI;	
	
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsoCFDI() {
		return usoCFDI;
	}
	public void setUsoCFDI(String usoCFDI) {
		this.usoCFDI = usoCFDI;
	}
}

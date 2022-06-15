package com.tameme.cobranza.common.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="configuracion")
public class Config implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id = 1L;
	private String rfc;
	private String directorioCarga;
	private String directorioOrdenes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getDirectorioCarga() {
		return directorioCarga;
	}
	public void setDirectorioCarga(String directorioCarga) {
		this.directorioCarga = directorioCarga;
	}
	public String getDirectorioOrdenes() {
		return directorioOrdenes;
	}
	public void setDirectorioOrdenes(String directorioOrdenes) {
		this.directorioOrdenes = directorioOrdenes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

package com.tameme.cobranza.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rolId;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario")	
	private Usuario usuario;
	private String rol;
	
	public Long getRolId() {
		return rolId;
	}
	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

}

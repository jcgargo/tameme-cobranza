package com.tameme.cobranza.common.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {

	@Id
	private String usuario;
	private String contrasena;
	private Boolean cuentaNoExpirada;
	private Boolean cuentaNoBloqueada;
	private Boolean credencialesNoExpiradas;
	private Boolean activo;
	
	private String nombre;
	private String correoElectronico;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
	private Set<Rol> roles;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Boolean isCuentaNoExpirada() {
		return cuentaNoExpirada;
	}

	public void setCuentaNoExpirada(Boolean cuentaNoExpirada) {
		this.cuentaNoExpirada = cuentaNoExpirada;
	}

	public Boolean isCuentaNoBloqueada() {
		return cuentaNoBloqueada;
	}

	public void setCuentaNoBloqueada(Boolean cuentaNoBloqueada) {
		this.cuentaNoBloqueada = cuentaNoBloqueada;
	}

	public Boolean isCredencialesNoExpiradas() {
		return credencialesNoExpiradas;
	}

	public void setCredencialesNoExpiradas(Boolean credencialesNoExpiradas) {
		this.credencialesNoExpiradas = credencialesNoExpiradas;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	
}

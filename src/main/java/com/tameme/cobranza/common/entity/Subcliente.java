package com.tameme.cobranza.common.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="subclientes")
public class Subcliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long subclienteId;
	private String nombre;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "clienteId")
	private Cliente cliente;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "subcte", cascade = CascadeType.ALL)
	private Set<Cfdi> cfdis;
	
	public Long getSubclienteId() {
		return subclienteId;
	}
	public void setSubclienteId(Long subclienteId) {
		this.subclienteId = subclienteId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Set<Cfdi> getCfdis() {
		return cfdis;
	}
	public void setCfdis(Set<Cfdi> cfdis) {
		this.cfdis = cfdis;
	}
	
}

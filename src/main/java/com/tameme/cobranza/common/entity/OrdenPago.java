package com.tameme.cobranza.common.entity;

import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ordenes_pago")
public class OrdenPago {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ordenpagoId;
	@JsonFormat(pattern = "dd/MM/yyyy")	
	private Date fechaGeneracion;
	private Double total;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")	
	private Date fechaPago;
	private String formaPago;
	private String archivo;
	private Boolean enviada;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "clienteId")	
	private Cliente cliente;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "ordenPago", cascade = CascadeType.DETACH)
	private Set<Cfdi> cfdis;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "usuario")	
	private Usuario usuario;

	public Long getOrdenpagoId() {
		return ordenpagoId;
	}

	public void setOrdenpagoId(Long ordenpagoId) {
		this.ordenpagoId = ordenpagoId;
	}

	public java.util.Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(java.util.Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public java.util.Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(java.util.Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
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

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getEnviada() {
		return enviada;
	}

	public void setEnviada(Boolean enviada) {
		this.enviada = enviada;
	}

}

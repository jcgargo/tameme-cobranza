package com.tameme.cobranza.common.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="cfdis")
public class Cfdi {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cfdiId;
	private String uuid;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")	
	private Date fechaTimbrado;
	private String archivo;
	private String serie;
	private String folio;

	private Double subtotal;
	private Double retencionIva;
	private Double retencionTransporte;
	private Double total;
	
	private Boolean pagado;
	
	private String codigo;
	private String estatus;
	private String cancelable;
	private String estatusCancelacion;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "subclienteId")	
	private Subcliente subcte;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ordenpagoId")	
	@JsonBackReference
	private OrdenPago ordenPago;
	
	public Long getCfdiId() {
		return cfdiId;
	}
	public void setCfdiId(Long cfdiId) {
		this.cfdiId = cfdiId;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Subcliente getSubcte() {
		return subcte;
	}
	public void setSubcte(Subcliente subcte) {
		this.subcte = subcte;
	}
	public Date getFechaTimbrado() {
		return fechaTimbrado;
	}
	public void setFechaTimbrado(Date fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getCancelable() {
		return cancelable;
	}
	public void setCancelable(String cancelable) {
		this.cancelable = cancelable;
	}
	public String getEstatusCancelacion() {
		return estatusCancelacion;
	}
	public void setEstatusCancelacion(String estatusCancelacion) {
		this.estatusCancelacion = estatusCancelacion;
	}
	public OrdenPago getOrdenPago() {
		return ordenPago;
	}
	public void setOrdenPago(OrdenPago ordenPago) {
		this.ordenPago = ordenPago;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Double getRetencionIva() {
		return retencionIva;
	}
	public void setRetencionIva(Double retencionIva) {
		this.retencionIva = retencionIva;
	}
	public Double getRetencionTransporte() {
		return retencionTransporte;
	}
	public void setRetencionTransporte(Double retencionTransporte) {
		this.retencionTransporte = retencionTransporte;
	}
	public Boolean getPagado() {
		return pagado;
	}
	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}
	
}

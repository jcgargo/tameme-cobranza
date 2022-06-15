package com.tameme.cobranza.common.service.interfaz;

import java.util.Date;
import java.util.List;

import com.tameme.cobranza.common.entity.OrdenPago;
import com.tameme.cobranza.common.entity.view.RespuestaOrdenPago;

public interface IOrdenPagoService {

	public List<OrdenPago> carga();
	
	public OrdenPago busca(Long ordenpagoId);
	
	public RespuestaOrdenPago genera(Long clienteId, List<Long> cfdis, String usr);
	
	public List<String> archivosOrden(OrdenPago op);
	
	public void eliminaOrden(Long ordenpagoId) throws Exception;
	
	public void registraPago(Long ordenpagoId, Date fecha, String forma) throws Exception;
	
	public void registraEnvio(OrdenPago op);
	
	public List<OrdenPago> porEnviar();
}

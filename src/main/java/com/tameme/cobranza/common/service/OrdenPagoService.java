package com.tameme.cobranza.common.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tameme.cobranza.common.dao.IOrdenPagoDao;
import com.tameme.cobranza.common.entity.Cfdi;
import com.tameme.cobranza.common.entity.Cliente;
import com.tameme.cobranza.common.entity.Config;
import com.tameme.cobranza.common.entity.OrdenPago;
import com.tameme.cobranza.common.entity.SatResponse;
import com.tameme.cobranza.common.entity.Usuario;
import com.tameme.cobranza.common.entity.view.RespuestaOrdenPago;
import com.tameme.cobranza.common.service.interfaz.ICfdiService;
import com.tameme.cobranza.common.service.interfaz.IClienteService;
import com.tameme.cobranza.common.service.interfaz.IOrdenPagoService;

@Service
public class OrdenPagoService implements IOrdenPagoService {
	
	private static final Logger log = LoggerFactory.getLogger(OrdenPagoService.class);
	
	@Autowired
	IOrdenPagoDao dao;
	
	@Autowired
	IClienteService cteService;
	
	@Autowired
	ICfdiService cfdiService;
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private EstatusService edoService;

	@Override
	public List<OrdenPago> carga() {
		return (List<OrdenPago>)dao.findAll();
	}
	
	@Override
	public OrdenPago busca(Long ordenpagoId) {
		Optional<OrdenPago> op = dao.findById(ordenpagoId);
		return op.isPresent() ? op.get() : null;
	}

	@Override
	public RespuestaOrdenPago genera(Long clienteId, List<Long> cfdis, String usr) {	
		RespuestaOrdenPago rop = new RespuestaOrdenPago();
		Cliente cte = cteService.busca(clienteId);
		OrdenPago op = new OrdenPago();
		op.setCliente(cte);
		op.setFechaGeneracion(new java.util.Date());
		op.setArchivo(generaNombreArchivo(clienteId));
		
		Set<Cfdi> lista = new LinkedHashSet<>();
		Double total = 0.00;
		for(Long cfdiId: cfdis) {
			Cfdi cfdi = cfdiService.busca(cfdiId);
			try {
				if (cfdi != null) {
					SatResponse edo = edoService.estado(cfdi);
					if (edo.getEstado().equals("Vigente")) {
						cfdi.setOrdenPago(op);
						lista.add(cfdi);
						total += cfdi.getTotal();
						rop.getAgregados().add(cfdi);
					} else {
						cfdi.setCodigo(edo.getCodigoEstatus());
						cfdi.setEstatus(edo.getEstado());
						cfdi.setCancelable(edo.getEsCancelable());
						cfdi.setEstatusCancelacion(edo.getEstatusCancelacion());
						cfdiService.guarda(cfdi);
						rop.getCancelados().add(cfdi);
					}
				}				
			} catch (Exception e) {
				log.error("Error al obtener el estado del comprobante " + cfdi.getFolio(), e);
			}
		}
		if (!lista.isEmpty()) {
			op.setCfdis(lista);
			op.setTotal(total);
			op.setEnviada(false);
			Usuario usuario = new Usuario();
			usuario.setUsuario(usr);
			op.setUsuario(usuario);
			op = dao.save(op);	
			rop.setOrdenpago(op);
			rop.setMensaje("Orden de pago #" + op.getOrdenpagoId() + " generada con exito");
		} else {
			rop.setMensaje("Orden de pago no se pudo generar ya que no contiene comprobantes");			
		}
		
		return rop;
	}

	@Override
	public List<String> archivosOrden(OrdenPago op) {
		List<String> archivos = new ArrayList<>();
		archivos.add(op.getArchivo());
		
		if (op.getCfdis() != null) {
			for(Cfdi cfdi: op.getCfdis()) {
				archivos.add(cfdi.getArchivo());
				archivos.add(cfdi.getArchivo().replace(".xml", ".pdf"));
			}			
		}
		
		return archivos;
	}
	
	private String generaNombreArchivo(Long clienteId) {
		Config cfg = configService.cargaConfig(1L);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String nombre = "";
		
		nombre = cfg.getDirectorioOrdenes() + "orden_pago_" + clienteId + sdf.format(new java.util.Date()) + ".pdf";
		return nombre;
	}

	@Override
	public void eliminaOrden(Long ordenpagoId) throws Exception {
		OrdenPago op = busca(ordenpagoId);
		if (op == null)
			throw new Exception("No existe la orden de pago");

		if (op.getFechaPago() != null)
			throw new Exception("Ya se ha registrado el pago de la orden de pago #" + ordenpagoId + " ya no se puede eliminar.");
		
		cfdiService.quitaOrdenpago(ordenpagoId);
		dao.deleteById(ordenpagoId);
	}

	@Override
	public void registraPago(Long ordenpagoId, Date fecha, String forma) throws Exception {
		OrdenPago op = busca(ordenpagoId);
		if (op == null)
			throw new Exception("No existe la orden de pago #" + ordenpagoId);
		
		if (op.getFechaPago() != null)
			throw new Exception("Ya se ha registrado el pago de la orden de pago #" + ordenpagoId + " ya no se puede registrar un nuevo pago.");
		
		op.setFechaPago(fecha);
		op.setFormaPago(forma);
		cfdiService.registraPago(ordenpagoId);
		System.out.println(op.getFormaPago());
		dao.save(op);			
	}

	@Override
	public List<OrdenPago> porEnviar() {
		return dao.findByEnviada(false);
	}

	@Override
	public void registraEnvio(OrdenPago op) {
		dao.save(op);
	}

}

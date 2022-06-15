package com.tameme.cobranza.common.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tameme.cobranza.common.entity.Cfdi;
import com.tameme.cobranza.common.entity.Cliente;
import com.tameme.cobranza.common.entity.Config;
import com.tameme.cobranza.common.entity.SatResponse;
import com.tameme.cobranza.common.entity.Subcliente;
import com.tameme.cobranza.common.service.interfaz.IArchivoService;
import com.tameme.cobranza.common.service.interfaz.ICargaService;
import com.tameme.cobranza.common.service.interfaz.IClienteService;
import com.tameme.cobranza.common.service.interfaz.IConfigService;
import com.tameme.cobranza.common.service.interfaz.IEstatusService;
import com.tameme.cobranza.common.service.interfaz.ISubclienteService;

@Service
public class CargaService implements ICargaService {

	private static final Logger logger = LoggerFactory.getLogger(CargaService.class);
	
	public static final String CFDI_RFC = "RFC";
	public static final String CFDI_RAZON_SOCIAL = "RAZON_SOCIAL";
	public static final String CFDI_TOTAL = "TOTAL"; 
	public static final String CFDI_SUBTOTAL = "SUBTOTAL";
	public static final String CFDI_UUID = "UUID";
	public static final String CFDI_TIMBRADO = "FECHA_TIMBRADO";
	public static final String CFDI_SERIE = "SERIE";
	public static final String CFDI_FOLIO = "FOLIO";
	public static final String CFDI_SUBCLIENTE = "SUBCLIENTE";
	public static final String CFDI_RETENCION_IVA = "RETENCION_IVA";
	public static final String CFDI_RETENCION_TRANSPORTE = "RETENCION_TRANSPORTE";
	
	@Autowired
	IConfigService configService;
	
	@Autowired
	IArchivoService archivoService;
	
	@Autowired
	IClienteService cteService;
	
	@Autowired
	ISubclienteService subcteService;
	
	@Autowired
	IEstatusService estatusService;

	@Override
	public Long extrae(String folder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Config config = configService.cargaConfig(1L);

		File[] archivos = archivoService.buscaXmls(config.getDirectorioCarga() + folder.replace("-", "/") + "/");

		Long contador = 0L;
		
		for(File f: archivos) {
			try {
				File pdf = archivoService.buscaPdf(f);
				Cfdi cfdi = new Cfdi();
				Map<String, String> dxml = archivoService.extraeDatosDelXml(f);
				cfdi.setTotal(Double.valueOf(dxml.get(CargaService.CFDI_TOTAL)));
				cfdi.setSubtotal(Double.valueOf(dxml.get(CargaService.CFDI_SUBTOTAL)));
				cfdi.setUuid(dxml.get(CargaService.CFDI_UUID));
				cfdi.setFechaTimbrado(sdf.parse(dxml.get(CargaService.CFDI_TIMBRADO)));
				cfdi.setArchivo(f.getAbsolutePath());
				cfdi.setSerie(dxml.get(CargaService.CFDI_SERIE));
				cfdi.setFolio(dxml.get(CargaService.CFDI_FOLIO));
				cfdi.setPagado(false);

				Map<String, String> dpdf = archivoService.extraeDatosDelPdf(pdf);
				Subcliente subcte = subcteService.buscar(dpdf.get(CargaService.CFDI_SUBCLIENTE));
				if (subcte == null) {
					subcte = new Subcliente();
					subcte.setNombre(dpdf.get(CargaService.CFDI_SUBCLIENTE));
					subcte.setCfdis(new LinkedHashSet<>());
				}
				cfdi.setRetencionIva(obtenMonto(dpdf.get(CargaService.CFDI_RETENCION_IVA)));
				cfdi.setRetencionTransporte(obtenMonto(dpdf.get(CargaService.CFDI_RETENCION_TRANSPORTE)));
				subcte.getCfdis().add(cfdi);
				cfdi.setSubcte(subcte);
				
				Cliente cte = cteService.buscaporRfc(dxml.get(CargaService.CFDI_RFC));
				if (cte == null) {
					cte = new Cliente();
					cte.setRfc(dxml.get(CargaService.CFDI_RFC));
					cte.setRazonSocial(dxml.get(CargaService.CFDI_RAZON_SOCIAL));
					cte.setSubclientes(new LinkedHashSet<>());
				}
				cte.getSubclientes().add(subcte);
				subcte.setCliente(cte);
				SatResponse edo = estatusService.estado(cfdi);
				cfdi.setCodigo(edo.getCodigoEstatus());
				cfdi.setEstatus(edo.getEstado());
				cfdi.setCancelable(edo.getEsCancelable());
				cfdi.setEstatusCancelacion(edo.getEstatusCancelacion());
				cteService.guardar(cte);
				
				contador++;
				
				logger.info("Se cargo correctamente el comprobante " + f.getName());
			} catch(Exception e) {
				logger.info("Error al procesar XML " + f.getName(), e);
			}
		}
		return contador;
	}
	
	private Double obtenMonto(String s) {
		Double monto = 0d;
		if (s != null)
			monto = Double.parseDouble(s.trim());
		return monto;
	}

}

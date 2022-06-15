package com.tameme.cobranza.controller.front;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tameme.cobranza.common.entity.OrdenPago;
import com.tameme.cobranza.common.entity.Reporte;
import com.tameme.cobranza.common.entity.view.RespuestaOrdenPago;
import com.tameme.cobranza.common.service.interfaz.ICorreoService;
import com.tameme.cobranza.common.service.interfaz.IOrdenPagoService;
import com.tameme.cobranza.common.service.interfaz.IReporteService;


@RestController
public class OrdenPagoController {
	
	@Autowired
	IOrdenPagoService opService;
	
	@Autowired
	IReporteService repService;
	
	@Autowired
	ICorreoService correoService;
	
	@GetMapping("/user/ordenpago/carga")
	public List<OrdenPago> carga() {
		return opService.carga();
	}

	@GetMapping("/user/ordenpago/detalle/{ordenpagoId}")
	public ModelAndView detalle(@PathVariable("ordenpagoId")Long ordenpagoId) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("ordenPago", opService.busca(ordenpagoId));
		mav.setViewName("user/ordenpago_detalle");
		return mav;
	}
	
	@GetMapping("/user/ordenpago/elimina/{ordenpagoId}")
	public void elimina(@PathVariable("ordenpagoId")Long ordenpagoId) throws Exception {
		opService.eliminaOrden(ordenpagoId);
	}
	
	@GetMapping("/user/ordenpago/confirma/{ordenpagoId}")
	public void confirma(@PathVariable("ordenpagoId")Long ordenpagoId, @RequestParam String fechaPago, @RequestParam String formaPago) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		opService.registraPago(ordenpagoId, sdf.parse(fechaPago), formaPago);
	}
	
	@GetMapping("/user/ordenpago/genera/{clienteId}/{cfdis}")
	public RespuestaOrdenPago genera(@AuthenticationPrincipal User usr, 
			@PathVariable("clienteId")Long clienteId, @PathVariable("cfdis")List<Long> cfdis) {
		
		RespuestaOrdenPago rop = opService.genera(clienteId, cfdis, usr.getUsername());
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("ordenPago", rop.getOrdenpago().getOrdenpagoId());
		
		InputStreamResource streamResource = null;
		Reporte rep = null;
		try {			
			repService.guardar("orden_pago", params, rop.getOrdenpago().getArchivo());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rop;
	}
}

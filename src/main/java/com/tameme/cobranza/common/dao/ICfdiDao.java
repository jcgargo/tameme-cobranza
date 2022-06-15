package com.tameme.cobranza.common.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tameme.cobranza.common.entity.Cfdi;

public interface ICfdiDao extends CrudRepository<Cfdi, Long> {
	
	List<Cfdi> findBySerieAndFolio(String serie, String folio);
	
	@Query(nativeQuery = true, value = "select cfdi_id, a.subcliente_id, serie, folio, fecha_timbrado, total, "
			+ "archivo, cancelable, codigo, estatus_cancelacion, uuid, ordenpago_id, estatus, pagado, "
			+ "subtotal, retencion_iva, retencion_transporte "
			+ "from cfdis a inner join subclientes b on a.subcliente_id = b.subcliente_id "
			+ "where a.estatus = 'Vigente' and a.ordenpago_id is null and b.cliente_id = ?")
	List<Cfdi> porpagar(Long clienteId);
	
	@Transactional
    @Modifying
	@Query(nativeQuery = true, value = "update cfdis set ordenpago_id = null where ordenpago_id = ?")
	void quitaOrdenpago(Long ordenpagoId);	
	
	@Transactional
    @Modifying
	@Query(nativeQuery = true, value = "update cfdis set pagado = true where ordenpago_id = ?")
	void registraPago(Long ordenpagoId);	
}

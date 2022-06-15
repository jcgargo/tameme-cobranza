package com.tameme.cobranza.common.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.tameme.cobranza.common.entity.OrdenPago;

public interface IOrdenPagoDao extends CrudRepository<OrdenPago, Long> {
	
	List<OrdenPago> findByEnviada(Boolean enviada);

}

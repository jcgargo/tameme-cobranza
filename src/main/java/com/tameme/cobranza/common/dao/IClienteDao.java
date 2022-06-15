package com.tameme.cobranza.common.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tameme.cobranza.common.entity.Cliente;
import com.tameme.cobranza.common.entity.view.IDatoCombo;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

	Optional<Cliente> findByRfc(String rfc);
	
	@Query(nativeQuery = true, value = "select cliente_id value, razon_social name from clientes")
	List<IDatoCombo> filtroCliente();
}

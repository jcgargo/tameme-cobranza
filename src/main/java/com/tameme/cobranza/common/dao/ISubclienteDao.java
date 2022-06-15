package com.tameme.cobranza.common.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tameme.cobranza.common.entity.Subcliente;

public interface ISubclienteDao extends CrudRepository<Subcliente, Long> {

	Optional<Subcliente> findByNombre(String rfc);

}

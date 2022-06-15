package com.tameme.cobranza.common.dao;

import org.springframework.data.repository.CrudRepository;

import com.tameme.cobranza.common.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, String> {

}

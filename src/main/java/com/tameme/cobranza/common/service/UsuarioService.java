package com.tameme.cobranza.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tameme.cobranza.common.dao.IUsuarioDao;
import com.tameme.cobranza.common.entity.Usuario;
import com.tameme.cobranza.common.service.interfaz.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private IUsuarioDao dao;

	@Override
	public void guarda(Usuario usr) {
		dao.save(usr);
	}
	
}

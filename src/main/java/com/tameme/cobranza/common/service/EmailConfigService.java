package com.tameme.cobranza.common.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tameme.cobranza.common.dao.IEmailConfigDao;
import com.tameme.cobranza.common.entity.EmailConfig;
import com.tameme.cobranza.common.service.interfaz.IEmailConfigService;

@Service
public class EmailConfigService implements IEmailConfigService {
	
	@Autowired
	IEmailConfigDao dao;

	@Override
	public void guarda(EmailConfig config) {
		dao.save(config);
	}

	@Override
	public EmailConfig busca() {
		Optional<EmailConfig> email = dao.findById(1L);
		return email.isPresent() ? email.get() : null;
	}

}

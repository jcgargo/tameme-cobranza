package com.tameme.cobranza.common.dao;

import org.springframework.data.repository.CrudRepository;

import com.tameme.cobranza.common.entity.EmailConfig;

public interface IEmailConfigDao extends CrudRepository<EmailConfig, Long> {

}

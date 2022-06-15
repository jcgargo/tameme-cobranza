package com.tameme.cobranza.common.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tameme.cobranza.common.dao.IConfigDao;
import com.tameme.cobranza.common.entity.Config;
import com.tameme.cobranza.common.service.interfaz.IConfigService;

@Service
public class ConfigService implements IConfigService {
	
	@Autowired
	private IConfigDao configDao;

	public List<Config> getConfig() {
		return (List<Config>)configDao.findAll();
	}

	public void guardaConfig(Config dto) {
		configDao.save(dto);
	}

	public Config cargaConfig(long id) {
		return configDao.findById(id).get();
	}
	
	

}

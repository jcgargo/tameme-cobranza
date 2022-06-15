package com.tameme.cobranza.common.service.interfaz;

import java.util.List;

import com.tameme.cobranza.common.entity.Config;

public interface IConfigService {
	
	public List<Config> getConfig();
	
	public void guardaConfig(Config dto);
	
	public Config cargaConfig(long id);

}

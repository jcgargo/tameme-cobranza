package com.tameme.cobranza.common.service.interfaz;

import com.tameme.cobranza.common.entity.EmailConfig;

public interface IEmailConfigService {
	
	void guarda(EmailConfig config);
	
	EmailConfig busca();

}

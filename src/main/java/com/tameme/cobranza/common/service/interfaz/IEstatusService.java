package com.tameme.cobranza.common.service.interfaz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tameme.cobranza.common.entity.Cfdi;
import com.tameme.cobranza.common.entity.SatResponse;

public interface IEstatusService {

	public SatResponse estado(Cfdi cfdi) throws JsonMappingException, JsonProcessingException;
}

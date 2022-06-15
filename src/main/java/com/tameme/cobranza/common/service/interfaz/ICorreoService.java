package com.tameme.cobranza.common.service.interfaz;

import java.util.List;

import javax.mail.MessagingException;

public interface ICorreoService {
	
	public void enviar(String destinatario, List<String> archivos) throws MessagingException;

}

package com.tameme.cobranza.common.service.interfaz;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface IArchivoService {
	
	public File[] buscaXmls(String directorio);
	public File buscaPdf(File xmlFile);
	public Map<String, String> extraeDatosDelXml(File xml)  throws IOException;
	public Map<String, String> extraeDatosDelPdf(File pdf) throws IOException;
}

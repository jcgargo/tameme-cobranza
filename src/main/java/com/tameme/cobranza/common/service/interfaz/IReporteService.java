package com.tameme.cobranza.common.service.interfaz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.tameme.cobranza.common.entity.Reporte;

import net.sf.jasperreports.engine.JRException;

public interface IReporteService {
	
	public Reporte obtener(String nombreReporte, Map<String, Object> params) throws JRException, IOException, SQLException;

	public void guardar(String nombreReporte, Map<String, Object> params, String archivo) throws FileNotFoundException, IOException, JRException, SQLException ;
}

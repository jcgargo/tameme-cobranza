package com.tameme.cobranza.common.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tameme.cobranza.common.config.JasperReportManager;
import com.tameme.cobranza.common.entity.Reporte;
import com.tameme.cobranza.common.service.interfaz.IReporteService;

import net.sf.jasperreports.engine.JRException;

@Service
public class ReporteService implements IReporteService {

	@Autowired
	private JasperReportManager reportManager;

	@Autowired
	private DataSource dataSource;

	@Override
	public Reporte obtener(String nombreReporte, Map<String, Object> params) throws JRException, IOException, SQLException {
		Reporte rep = new Reporte();
		String extension = ".pdf";
		rep.setFileName(nombreReporte + extension);

		ByteArrayOutputStream stream = reportManager.export(nombreReporte, JasperReportManager.RPT_PDF, params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		rep.setStream(new ByteArrayInputStream(bs));
		rep.setOutput(stream);
		rep.setLength(bs.length);

		return rep;
	}

	@Override
	public void guardar(String nombreReporte, Map<String, Object> params, String archivo) throws FileNotFoundException, IOException, JRException, SQLException {
		Reporte rep = new Reporte();
		String extension = ".pdf";
		rep.setFileName(nombreReporte + extension);

		ByteArrayOutputStream stream = reportManager.export(nombreReporte, JasperReportManager.RPT_PDF, params,
				dataSource.getConnection());

		try(OutputStream outputStream = new FileOutputStream(archivo)) {
		    stream.writeTo(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

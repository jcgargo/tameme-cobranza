package com.tameme.cobranza.common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.tameme.cobranza.common.service.interfaz.IArchivoService;

@Service
public class ArchivoService implements IArchivoService {

	private static final String ATTR_TOTAL = "Total";
	private static final String ATTR_SUBTOTAL = "SubTotal";
	private static final String ATTR_SERIE = "Serie";
	private static final String ATTR_FOLIO = "Folio";
	private static final String NODO_RECEPTOR = "Receptor";
	private static final String ATTR_RFC = "Rfc";
	private static final String ATTR_NOMBRE = "Nombre";
	private static final String NODO_TIMBRE = "TimbreFiscalDigital";
	private static final String ATTR_UUID = "UUID";
	private static final String ATTR_TIMBRADO = "FechaTimbrado";
	
	private static final String LBL_SUBCLIENTE = "Sub-cliente:";
	private static final String LBL_RETENCION_IVA = "002 IVA 16%: ";
	private static final String LBL_RETENCION_TRANSPORTE = "002 RET IVA 4%: ";

	@Override
	public File[] buscaXmls(String directorio) {
		File dir = new File(directorio);
		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".xml");
			}
		});

		return files;
	}

	@Override
	public File buscaPdf(File xmlFile) {
		File pdfFile = new File(xmlFile.getPath().replace(".xml", ".pdf").replace(".XML", ".pdf"));
		if (pdfFile.exists())
			return pdfFile;

		return null;
	}

	@Override
	public Map<String, String> extraeDatosDelXml(File xml) throws IOException {
		Map<String, String> lista = new LinkedHashMap<>();
		String dato;

		XmlMapper mapper = new XmlMapper();
		JsonNode node = mapper.readTree(xml);

		dato = node.findValue(ArchivoService.ATTR_TOTAL).asText();
		lista.put(CargaService.CFDI_TOTAL, dato);

		dato = node.findValue(ArchivoService.ATTR_SUBTOTAL).asText();
		lista.put(CargaService.CFDI_SUBTOTAL, dato);

		dato = node.findValue(ArchivoService.ATTR_SERIE).asText();
		lista.put(CargaService.CFDI_SERIE, dato);

		dato = node.findValue(ArchivoService.ATTR_FOLIO).asText();
		lista.put(CargaService.CFDI_FOLIO, dato);

		dato = node.findValue(ArchivoService.NODO_RECEPTOR).findValue(ArchivoService.ATTR_RFC).asText();
		lista.put(CargaService.CFDI_RFC, dato);

		dato = node.findValue(ArchivoService.NODO_RECEPTOR).findValue(ArchivoService.ATTR_NOMBRE).asText();
		lista.put(CargaService.CFDI_RAZON_SOCIAL, dato);

		dato = node.findValue(ArchivoService.NODO_TIMBRE).findValue(ArchivoService.ATTR_UUID).asText();
		lista.put(CargaService.CFDI_UUID, dato);

		dato = node.findValue(ArchivoService.NODO_TIMBRE).findValue(ArchivoService.ATTR_TIMBRADO).asText();
		lista.put(CargaService.CFDI_TIMBRADO, dato.substring(0, 10));

		return lista;
	}

	@Override
	public Map<String, String> extraeDatosDelPdf(File pdf) throws IOException {
		PdfReader reader;
		String actual = "";
		String anterior = "";
		Map<String, String> lista = new LinkedHashMap<>();

		FileInputStream isr = new FileInputStream(pdf);
		reader = new PdfReader(isr);

		// pageNumber = 1
		String text = PdfTextExtractor.getTextFromPage(reader, 1);
		String[] lineas = text.split("\n");
		reader.close();
		isr.close();

		for (String s : lineas) {
			anterior = actual;
			actual = s;
			if (actual.equals(ArchivoService.LBL_SUBCLIENTE)) {
				lista.put(CargaService.CFDI_SUBCLIENTE, anterior);
			}
			if (actual.startsWith(ArchivoService.LBL_RETENCION_IVA)) {
				String iva = actual.replace(ArchivoService.LBL_RETENCION_IVA, "").replace(",", "");
				lista.put(CargaService.CFDI_RETENCION_IVA, iva);
			}
			if (actual.startsWith(ArchivoService.LBL_RETENCION_TRANSPORTE)) {
				String transporte = actual.replace(ArchivoService.LBL_RETENCION_TRANSPORTE, "").replace(",", "");
				lista.put(CargaService.CFDI_RETENCION_TRANSPORTE, transporte);
			}
		}
		return lista;

	}

}

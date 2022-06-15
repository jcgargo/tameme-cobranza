package com.tameme.cobranza.common.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Reporte {

	private String fileName;
	private ByteArrayOutputStream output;
	private ByteArrayInputStream stream;
	private int length;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public ByteArrayInputStream getStream() {
		return stream;
	}
	public void setStream(ByteArrayInputStream stream) {
		this.stream = stream;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public ByteArrayOutputStream getOutput() {
		return output;
	}
	public void setOutput(ByteArrayOutputStream output) {
		this.output = output;
	}
	
}

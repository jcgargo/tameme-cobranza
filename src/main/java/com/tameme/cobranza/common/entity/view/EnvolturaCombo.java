package com.tameme.cobranza.common.entity.view;

import java.util.List;

public class EnvolturaCombo<T> {
	
	private String success = "true";
	private List<T> results;
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}

}

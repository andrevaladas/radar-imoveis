package com.chronosystems.entity.enumeration;

public enum TipoLocalizacao {
	A("Aproximada"), E("Exata");

	private String description;

	private TipoLocalizacao(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
package com.chronosystems.entity.enumeration;

public enum TipoLocalizacao implements BaseEnum {
	A("Aproximada"), E("Exata");

	private String description;

	private TipoLocalizacao(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return this.description;
	}
}
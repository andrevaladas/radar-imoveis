package com.chronosystems.entity.enumeration;


public enum Estado implements BaseEnum {
	RS("Rio Grande do Sul"), SC("Santa Catarina");

	private String description;

	private Estado(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
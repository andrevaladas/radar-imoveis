package com.chronosystems.entity.enumeration;

public enum Estado {
	RS("RS"), SC("SC");

	private String description;

	private Estado(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
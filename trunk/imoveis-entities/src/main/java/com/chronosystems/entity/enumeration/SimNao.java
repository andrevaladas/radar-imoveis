package com.chronosystems.entity.enumeration;

public enum SimNao {
	S("Sim"), N("N�o");

	private String description;

	private SimNao(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
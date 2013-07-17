package com.chronosystems.entity.enumeration;

public enum TipoImovel {
	CA("Casa"), AP("Apartamento"), CO("Comercial"), TE("Terreno"), NA("Desconhecido");

	private String description;

	private TipoImovel(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
	public static TipoImovel getEnum(final String description)
    {
		final TipoImovel[] enums = TipoImovel.values();
		for (TipoImovel tipoImovel : enums) {
			if(tipoImovel.getDescription().toLowerCase().startsWith(description.toLowerCase().substring(0, 3)))
            {
                return tipoImovel;
            }
		}

        System.out.println("| WARN: TipoImovel n�o encontrado para a descri��o: " + description);
        return NA;
    }
}
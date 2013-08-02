package com.chronosystems.entity.enumeration;

public enum TipoOperacao implements BaseEnum {
	CO("Compra"), VE("Venda"), AL("Aluguel"), NA("Desconhecido");

	private String description;

	private TipoOperacao(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public static TipoOperacao getEnum(final String description)
    {
		final TipoOperacao[] enums = TipoOperacao.values();
		for (TipoOperacao tipo : enums) {
			if(tipo.getDescription().toLowerCase().startsWith(description.toLowerCase().substring(0, 3)))
            {
                return tipo;
            }
		}

        System.out.println("| WARN: TipoOperacao não encontrado para a descrição: " + description);
        return NA;
    }
}
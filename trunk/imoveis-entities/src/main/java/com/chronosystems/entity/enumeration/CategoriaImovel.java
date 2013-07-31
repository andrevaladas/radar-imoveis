package com.chronosystems.entity.enumeration;
/**
 * @author Andre Valadas
 */
public enum CategoriaImovel implements BaseEnum {
	NO("Novo"), US("Usado"), LA("Lançamento"), NA("Desconhecido");

	private String description;
	private CategoriaImovel(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static CategoriaImovel getEnum(final String description)
    {
		final CategoriaImovel[] enums = CategoriaImovel.values();
		for (CategoriaImovel categoriaImovel : enums) {
			if(categoriaImovel.getDescription().toLowerCase().startsWith(description.toLowerCase().substring(0, 3)))
            {
                return categoriaImovel;
            }
		}

		System.out.println("| WARN: CategoriaImovel não encontrado para a descrição: " + description);
        return NA;
    }
}
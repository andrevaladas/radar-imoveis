/**
 * 
 */
package com.chronosystems.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author André Valadas
 */
public class ListImovelDTO implements Serializable {

	private static final long serialVersionUID = -5606270245084205451L;
	
	private final Map<LocalizacaoDTO, List<ImovelDTO>> listImovel = new HashMap<>(); 

	public void addImovel(final ImovelDTO imovel) {
		final LocalizacaoDTO localizacao = imovel.getLocalizacao();
		List<ImovelDTO> list = listImovel.get(localizacao);
		if (list == null) {
			list = new ArrayList<ImovelDTO>();
		}
		list.add(imovel);
		listImovel.put(localizacao, list);	
	}

	public Map<LocalizacaoDTO, List<ImovelDTO>> getListImovel() {
		return listImovel;
	}
}
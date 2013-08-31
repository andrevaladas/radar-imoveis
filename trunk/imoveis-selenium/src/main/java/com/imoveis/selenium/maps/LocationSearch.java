package com.imoveis.selenium.maps;

import com.chronosystems.entity.Imovel;

/**
 * Busca via Selenium as coordendas em sites na web
 * 
 * @author André Valadas
 *
 */
public interface LocationSearch {

	void initialize();

	void configureLocation(Imovel imovel);

	void release();
}

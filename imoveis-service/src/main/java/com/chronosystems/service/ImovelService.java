package com.chronosystems.service;

import java.util.List;

import com.chronosystems.entity.Imovel;

public interface ImovelService {

	void save(Imovel entity);
	
	void delete(Imovel entity);
	
	Imovel findById(Long id);
	
	List<Imovel> findAll();
	
	List<Imovel> findByQuery(String query);

	Imovel findByCodigoAnuncio(String codigoAnuncio);

}

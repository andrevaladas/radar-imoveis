package com.chronosystems.dao;

import java.util.List;

import com.chronosystems.entity.Imovel;

public interface ImovelDAO {

	void save(Imovel entity);
	
	void delete(Imovel entity);
	
	Imovel findById(Long id);
	
	List<Imovel> findAll();
	
	List<Imovel> findByQuery(String query);
	
	Imovel findByCodigoAnuncio(String codigoAnuncio);
}

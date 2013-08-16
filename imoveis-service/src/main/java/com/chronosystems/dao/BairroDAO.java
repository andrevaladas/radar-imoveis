package com.chronosystems.dao;

import java.util.List;

import com.chronosystems.entity.Bairro;
import com.chronosystems.entity.Cidade;

public interface BairroDAO {

	void save(Bairro entity);
	
	void delete(Bairro entity);
	
	Bairro findById(Long id);

	List<Bairro> findAll();

	List<Bairro> findByCidade(Cidade cidade);

	Bairro find(Cidade cidade, String descricao);
}

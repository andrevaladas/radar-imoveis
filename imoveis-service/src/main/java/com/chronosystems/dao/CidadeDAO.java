package com.chronosystems.dao;

import java.util.List;

import com.chronosystems.entity.Cidade;
import com.chronosystems.entity.enumeration.Estado;

public interface CidadeDAO {

	void save(Cidade entity);
	
	void delete(Cidade entity);
	
	Cidade findById(Long id);

	List<Cidade> findAll();

	List<Cidade> findByEstado(Estado estado);

	Cidade find(Estado estado, String descricao);
}

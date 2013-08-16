package com.chronosystems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chronosystems.dao.BairroDAO;
import com.chronosystems.entity.Bairro;
import com.chronosystems.entity.Cidade;
import com.chronosystems.service.BairroService;

@Service
@Transactional
public class BairroServiceImpl implements BairroService {

	@Autowired
	private BairroDAO bairroDAO;

	@Override
	public void save(final Bairro entity) {
		bairroDAO.save(entity);
	}

	@Override
	public void delete(Bairro entity) {
		bairroDAO.delete(entity);
	}

	@Override
	public Bairro findById(Long id) {
		return bairroDAO.findById(id);
	}
	
	@Override
	public List<Bairro> findAll() {
		return bairroDAO.findAll();
	}

	@Override
	public List<Bairro> findByCidade(Cidade cidade) {
		return bairroDAO.findByCidade(cidade);
	}
	
	@Override
	public Bairro find(Cidade cidade, String descricao) {
		return bairroDAO.find(cidade, descricao);
	}
}
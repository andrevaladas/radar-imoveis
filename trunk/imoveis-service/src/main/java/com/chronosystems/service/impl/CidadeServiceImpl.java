package com.chronosystems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chronosystems.dao.CidadeDAO;
import com.chronosystems.entity.Cidade;
import com.chronosystems.entity.enumeration.Estado;
import com.chronosystems.service.CidadeService;

@Service
@Transactional
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private CidadeDAO cidadeDAO;

	@Override
	public void save(final Cidade entity) {
		cidadeDAO.save(entity);
	}

	@Override
	public void delete(Cidade entity) {
		cidadeDAO.delete(entity);
	}

	@Override
	public Cidade findById(Long id) {
		return cidadeDAO.findById(id);
	}
	
	@Override
	public List<Cidade> findAll() {
		return cidadeDAO.findAll();
	}

	@Override
	public List<Cidade> findByEstado(Estado estado) {
		return cidadeDAO.findByEstado(estado);
	}
	
	@Override
	public Cidade find(Estado estado, String descricao) {
		return cidadeDAO.find(estado, descricao);
	}
}
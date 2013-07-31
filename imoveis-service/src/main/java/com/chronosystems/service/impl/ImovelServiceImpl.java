package com.chronosystems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chronosystems.dao.ImovelDAO;
import com.chronosystems.entity.Imovel;
import com.chronosystems.service.ImovelService;

@Service
@Transactional
public class ImovelServiceImpl implements ImovelService {

	@Autowired
	private ImovelDAO imovelDAO;

	@Override
	public void save(final Imovel entity) {
		imovelDAO.save(entity);
	}

	@Override
	public void delete(Imovel entity) {
		imovelDAO.delete(entity);
	}

	@Override
	public Imovel findById(Long id) {
		return imovelDAO.findById(id);
	}
	
	@Override
	public List<Imovel> findAll() {
		return imovelDAO.findAll();
	}

	public List<Imovel> findByQuery(String query) {
		return imovelDAO.findByQuery(query);
	}
	
	@Override
	public Imovel findByCodigoAnuncio(String codigoAnuncio) {
		return imovelDAO.findByCodigoAnuncio(codigoAnuncio);
	}
}
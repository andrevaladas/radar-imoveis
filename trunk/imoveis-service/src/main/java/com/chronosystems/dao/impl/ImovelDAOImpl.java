package com.chronosystems.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chronosystems.dao.ImovelDAO;
import com.chronosystems.entity.Imovel;
import com.chronosystems.util.DaoSupport;

@Repository
public class ImovelDAOImpl extends DaoSupport implements ImovelDAO {

	@Override
	public void save(final Imovel entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Imovel entity) {
		getCurrentSession().delete(entity);
	}
	
	@Override
	public Imovel findById(Long id) {
		return (Imovel) getCurrentSession().get(Imovel.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Imovel> findAll() {
		return getCurrentSession().createQuery("select distinct i from Imovel i left join fetch i.imagens").list();
	}
	
	@Override
	public Imovel find(String codigoAnuncio) {
		final Query query = getCurrentSession().createQuery("select distinct i from Imovel i left join fetch i.imagens where i.codigoAnuncio = :codigoAnuncio");
		query.setParameter("codigoAnuncio", codigoAnuncio);
		return (Imovel) query.uniqueResult();
	}
}

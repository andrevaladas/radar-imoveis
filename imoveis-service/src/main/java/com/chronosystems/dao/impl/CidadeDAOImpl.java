package com.chronosystems.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chronosystems.dao.CidadeDAO;
import com.chronosystems.entity.Cidade;
import com.chronosystems.entity.enumeration.Estado;
import com.chronosystems.util.DaoSupport;

@Repository
public class CidadeDAOImpl extends DaoSupport implements CidadeDAO {

	@Override
	public void save(final Cidade entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Cidade entity) {
		getCurrentSession().delete(entity);
	}
	
	@Override
	public Cidade findById(Long id) {
		return (Cidade) getCurrentSession().get(Cidade.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> findAll() {
		return getCurrentSession().createQuery("select c from Cidade c").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cidade> findByEstado(Estado estado) {
		final Query query = getCurrentSession().createQuery("select c from Cidade c where c.estado = :estado");
		query.setParameter("estado", estado);
		return query.list();
	}

	@Override
	public Cidade find(Estado estado, String descricao) {
		final Query query = getCurrentSession().createQuery("select c from Cidade c where c.estado = :estado and c.descricao = :descricao");
		query.setParameter("estado", estado);
		query.setParameter("descricao", descricao);
		return (Cidade) query.uniqueResult();
	}
}
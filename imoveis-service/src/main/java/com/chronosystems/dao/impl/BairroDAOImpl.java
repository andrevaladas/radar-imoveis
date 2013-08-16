package com.chronosystems.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chronosystems.dao.BairroDAO;
import com.chronosystems.entity.Bairro;
import com.chronosystems.entity.Cidade;
import com.chronosystems.util.DaoSupport;

@Repository
public class BairroDAOImpl extends DaoSupport implements BairroDAO {

	@Override
	public void save(final Bairro entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(Bairro entity) {
		getCurrentSession().delete(entity);
	}
	
	@Override
	public Bairro findById(Long id) {
		return (Bairro) getCurrentSession().get(Bairro.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bairro> findAll() {
		return getCurrentSession().createQuery("select b from Bairro b").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bairro> findByCidade(Cidade cidade) {
		final Query query = getCurrentSession().createQuery("select b from Bairro b where b.cidade = :cidade");
		query.setParameter("cidade", cidade);
		return query.list();
	}

	@Override
	public Bairro find(Cidade cidade, String descricao) {
		final Query query = getCurrentSession().createQuery("select b from Bairro b where b.cidade = :cidade and b.descricao = :descricao");
		query.setParameter("cidade", cidade);
		query.setParameter("descricao", descricao);
		return (Bairro) query.uniqueResult();
	}
}
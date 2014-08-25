package org.poligraph.model.hib.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.poligraph.model.bean.Triple;
import org.poligraph.model.hib.dao.impl.iface.ITripleDAO;
import org.poligraph.model.hib.impl.TripleService;
import org.poligraph.model.impl.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository(value="tripleDAOImpl")
public class TripleDAOImpl implements ITripleDAO {
	
private static final Logger LOG = Logger.getLogger(TripleService.class);
	
	@Autowired
	// (required=false)
	private SessionFactory sessionFactory;

	@Override
	public List<Triple> getTriplesByQuery(String query) throws DaoException {
		System.out.println("get all exist triples");
		LOG.info("get all exist triples");
		List<Triple> triples = new ArrayList<Triple>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			triples = (List<Triple>) session.createQuery("from Triple").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in get all exist triples");
		}
		return triples;
	}

}

package org.poligraph.model.hib.impl;

import java.util.List;

import org.poligraph.model.bean.Triple;
import org.poligraph.model.hib.dao.impl.iface.ITripleDAO;
import org.poligraph.model.hib.iface.ITripleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="tripleService")
public class TripleService implements ITripleService {

	@Autowired
	// (required=false)
	private ITripleDAO tripleDAOImpl;

	@Transactional
	public List<Triple> getTriplesByQuery(String query) {
//		return tripleDAOImpl.getTriplesByQuery(query);
		return null;
	}

}

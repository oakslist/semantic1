package org.poligraph.model.hib.dao.impl.iface;

import java.util.List;

import org.poligraph.model.bean.Triple;
import org.poligraph.model.impl.DaoException;


public interface ITripleDAO {

	public List<Triple> getTriplesByQuery(String query) throws DaoException;

}

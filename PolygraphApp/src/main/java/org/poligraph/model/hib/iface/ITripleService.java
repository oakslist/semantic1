package org.poligraph.model.hib.iface;

import java.util.List;

import org.poligraph.model.bean.Triple;
import org.poligraph.model.impl.DaoException;

public interface ITripleService {

	public List<Triple> getTriplesByQuery(String query) throws DaoException;

}

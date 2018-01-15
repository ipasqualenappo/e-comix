package GestioneProdotti;

import java.sql.SQLException;
import java.util.Collection;

public interface SearchModel {

	public Collection<GadgetBean> doRetrieveAllSearchGadget(String search) throws SQLException;
	public Collection<ComicsBean> doRetrieveAllSearchComics(String search) throws SQLException;
}

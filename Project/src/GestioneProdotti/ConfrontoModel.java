package GestioneProdotti;

import java.sql.SQLException;
import java.util.Collection;


public interface ConfrontoModel {

	public boolean doDeleteConfrontGadget(String code) throws SQLException;
	public boolean doDeleteConfrontComics(String code) throws SQLException;

	public boolean doAddConfrontGadget(String code)throws SQLException;
	public boolean doAddConfrontComics(String code)throws SQLException;

	public Collection<GadgetBean> doRetrieveAllConfrontGadget() throws SQLException;
	public Collection<ComicsBean> doRetrieveAllConfrontComics() throws SQLException;

}

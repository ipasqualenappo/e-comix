package GestioneProdotti;

import java.sql.SQLException;
import java.util.Collection;

import GestioneProdotti.GadgetBean;

public interface GadgetModel {


	public Collection<GadgetBean> doRetrieveAll() throws SQLException;


	public Collection<GadgetBean> doRetrieveAll(String tipo,String  publisher) throws SQLException;
}
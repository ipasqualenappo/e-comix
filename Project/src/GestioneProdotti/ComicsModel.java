package GestioneProdotti;

import java.sql.SQLException;
import java.util.Collection;

import GestioneProdotti.ComicsBean;

public interface ComicsModel {

	public Collection<ComicsBean> doRetrieveAll(String genere, String publisher, String tipo) throws SQLException;

	public Collection<ComicsBean> doRetrieveAll() throws SQLException;
}
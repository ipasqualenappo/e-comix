package GestioneProdotti;

import java.sql.SQLException;

import GestioneProdotti.ComicsBean;

public interface ObjectModel {

	public ComicsBean doRetrieveByKey(String code) throws SQLException;
}

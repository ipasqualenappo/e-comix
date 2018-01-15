package GestioneProdotti;

import java.sql.SQLException;

import GestioneProdotti.GadgetBean;

public interface ObjectgModel {


	public GadgetBean doRetrieveByKey(String code) throws SQLException;
}

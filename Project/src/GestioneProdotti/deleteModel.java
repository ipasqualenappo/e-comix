package GestioneProdotti;

import java.sql.SQLException;


public interface deleteModel {

	public boolean doDeleteGadget(String code) throws SQLException;
	public boolean doDeleteComics(String code) throws SQLException;



}

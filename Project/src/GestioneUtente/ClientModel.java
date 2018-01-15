package GestioneUtente;

import java.sql.SQLException;

import GestioneUtente.ClientBean;

public interface ClientModel {
	public void doSave(ClientBean Client) throws SQLException;


	public ClientBean getClient(String email) throws SQLException;

	public void doUpdate(ClientBean Client, String email) throws SQLException;

	public String Check(String email) throws SQLException;

}
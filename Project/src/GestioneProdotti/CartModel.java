package GestioneProdotti;

import java.sql.SQLException;

public interface CartModel {

	public void Shopgadget(String gadget, String order, int quantity,int availability)throws SQLException;
	public void Shopcomics(String comic, String order, int quantity, int availability)throws SQLException;
	public void Shop(String order, String date,String email, String prezzo) throws SQLException;
	public String Getemail(String email);


}

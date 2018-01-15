package GestioneFumetteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import GestioneProdotti.CartModel;

public class CartModelDS implements CartModel {
	private static DataSource ds;
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/fumetteria");
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	public synchronized String Getemail(String email) {
		return email;
	}

	public synchronized void Shop(String order,String date, String email, String prezzo)
			throws SQLException {
		if (!(email.equals("noemail"))) {
			Connection connection5 = null;
			PreparedStatement preparedStatement5 = null;
			String insertSQL2 = "INSERT Ordine VALUES (?,?,?,'completato','effettuato',?)"; 

			try {
				connection5 = ds.getConnection();
				preparedStatement5 = connection5.prepareStatement(insertSQL2);
				preparedStatement5.setString(1, date);
				preparedStatement5.setString(2, order);
				preparedStatement5.setString(3, prezzo);
				preparedStatement5.setString(4, email);
				preparedStatement5.executeUpdate();
			} finally {
				try {
					if (preparedStatement5 != null){
						preparedStatement5.close(); 
					}
				} finally {
					if (connection5 != null ){
						connection5.close();
					}
				}
			}
			Connection connection6 = null;
			PreparedStatement preparedStatement6 = null;
			String selectSQL3 = " Select NUM_ORDINI from Utente where E_MAIL = ?"; 
			int nordini = 0;

			try {
				connection6 = ds.getConnection();
				preparedStatement6 = connection6.prepareStatement(selectSQL3);
				preparedStatement6.setString(1, email);
				ResultSet rs = preparedStatement6.executeQuery();

				if (rs.next()){
					nordini = rs.getInt("NUM_ORDINI");
					nordini = nordini +1;
				}
				Connection connection7 = null;
				PreparedStatement preparedStatement7 = null;	
				String updatesql = " Update Utente set NUM_ORDINI = ? where E_MAIL= ?"; 

				try {
					connection7 = ds.getConnection();
					preparedStatement7 = connection7.prepareStatement(updatesql);
					preparedStatement7.setInt(1, nordini);
					preparedStatement7.setString(2, email);
					preparedStatement7.executeUpdate();
				} finally {
					try {
						if (preparedStatement7 != null)
							preparedStatement7.close();
					}
					finally {
						if (connection7 != null)
							connection7.close();
					}		
				}
			} finally {
				try {
					if (preparedStatement6 != null)
						preparedStatement6.close();
				}
				finally {
					if (connection6 != null)
						connection6.close();
				}
			}
		}
	}

	public synchronized void Shopcomics(String comic, String order, int quantity, int availability)
			throws SQLException {
		Connection connection3 = null;
		PreparedStatement preparedStatement3 = null;
		String insertSQL = "INSERT Formato_da VALUES (?,?,?)";

		try {
			connection3 = ds.getConnection();
			preparedStatement3 = connection3.prepareStatement(insertSQL);
			preparedStatement3.setString(1, comic);
			preparedStatement3.setString(3, order);
			preparedStatement3.setInt(2, quantity);
			preparedStatement3.executeUpdate();
		} finally {
			try {
				if (preparedStatement3 != null){
					preparedStatement3.close(); 
				}
			} finally {
				if (connection3 != null ){
					connection3.close();
				}
			}
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int disponibilita = availability - quantity;
		String updateSQL = "Update Fumetto set DISPONIBILITA = ? where COD_FUMETTO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(2, comic);
			preparedStatement.setInt(1, disponibilita);
			preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null){
					preparedStatement.close(); 
				}
			} finally {
				if (connection != null ){
					connection.close();
				}
			}
		}
	}

	public synchronized void Shopgadget(String gadget, String order, int quantity,int availability)
			throws SQLException {
		Connection connection4 = null;
		PreparedStatement preparedStatement4 = null;
		String insertSQL = "INSERT Articolato_da VALUES (?,?,?)";

		try {
			connection4 = ds.getConnection();
			preparedStatement4 = connection4.prepareStatement(insertSQL);
			preparedStatement4.setString(1, gadget);
			preparedStatement4.setString(3, order);
			preparedStatement4.setInt(2, quantity);
			preparedStatement4.executeUpdate();
		} finally {
			try {
				if (preparedStatement4 != null){
					preparedStatement4.close(); 
				}
			} finally {
				if (connection4 != null ){
					connection4.close();
				}
			}
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int disponibilita = availability - quantity;
		String updateSQL = "Update Gadget set DISPONIBILITA = ? where COD_GADGET = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(2, gadget);
			preparedStatement.setInt(1, disponibilita);
			preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null){
					preparedStatement.close(); 
				}
			} finally {
				if (connection != null ){
					connection.close();
				}
			}
		}
	}	



}
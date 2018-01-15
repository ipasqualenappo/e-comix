package GestioneFumetteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import GestioneProdotti.ComicsBean;
import GestioneProdotti.ConfrontoModel;
import GestioneProdotti.GadgetBean;


public class ConfrontoModelDS implements ConfrontoModel {

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
	
	@Override
	public  synchronized boolean doAddConfrontGadget(String code)throws SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;

	int result = 0;
	int i = 0;

	try {
	String SQL = "Select * from Fumetto where Fumetto.CONFRONTO = true";
	
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(SQL);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			i++;
		}
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
		
	try {
		String SQL2 = "Select * from Gadget where Gadget.CONFRONTO = true";
		
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(SQL2);
			ResultSet rs2 = preparedStatement.executeQuery();

			while (rs2.next()) {
				i++;
			}
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
	String deleteSQL = "Update Gadget set Gadget.CONFRONTO = true where Gadget.COD_GADGET=?";
	
	try {
		
		if (i < 2){	
	
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteSQL);
		preparedStatement.setString(1, code);
		
	
		result = preparedStatement.executeUpdate();
		}
		
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
	return (result != 0);
}

	
	@Override
	public synchronized boolean doAddConfrontComics(String code)throws SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;

	int result = 0;
	int i = 0;
	try {
	String SQL = "Select * from Fumetto where Fumetto.CONFRONTO = true";
	
	connection = ds.getConnection();
	preparedStatement = connection.prepareStatement(SQL);
	ResultSet rs = preparedStatement.executeQuery();

	while (rs.next()) {
		i++;
	}
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
	
	try {
	String SQL2 = "Select * from Gadget where Gadget.CONFRONTO = true";
	
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(SQL2);
		ResultSet rs2 = preparedStatement.executeQuery();

		while (rs2.next()) {
			i++;
		}
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
		
		String deleteSQL = "Update Fumetto set Fumetto.CONFRONTO = true where Fumetto.COD_FUMETTO=?";

	
	try {
		if (i < 2){
		connection = ds.getConnection();
		preparedStatement = connection.prepareStatement(deleteSQL);
		preparedStatement.setString(1, code);
		
	
		result = preparedStatement.executeUpdate();
		}
			

		
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
	return (result != 0);
}

	
	

	@Override
	public synchronized boolean doDeleteConfrontComics(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "Update Fumetto set Confronto = FALSE where COD_FUMETTO= ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);
			
		
			result = preparedStatement.executeUpdate();

			
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
		return (result != 0);
	}
	
	@Override
	public synchronized boolean doDeleteConfrontGadget(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "Update Gadget set Confronto = FALSE where COD_GADGET=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);
			
		
			result = preparedStatement.executeUpdate();

			
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
		return (result != 0);
	}
	
	
	
	
	
	@Override
	public synchronized Collection<ComicsBean> doRetrieveAllConfrontComics()  throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ComicsBean> comics = new LinkedList<ComicsBean>();
		String selectSQL = " Select * from Fumetto where Confronto = TRUE "; 

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//preparedStatement.setString(1, code);//

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ComicsBean bean = new ComicsBean();

				bean.settitle(rs.getString("TITOLO"));
				bean.setimage(rs.getString("IMMAGINI"));
				bean.setcode(rs.getString("COD_FUMETTO"));
				bean.setgender(rs.getString("GENERE"));
				bean.setnumber(rs.getString("NUMERO"));
				bean.setplot(rs.getString("TRAMA"));
				bean.setprice(rs.getFloat("COSTO"));
				bean.setavailability(rs.getInt("DISPONIBILITA"));
				comics.add(bean);
			}
		}
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}
			finally {
				if (connection != null)
					connection.close();
			}
		}
		return comics;
	}
	
	
	@Override
	public synchronized Collection<GadgetBean> doRetrieveAllConfrontGadget() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<GadgetBean> gadget = new LinkedList<GadgetBean>();
		String selectSQL = " Select * from Gadget where Confronto = TRUE "; 
	
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//preparedStatement.setString(1, code);//
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
					GadgetBean bean = new GadgetBean();
					
						bean.setname(rs.getString("NOME"));
						bean.setimage(rs.getString("IMMAGINI"));

						bean.settype(rs.getString("TIPO"));
						bean.setdescription(rs.getString("DESCRIZIONE"));
						bean.setcode(rs.getString("COD_GADGET"));
						bean.setprice(rs.getFloat("COSTO"));
						bean.setavailability(rs.getInt("DISPONIBILITA"));
						gadget.add(bean);			
				}
			}
		
	finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		}
		finally {
			if (connection != null)
				connection.close();
		}
	}
	return gadget;
	
}
}

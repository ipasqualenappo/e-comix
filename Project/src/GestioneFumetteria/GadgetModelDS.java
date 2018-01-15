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

import GestioneProdotti.GadgetBean;
import GestioneProdotti.GadgetModel;

public class GadgetModelDS implements GadgetModel {
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/fumetteria");
		}
		catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "Gadget";

	@Override
	public synchronized Collection<GadgetBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<GadgetBean> gadget = new LinkedList<GadgetBean>();
		String selectSQL = "SELECT * FROM " + GadgetModelDS.TABLE_NAME;
	
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
					GadgetBean bean = new GadgetBean();
					
						bean.setname(rs.getString("NOME"));
						bean.settype(rs.getString("TIPO"));
						bean.setprice(rs.getFloat("COSTO"));
						bean.setcode(rs.getString("COD_GADGET"));
						bean.setimage(rs.getString("IMMAGINI"));
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
	
	@Override
	public synchronized Collection<GadgetBean> doRetrieveAll(String tipo, String publisher) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<GadgetBean> gadget = new LinkedList<GadgetBean>();
		String selectSQL = "SELECT * FROM " + GadgetModelDS.TABLE_NAME;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
					GadgetBean bean = new GadgetBean();
					
					boolean cerca = true;
				
					if (publisher != null && tipo !=null){
					cerca = false;
					String stringa = rs.getString("TIPO").toLowerCase();
					String sottostringa = tipo.toLowerCase();

					if (stringa.indexOf(sottostringa) != -1) {
						cerca = true;
					}
					
					String stringa2 = rs.getString("PUBLISHER").toLowerCase();
					String sottostringa2 = publisher.toLowerCase();

					if (stringa2.indexOf(sottostringa2) != -1) {
						cerca = true;
					}
					
					}
					if(cerca == true) {
						bean.setname(rs.getString("NOME"));
						bean.settype(rs.getString("TIPO"));
						bean.setcode(rs.getString("COD_GADGET"));
						bean.setimage(rs.getString("IMMAGINI"));
						gadget.add(bean);
					}
					
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
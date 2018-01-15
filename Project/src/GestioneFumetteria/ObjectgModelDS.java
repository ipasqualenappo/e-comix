package GestioneFumetteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import GestioneProdotti.GadgetBean;
import GestioneProdotti.ObjectgModel;

public class ObjectgModelDS implements ObjectgModel {
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
	public synchronized GadgetBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		GadgetBean bean = new GadgetBean();
		String selectSQL = "SELECT * FROM " + ObjectgModelDS.TABLE_NAME + " WHERE COD_GADGET = ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setcode(rs.getString("COD_GADGET"));
				bean.setprice(rs.getFloat("COSTO"));
				bean.setdimension(rs.getString("DIMENSIONI"));
				bean.setweight(rs.getString("PESO"));
				bean.settype(rs.getString("TIPO"));
				bean.setpublisher(rs.getString("PUBLISHER"));
				bean.setdescription(rs.getString("DESCRIZIONE"));
				bean.setname(rs.getString("NOME"));
				bean.setimage(rs.getString("IMMAGINI"));
				bean.setavailability(rs.getInt("DISPONIBILITA"));

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
		return bean;
	}


}
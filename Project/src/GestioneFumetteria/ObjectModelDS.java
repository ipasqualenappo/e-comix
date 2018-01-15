package GestioneFumetteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import GestioneProdotti.ComicsBean;
import GestioneProdotti.ObjectModel;

public class ObjectModelDS implements ObjectModel {
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
	private static final String TABLE_NAME = "Fumetto";

	@Override
	public synchronized ComicsBean doRetrieveByKey(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ComicsBean bean = new ComicsBean();
		String selectSQL = "SELECT * FROM " + ObjectModelDS.TABLE_NAME + " WHERE COD_FUMETTO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setcode(rs.getString("COD_FUMETTO"));
				bean.setgender(rs.getString("GENERE"));
				bean.setprice(rs.getFloat("COSTO"));
				bean.setwriter(rs.getString("SCRITTORE"));
				bean.setdrawer(rs.getString("DISEGNATORE"));
				bean.setpublisher(rs.getString("PUBLISHER"));
				bean.settype(rs.getString("TIPO"));
				bean.setplot(rs.getString("TRAMA"));
				bean.settitle(rs.getString("TITOLO"));
				bean.setnumber(rs.getString("NUMERO"));
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
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
import GestioneProdotti.ComicsModel;

public class ComicsModelDS implements ComicsModel {
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
	public synchronized Collection<ComicsBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ComicsBean> comics = new LinkedList<ComicsBean>();
		String selectSQL = "SELECT * FROM " + ComicsModelDS.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ComicsBean bean = new ComicsBean();

				bean.settitle(rs.getString("TITOLO"));
				bean.setprice(rs.getFloat("COSTO"));

				bean.setcode(rs.getString("COD_FUMETTO"));
				bean.setnumber(rs.getString("NUMERO"));
				bean.setimage(rs.getString("IMMAGINI"));
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
	public synchronized Collection<ComicsBean> doRetrieveAll(String genere, String publisher, String tipo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ComicsBean> comics = new LinkedList<ComicsBean>();
		String selectSQL = "SELECT * FROM " + ComicsModelDS.TABLE_NAME;



		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

				ComicsBean bean = new ComicsBean();

				boolean cerca = true;

				if (publisher != null && tipo !=null){
					cerca = false;
					String stringa = rs.getString("GENERE").toLowerCase();
					String sottostringa = genere.toLowerCase();

					if (stringa.indexOf(sottostringa) != -1) {
						cerca = true;
					}

					String stringa2 = rs.getString("PUBLISHER").toLowerCase();
					String sottostringa2 = publisher.toLowerCase();

					if (stringa2.indexOf(sottostringa2) != -1) {
						cerca = true;
					}


					String stringa3 = rs.getString("TIPO").toLowerCase();
					String sottostringa3 = tipo.toLowerCase();

					if (stringa3.indexOf(sottostringa3) != -1) {
						cerca = true;
					}

				}
				if(cerca == true) {
					bean.settitle(rs.getString("TITOLO"));
					bean.setcode(rs.getString("COD_FUMETTO"));
					bean.setnumber(rs.getString("NUMERO"));
					bean.setimage(rs.getString("IMMAGINI"));
					comics.add(bean);
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
		return comics;
	}

}
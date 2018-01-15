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
import GestioneProdotti.GadgetBean;
import GestioneProdotti.SearchModel;


public class SearchModelDS implements SearchModel {
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
	private static final String TABLE_NAME = "Fumetto";
	private static final String TABLE_NAMEG = "Gadget";

	@Override
	public synchronized Collection<ComicsBean> doRetrieveAllSearchComics(String search) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ComicsBean> comics = new LinkedList<ComicsBean>();
		String selectSQL = "SELECT * FROM " + SearchModelDS.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ComicsBean bean = new ComicsBean();

				boolean cerca = false;
				String stringa = rs.getString("TITOLO").toLowerCase();
				String sottostringa = search.toLowerCase();

				if (stringa.indexOf(sottostringa) != -1) {
					cerca = true;
				}

				if(cerca == true) {
					bean.settitle(rs.getString("TITOLO"));
					bean.setcode(rs.getString("COD_FUMETTO"));
					bean.setnumber(rs.getString("NUMERO"));
					bean.setimage(rs.getString("IMMAGINI"));
					comics.add(bean);
				}
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return comics;
	}

	@Override
	public synchronized Collection<GadgetBean> doRetrieveAllSearchGadget(String search) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<GadgetBean> gadget = new LinkedList<GadgetBean>();
		String selectSQL = "SELECT * FROM " + SearchModelDS.TABLE_NAMEG;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				GadgetBean bean = new GadgetBean();

				boolean cerca = false;
				String stringa = rs.getString("NOME").toLowerCase();
				String sottostringa = search.toLowerCase();

				if (stringa.indexOf(sottostringa) != -1) {
					cerca = true;
				}

				if(cerca == true) {
					bean.setname(rs.getString("NOME"));
					bean.settype(rs.getString("TIPO"));
					bean.setcode(rs.getString("COD_GADGET"));
					bean.setimage(rs.getString("IMMAGINI"));
					gadget.add(bean);
				}	
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return gadget;
	}
}
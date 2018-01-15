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

import GestioneUtente.BanModel;
import GestioneUtente.ClientBean;

public class BanModelDS implements BanModel {
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

	@Override
	public synchronized Collection<ClientBean> doRetrieveAll()
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ClientBean> oggetti = new LinkedList<ClientBean>();
		String selectSQL = "SELECT * FROM Utente";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ClientBean bean = new ClientBean();
				String email = rs.getString("E_MAIL");
				if (!(email.equals("duckpro@libero.it"))) {
					bean.setName(rs.getString("NOME"));
					bean.setSurname(rs.getString("COGNOME"));
					bean.setEmail(rs.getString("E_MAIL"));
					bean.setNordini(rs.getInt("NUM_ORDINI"));
					bean.setPhone(rs.getString("CELLULARE"));
					oggetti.add(bean);
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
		return oggetti;
	}

	public boolean doDelete(String code)
			throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		String deleteSQL = "DELETE FROM  Utente where E_MAIL = ?";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, code);
			result = preparedStatement.executeUpdate();
		}
		finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close(); 
				}
			}
			finally {
				if (connection != null ){
					connection.close();
				}
			}
		}
		return (result != 0);
	}
}
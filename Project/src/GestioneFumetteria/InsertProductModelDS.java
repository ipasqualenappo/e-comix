package GestioneFumetteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import GestioneProdotti.ComicsBean;
import GestioneProdotti.GadgetBean;
import GestioneProdotti.InsertProductModel;

public class InsertProductModelDS  implements InsertProductModel {

	@SuppressWarnings("unused")
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
	}					/* */





	@Override
	public synchronized void doSaveC(ComicsBean Comic) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Fumetto"
				+ " (COD_FUMETTO, GENERE, COSTO, SCRITTORE, DISEGNATORE, PUBLISHER, TIPO, TRAMA, TITOLO, NUMERO, IMMAGINI, CONFRONTO, DISPONIBILITA)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, false,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, Comic.getcode());
			preparedStatement.setString(2, Comic.getgender());
			preparedStatement.setFloat(3, Comic.getprice());
			preparedStatement.setString(4, Comic.getwriter());
			preparedStatement.setString(5, Comic.getdrawer());
			preparedStatement.setString(6, Comic.getpublisher());
			preparedStatement.setString(7, Comic.gettype());
			preparedStatement.setString(8, Comic.getplot());
			preparedStatement.setString(9, Comic.gettitle());
			preparedStatement.setString(10, Comic.getnumber());
			preparedStatement.setString(11, Comic.getimage());
			preparedStatement.setInt(12, Comic.getavailability());



			preparedStatement.executeUpdate();
			connection.commit();
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
	}


	@Override
	public synchronized void doSaveG(GadgetBean Gadget) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO Gadget"
				+ " (COD_GADGET, TIPO, PESO, COSTO, DIMENSIONI, NOME, PUBLISHER, DESCRIZIONE, IMMAGINI, CONFRONTO, DISPONIBILITA) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, false,?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, Gadget.getcode());
			preparedStatement.setString(2, Gadget.gettype());
			preparedStatement.setString(3, Gadget.getweight());
			preparedStatement.setFloat(4, Gadget.getprice());
			preparedStatement.setString(5, Gadget.getdimension());
			preparedStatement.setString(6, Gadget.getname());
			preparedStatement.setString(7, Gadget.getpublisher());
			preparedStatement.setString(8, Gadget.getdescription());
			preparedStatement.setString(9, Gadget.getimage());

			preparedStatement.setInt(10, Gadget.getavailability());


			preparedStatement.executeUpdate();
			connection.commit();
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
	}


	@Override
	public synchronized void doUpdateC(ComicsBean Comic, String comic) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "Update Fumetto set GENERE=?, COSTO=?, SCRITTORE=?, DISEGNATORE=?, PUBLISHER=?, TIPO=?, TRAMA=?, TITOLO=?, NUMERO=?, IMMAGINI=?, DISPONIBILITA=? where COD_FUMETTO=? ";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, Comic.getgender());
			preparedStatement.setFloat(2, Comic.getprice());
			preparedStatement.setString(3, Comic.getwriter());
			preparedStatement.setString(4, Comic.getdrawer());
			preparedStatement.setString(5, Comic.getpublisher());
			preparedStatement.setString(6, Comic.gettype());
			preparedStatement.setString(7, Comic.getplot());
			preparedStatement.setString(8, Comic.gettitle());
			preparedStatement.setString(9, Comic.getnumber());
			preparedStatement.setString(10, Comic.getimage());
			preparedStatement.setInt(11, Comic.getavailability());
			preparedStatement.setString(12, comic);


			preparedStatement.executeUpdate();
			connection.commit();
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
	}


	@Override
	public synchronized void doUpdateG(GadgetBean Gadget, String codgadget) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "Update Gadget set TIPO=?, PESO=?, COSTO=?, DIMENSIONI=?, NOME=?, PUBLISHER=?, DESCRIZIONE=?, IMMAGINI=?, DISPONIBILITA=? where COD_GADGET=? ";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, Gadget.gettype());
			preparedStatement.setString(2, Gadget.getweight());
			preparedStatement.setFloat(3, Gadget.getprice());
			preparedStatement.setString(4, Gadget.getdimension());
			preparedStatement.setString(5, Gadget.getname());
			preparedStatement.setString(6, Gadget.getpublisher());
			preparedStatement.setString(7, Gadget.getdescription());
			preparedStatement.setString(8, Gadget.getimage());
			preparedStatement.setInt(9, Gadget.getavailability());
			preparedStatement.setString(10, codgadget);


			preparedStatement.executeUpdate();
			connection.commit();
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
	}
	/* */
}

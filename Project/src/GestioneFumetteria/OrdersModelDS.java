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

import GestioneOrdini.OrdersBean;
import GestioneOrdini.OrdersModel;
import GestioneProdotti.ComicsBean;
import GestioneProdotti.GadgetBean;

public class OrdersModelDS implements OrdersModel {

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
	
	public synchronized int QuantityGadget(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int quantita = 1;

		String selectSQL = "select QUANTITA from Articolato_da where Formato_da.COD_GADGET= ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			quantita = rs.getInt("QUANTITA");
		
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
	return quantita;
}
	
	
	public synchronized int QuantityComics(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int quantita = 0;

		String selectSQL = "select QUANTITA  from Formato_da where Formato_da.COD_FUMETTO= ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			quantita = rs.getInt("QUANTITA");
		
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
	return quantita;
}
	
	

	@Override
	public synchronized Collection<ComicsBean> doRetrieveAllOrderComics(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<ComicsBean> comics = new LinkedList<ComicsBean>();
		String selectSQL = "select * from Fumetto, Ordine,  Formato_da where Ordine.COD_ORDINE = Formato_da.COD_ORDINE and  Fumetto.COD_FUMETTO = Formato_da.COD_FUMETTO and Formato_da.COD_ORDINE= ?";

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ComicsBean bean = new ComicsBean();

				bean.settitle(rs.getString("TITOLO"));
				bean.setquantity(rs.getInt("QUANTITA"));
				bean.setcode(rs.getString("COD_FUMETTO"));
				bean.setimage(rs.getString("IMMAGINI"));
				bean.setgender(rs.getString("GENERE"));
				bean.setnumber(rs.getString("NUMERO"));
				bean.setprice(rs.getFloat("COSTO"));
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
	public synchronized Collection<GadgetBean> doRetrieveAllOrderGadget(String code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<GadgetBean> gadget = new LinkedList<GadgetBean>();
		String selectSQL = "select * from Gadget, Ordine,  Articolato_da where Ordine.COD_ORDINE = Articolato_da.COD_ORDINE and  Gadget.COD_GADGET = Articolato_da.COD_GADGET and Articolato_da.COD_ORDINE= ?"; 
	
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
					GadgetBean bean = new GadgetBean();
					
						bean.setname(rs.getString("NOME"));
						bean.settype(rs.getString("TIPO"));
						bean.setimage(rs.getString("IMMAGINI"));
						bean.setquantity(rs.getInt("QUANTITA"));
						bean.setcode(rs.getString("COD_GADGET"));
						bean.setprice(rs.getFloat("COSTO"));
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
	
	public synchronized Collection<OrdersBean>  doRetrieveAllAdmin() throws SQLException{
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdersBean> orders = new LinkedList<OrdersBean>();

		String selectSQL = "SELECT * FROM  Ordine";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdersBean bean = new OrdersBean();

				bean.setcode(rs.getString("COD_ORDINE"));
				bean.setemail(rs.getString("E_MAIL"));
				bean.setdate(rs.getString("DATA_ORDINE"));
				bean.settotal(rs.getString("COSTO_TOTALE"));
				bean.setstate(rs.getString("STATO"));
				bean.setpayment(rs.getString("PAGAMENTO"));
				orders.add(bean);
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
		return orders;
	}


	
	@Override
	public synchronized Collection<OrdersBean> doRetrieveAll(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdersBean> orders = new LinkedList<OrdersBean>();

		String selectSQL = "SELECT * FROM  Ordine where E_MAIL=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdersBean bean = new OrdersBean();

				bean.setcode(rs.getString("COD_ORDINE"));
				bean.setdate(rs.getString("DATA_ORDINE"));
				bean.settotal(rs.getString("COSTO_TOTALE"));
				bean.setstate(rs.getString("STATO"));
				bean.setpayment(rs.getString("PAGAMENTO"));
				orders.add(bean);
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
		return orders;
	}

}
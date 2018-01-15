package GestioneOrdini;

import java.sql.SQLException;
import java.util.Collection;

import GestioneOrdini.OrdersBean;
import GestioneProdotti.ComicsBean;
import GestioneProdotti.GadgetBean;

public interface OrdersModel {

	public Collection<GadgetBean> doRetrieveAllOrderGadget(String code) throws SQLException;
	public Collection<ComicsBean> doRetrieveAllOrderComics(String code) throws SQLException;

	public int QuantityGadget(String code) throws SQLException;

	public int QuantityComics(String code) throws SQLException;

	public Collection<OrdersBean> doRetrieveAll(String email) throws SQLException;

	public Collection<OrdersBean> doRetrieveAllAdmin() throws SQLException;

}

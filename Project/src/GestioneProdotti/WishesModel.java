package GestioneProdotti;

import java.sql.SQLException;
import java.util.Collection;


public interface WishesModel {

	public boolean doDeleteWishlistGadget(String code, String email) throws SQLException;
	public boolean doDeleteWishlistComics(String code,String email) throws SQLException;

	public boolean doAddWishlistGadget(String code,String email)throws SQLException;
	public boolean doAddWishlistComics(String code,String email)throws SQLException;

	public Collection<GadgetBean> doRetrieveAllWishlistG(String code) throws SQLException;
	public Collection<ComicsBean> doRetrieveAllWishlistC(String code) throws SQLException;

}

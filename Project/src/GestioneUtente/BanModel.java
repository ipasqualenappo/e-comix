package GestioneUtente;

import java.sql.SQLException;
import java.util.Collection;

public interface BanModel {
	public boolean doDelete(String code) throws SQLException;
	public Collection<ClientBean> doRetrieveAll() throws SQLException;
}
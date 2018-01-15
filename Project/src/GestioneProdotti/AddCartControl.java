package GestioneProdotti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GestioneFumetteria.CartModelDS;
import GestioneFumetteria.ObjectModelDS;
import GestioneFumetteria.ObjectgModelDS;

/**
 * Servlet implementation class OrdersControl
 */
public class AddCartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static boolean isDataSource = true;
	static CartModel model;
	static ObjectgModel model2;
	static ObjectModel model3;
	static {
		if (isDataSource) {
			model = new CartModelDS();
			model2 = new ObjectgModelDS();
			model3 = new ObjectModelDS();
		}
	}

	public AddCartControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String page = "";
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}

		try {
			if (action != null) {
				String id = request.getParameter("id");
				boolean prova;

				if (id.contains("G")) {
					page = "/oggetto";
					prova = true;
					List<GadgetBean> gadget = cart.getgadget(); 	
					for (GadgetBean gadg : gadget) {
						if (gadg.getcode().equals(id)) {
							gadg.setquantity(gadg.getquantity()+1);
							GadgetBean gadge = model2.doRetrieveByKey(id);
							gadg.setprice(gadg.getprice()+gadge.getprice());
							prova = false;
						} else {
							prova = true;
						}
					}
					if (prova == true) {
						cart.addGadget(model2.doRetrieveByKey(id));
					}
				} else if (id.contains("F")) {
					prova = true;
					page = "/object";
					List<ComicsBean> comicscart = cart.getcomic(); 
					for (ComicsBean com : comicscart) {
						if (com.getcode().equals(id)) {
							com.setquantity(com.getquantity()+1);
							ComicsBean comic = model3.doRetrieveByKey(id);
							com.setprice(com.getprice() + comic.getprice());
							prova = false;
						} else {
							prova = true;
						}
					}
					if (prova == true) {
						cart.addComic(model3.doRetrieveByKey(id));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
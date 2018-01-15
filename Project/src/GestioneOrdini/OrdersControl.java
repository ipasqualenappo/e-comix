package GestioneOrdini;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GestioneFumetteria.CartModelDS;
import GestioneFumetteria.OrdersModelDS;
import GestioneProdotti.Cart;
import GestioneProdotti.CartModel;


/**
 * Servlet implementation class OrdersControl
 */
public class OrdersControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// OrdersModelDS usa il DataSource
	// OrdersModelDM usa il DriverManager	
	static boolean isDataSource = true;

	static OrdersModel model;
	static CartModel model2;


	static {
		if (isDataSource) {
			model = new OrdersModelDS();
			model2 = new CartModelDS();
		}
	}

	public OrdersControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		String email = "noemail";
		if (request.getCookies() != null) {

			for (int i = 0; i < request.getCookies().length; i++) {
				if (request.getCookies()[i].getName().equals("email")) {

					email =  request.getCookies()[i].getValue();

				}
			}
		}   

		request.removeAttribute("email");
		request.setAttribute("email", model2.Getemail(email));

		try {
			if (action != null) {

				if (action.equalsIgnoreCase("read")) {
					String id = request.getParameter("id");				
					request.removeAttribute("comics");
					request.setAttribute("comics", model.doRetrieveAllOrderComics(id));

					request.removeAttribute("gadgets");
					request.setAttribute("gadgets", model.doRetrieveAllOrderGadget(id));
				} 
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		try {
			email = "";
			if (request.getCookies() != null) {

				for (int i = 0; i < request.getCookies().length; i++) {
					if (request.getCookies()[i].getName().equals("email")) {

						email =  request.getCookies()[i].getValue();

					}
				} 

				request.removeAttribute("orders");
				request.setAttribute("orders", model.doRetrieveAll(email));

				request.removeAttribute("ordersadmin");
				request.setAttribute("ordersadmin", model.doRetrieveAllAdmin());
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);


		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ordini.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
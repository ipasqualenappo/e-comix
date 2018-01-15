package GestioneProdotti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GestioneFumetteria.CartModelDS;
import GestioneFumetteria.WishesModelDS;


/**
 * Servlet implementation class OrdersControl
 */
public class WishesControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = true;

	static WishesModel model;
	static CartModel  model2;


	static {
		if (isDataSource) {
			model = new WishesModelDS();
			model2 = new CartModelDS();


		}
	}

	public WishesControl() {
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

			email = "";
			if (request.getCookies() != null) {

				for (int i = 0; i < request.getCookies().length; i++) {
					if (request.getCookies()[i].getName().equals("email")) {

						email =  request.getCookies()[i].getValue();

					}
				} 		

				if (action != null) {

					if (action.equalsIgnoreCase("delete")) {
						String id = request.getParameter("id");
						model.doDeleteWishlistComics(id, email);
						model.doDeleteWishlistGadget(id, email);
					}	
				}
			}	
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}


		try {

			String id = "";
			if (request.getCookies() != null) {

				for (int i = 0; i < request.getCookies().length; i++) {
					if (request.getCookies()[i].getName().equals("email")) {

						id =  request.getCookies()[i].getValue();

					}
				} 

				request.removeAttribute("comics");
				request.setAttribute("comics", model.doRetrieveAllWishlistC(id));

				request.removeAttribute("gadgets");
				request.setAttribute("gadgets", model.doRetrieveAllWishlistG(id));

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



		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/desideri.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
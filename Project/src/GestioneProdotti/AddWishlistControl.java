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

public class AddWishlistControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static boolean isDataSource = true;
	static WishesModel model;
	static CartModel model2;
	static {
		if (isDataSource) {
			model = new WishesModelDS();
			model2 = new CartModelDS();
		}
	}

	public AddWishlistControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);

		String action = request.getParameter("action");
		String page = "";

		try {
			if (action != null) {
				String email = "noemail";
				if (request.getCookies() != null) {
					for (int i = 0; i < request.getCookies().length; i++) {
						if (request.getCookies()[i].getName().equals("email")) {
							email =  request.getCookies()[i].getValue();
						}
					}
				}
				String id = request.getParameter("id");
				if (id.contains("G")) {
					page = "/oggetto";
					model.doAddWishlistGadget(id, email);
				}
				else if (id.contains("F")) {
					page = "/object";
					model.doAddWishlistComics(id, email);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

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

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
package GestioneProdotti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GestioneFumetteria.CartModelDS;
import GestioneFumetteria.ConfrontoModelDS;


/**
 * Servlet implementation class OrdersControl
 */
public class ConfrontoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = true;

	static ConfrontoModel model;
	static CartModel  model2;



	static {
		if (isDataSource) {
			model = new ConfrontoModelDS();
			model2 = new CartModelDS();


		}
	}

	public ConfrontoControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {




		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("delete")) {
					String id = request.getParameter("id");
					model.doDeleteConfrontComics(id);
					model.doDeleteConfrontGadget(id);
				}	
			}

		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}


		try {

			request.removeAttribute("comics");
			request.setAttribute("comics", model.doRetrieveAllConfrontComics());

			request.removeAttribute("gadgets");
			request.setAttribute("gadgets", model.doRetrieveAllConfrontGadget());


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


		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confronto.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
package GestioneProdotti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GestioneFumetteria.ComicsModelDS;
import GestioneFumetteria.GadgetModelDS;
import GestioneFumetteria.deleteModelDs;

/**
 * Servlet implementation class OrdersControl
 */
public class deleteproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// OrdersModelDS usa il DataSource
	// OrdersModelDM usa il DriverManager	
	static boolean isDataSource = true;
	static deleteModel model2;
	static GadgetModel model3;
	static ComicsModel model;


	static {
		if (isDataSource) {
			model = new ComicsModelDS();
			model2 = new deleteModelDs();
			model3 = new GadgetModelDS();
		}
	}

	public deleteproduct() {

		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		try {


			if (action != null) {

				if (action.equalsIgnoreCase("delete")) {
					String id = request.getParameter("id");
					model2.doDeleteComics(id);
					model2.doDeleteGadget(id);
				}}	


		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}


		try {


			request.removeAttribute("comics");
			request.setAttribute("comics", model.doRetrieveAll());

			request.removeAttribute("gadgets");
			request.setAttribute("gadgets", model3.doRetrieveAll());


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



		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/eliminaprodotti.jsp");
		dispatcher.forward(request, response);

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
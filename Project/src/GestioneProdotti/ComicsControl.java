package GestioneProdotti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GestioneFumetteria.ComicsModelDS;

/**
 * Servlet implementation class OrdersControl
 */
public class ComicsControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// OrdersModelDS usa il DataSource
	// OrdersModelDM usa il DriverManager	
	static boolean isDataSource = true;
	static ComicsModel model;

	static {
		if (isDataSource) {
			model = new ComicsModelDS();


		}
	}

	public ComicsControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametro ="";
		String parametro2 ="";
		String parametro3 ="";

		try {


			parametro = request.getParameter("genere");
			parametro2 = request.getParameter("publisher");
			parametro3 = request.getParameter("tipo");

			request.removeAttribute("comics");
			request.setAttribute("comics", model.doRetrieveAll(parametro, parametro2, parametro3));		


		}
		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}


		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);


		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fumetti.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
package GestioneProdotti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GestioneFumetteria.GadgetModelDS;

/**
 * Servlet implementation class OrdersControl
 */
public class GadgetControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// OrdersModelDS usa il DataSource
	// OrdersModelDM usa il DriverManager	
	static boolean isDataSource = true;
	static GadgetModel model;


	static {
		if (isDataSource) {
			model = new GadgetModelDS();

		}
	}

	public GadgetControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametro ="";
		String parametro2 ="";

		try {

			parametro = request.getParameter("publisher");
			parametro2 = request.getParameter("tipo");


			request.removeAttribute("gadgets");
			request.setAttribute("gadgets", model.doRetrieveAll(parametro2, parametro));		


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


		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gadget.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
package GestioneProdotti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GestioneFumetteria.ConfrontoModelDS;

public class AddConfrontoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static boolean isDataSource = true;
	static ConfrontoModel model;
	static CartModel model2;
	static {
		if (isDataSource) {
			model = new ConfrontoModelDS();
		}
	}

	public AddConfrontoControl() {
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
				String id = request.getParameter("id");
				if (id.contains("G")) {
					page = "/oggetto";
					model.doAddConfrontGadget(id);
				}
				else if (id.contains("F")) {
					page = "/object";
					model.doAddConfrontComics(id);
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
package GestioneProdotti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import GestioneFumetteria.CartModelDS;
import GestioneFumetteria.ObjectModelDS;
import GestioneFumetteria.ObjectgModelDS;
import GestioneProdotti.Cart;

public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static boolean isDataSource = true;
	static CartModel model;
	static ObjectgModel model3;
	static ObjectModel model4;
	static {
		if (isDataSource) {
			model = new CartModelDS();
			model3 = new ObjectgModelDS();
			model4 = new ObjectModelDS();
		}
	}
	String page = "";

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
		page = "/carrello.jsp";

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("delete")) {
					boolean prova;
					String id = request.getParameter("id");
					if (id.contains("G")) {
						prova = false;
						GadgetBean gadge = model3.doRetrieveByKey(id);
						List<GadgetBean> gadget = cart.getgadget(); 	
						for(GadgetBean gadg : gadget) {
							if(gadg.getcode().equals(id)) {
								gadg.setprice(gadg.getprice() - gadge.getprice());
								gadg.setquantity(gadg.quantity - 1);
								if (gadg.quantity == 0) {
									prova = true;
								}
							}
						}
						if (prova == true)
							cart.deleteGadget(model3.doRetrieveByKey(id));
					}
					else if (id.contains("F")) {
						prova = false;
						ComicsBean comi = model4.doRetrieveByKey(id);
						List<ComicsBean> comicscart = cart.getcomic(); 
						for(ComicsBean com : comicscart) {
							if(com.getcode().equals(id)){
								com.setprice(com.getprice() - comi.getprice());
								com.setquantity(com.quantity - 1);
								if (com.quantity == 0){
									prova = true;
								}
							}
						}
						if (prova == true)
							cart.deleteComic(model4.doRetrieveByKey(id));
					}
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
		request.setAttribute("email", model.Getemail(email));

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
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
		request.setAttribute("email", model.Getemail(email));
		String action = request.getParameter("action");
		try {
			if (action != null) {
				if (action.equalsIgnoreCase("shop")) {
					GregorianCalendar gc = new GregorianCalendar();
					String 	date = gc.get(Calendar.YEAR) + "/" +  gc.get(Calendar.MONTH) + "/" + gc.get(Calendar.DAY_OF_MONTH);
					String lol= "";
					Random randomGenerator = new Random();
					int randomInt = randomGenerator.nextInt(10000);
					lol = String.valueOf(randomInt);
					email = "noemail";
					if (request.getCookies() != null) {
						for (int i = 0; i < request.getCookies().length; i++) {
							if (request.getCookies()[i].getName().equals("email")) {
								email =  request.getCookies()[i].getValue();
							}
						}
					}
					String prezzo = request.getParameter("totale");
					if (!(email.equals("noemail"))) {
						if (!(prezzo.equals("0,00"))){
							model.Shop(lol,date,email,prezzo);
							List<ComicsBean> comicscart = cart.getcomic(); 
							for(ComicsBean beancart: comicscart) {
								model.Shopcomics(beancart.getcode(),lol,beancart.getquantity(),beancart.getavailability());
							}
							List<GadgetBean> gadgetscart = cart.getgadget(); 	
							for(GadgetBean bean2cart: gadgetscart) {
								model.Shopgadget(bean2cart.getcode(),lol,bean2cart.getquantity(),bean2cart.getavailability());
							}			   		
							cart = null;
							page="/order";
						}
					}
					request.getSession().setAttribute("cart", cart);
					request.setAttribute("cart", cart);

				}	
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);	
	}
}
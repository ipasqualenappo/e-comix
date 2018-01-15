package GestioneProdotti;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import GestioneFumetteria.CartModelDS;
import GestioneFumetteria.InsertProductModelDS;
import GestioneFumetteria.ObjectModelDS;
import GestioneFumetteria.ObjectgModelDS;

public class ModifyControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String SAVE_DIR = "/WebContent/immagini";
	// ClientModelDM usa il DriverManager	
	static boolean isDataSource = true;
	static InsertProductModel model;
	static CartModel  model2;
	static ObjectModel model3;
	static ObjectgModel model4;

	static {
		model = new InsertProductModelDS();
		model2 = new CartModelDS();
		model3 = new ObjectModelDS();
		model4 = new ObjectgModelDS();

	}

	public ModifyControl() {
		super();
	}

	String page="";
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		page="/modificaprodotto.jsp";

		try {
			request.removeAttribute("oggetto");
			GadgetBean oggetto2 = new GadgetBean();
			request.setAttribute("oggetto", oggetto2);

			request.removeAttribute("object");
			ComicsBean oggetto = new ComicsBean();
			request.setAttribute("object", oggetto);

			if (action != null) {
				if (action.equalsIgnoreCase("read")) {
					String id = request.getParameter("id");
					if (id.contains("G")) {		
						request.removeAttribute("oggetto");
						request.setAttribute("oggetto", model4.doRetrieveByKey(id));

					}
					else if(id.contains("F")) {
						request.removeAttribute("object");
						request.setAttribute("object", model3.doRetrieveByKey(id));

					}
				}
			}
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


		RequestDispatcher dispatcher = getServletContext().
				getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		String action = request.getParameter("action");
		String savePath = SAVE_DIR;

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}


		String db="";
		if( request.getParts() != null && request.getParts().size() > 0) {
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				if (fileName != null && !fileName.equals("")) {
					part.write(savePath + File.separator + fileName);
					db = "\"immagini/" + fileName + "\"";

				} 
			}

		}

		try {
			request.removeAttribute("oggetto");
			GadgetBean oggetto2 = new GadgetBean();
			request.setAttribute("oggetto", oggetto2);

			request.removeAttribute("object");
			ComicsBean oggetto = new ComicsBean();
			request.setAttribute("object", oggetto);

			if (action != null) {
				if (action.equalsIgnoreCase("read")) {
					String id = request.getParameter("id");
					if (id.contains("G")) {		
						request.removeAttribute("oggetto");
						request.setAttribute("oggetto", model4.doRetrieveByKey(id));

					}
					else if(id.contains("F")) {
						request.removeAttribute("object");
						request.setAttribute("object", model3.doRetrieveByKey(id));

					}

				}
			}
		}
		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}


		try {		
			if (action != null) {
				if (action.equalsIgnoreCase("modifyc")) {
					String code = request.getParameter("code");
					String title = request.getParameter("titolo");
					String gender = request.getParameter("gender");
					float price = Float.parseFloat(request.getParameter("price"));
					String writer = request.getParameter("writer");
					String drawer = request.getParameter("drawer");
					String publisher = request.getParameter("publisher");
					String plot = request.getParameter("plot");
					String type = request.getParameter("tipo");
					String number = request.getParameter("number");
					int availability = Integer.parseInt(request.getParameter("availability"));

					ComicsBean bean = new ComicsBean();
					bean.settitle(title);
					bean.setgender(gender);
					bean.setprice(price);
					bean.setwriter(writer);
					bean.setdrawer(drawer);
					bean.setpublisher(publisher);
					bean.setplot(plot);
					bean.settype(type);
					if (db.equals("")){
						bean.setimage(model3.doRetrieveByKey(code).getimage());
					}
					else {
						bean.setimage(db);
					}
					bean.setnumber(number);
					bean.setavailability(availability);


					page="/deleteproduct";
					model.doUpdateC(bean, code);

				}

			}
		}catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}


		try {		
			if (action != null) {
				if (action.equalsIgnoreCase("modifyg")) {
					String code = request.getParameter("codeg");
					String name = request.getParameter("nome");
					String type = request.getParameter("typeg");
					float price = Float.parseFloat(request.getParameter("priceg"));
					String weight = request.getParameter("weight");
					String dimension = request.getParameter("dimension");
					String publisher = request.getParameter("publisherg");
					String description = request.getParameter("description");
					int availabilityg = Integer.parseInt(request.getParameter("availabilityg"));

					GadgetBean bean = new GadgetBean();
					bean.setname(name);
					bean.settype(type);
					bean.setprice(price);
					bean.setweight(weight);
					bean.setdimension(dimension);
					bean.setpublisher(publisher);
					bean.setdescription(description);
					if (db.equals("")){
						bean.setimage(model4.doRetrieveByKey(code).getimage());
					}
					else {
						bean.setimage(db);
					}
					bean.setavailability(availabilityg);


					page="/deleteproduct";
					model.doUpdateG(bean, code);

				}
			}
		}

		catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}


		RequestDispatcher dispatcher = getServletContext().
				getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}

	private String extractFileName(Part part) {
		//content-disposition: form-data; name="file"; filename="file.txt"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

}

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	float pricegadget = 0;
	float price = 0;
	String strDouble2 = "";

	Cart cart = (Cart) request.getAttribute("cart");
	List<ComicsBean> comicscart = cart.getcomic();
	for (ComicsBean beancart : comicscart) {
		price = price + beancart.getprice();
	}

	List<GadgetBean> gadgetscart = cart.getgadget();
	for (GadgetBean bean2cart : gadgetscart) {
		pricegadget = pricegadget + bean2cart.getprice();
	}

	strDouble2 = String.format("%.2f", price + pricegadget);
%>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>
<header>


	<nav class="topnav" id="myTopnav">
		<a id="title" href="home"> <img class="titolo"
			src="immagini/logo4.png" width=100%></a> <a href="comic">Fumetti</a>
		<a href="gadget">Gadget</a>



		<!--
			Creo il check del cookie per gestire la connessione
			 Così da cambiare in "Il tuo profilo" al posto di "Accedi"
			 In caso possa servire, vecchio pezzo di codice:
			 <a href="login.jsp">Accedi</a>
		/****************************************************************/
		Idea del codice di controllo per la scritta "Accedi" o "Il mio account":
			PS: attaccare la </> e la %, non entrano attaccati nel commento
		-->
		<a href="confronto">Confronto</a>

		<%
			Cookie[] cookies2 = request.getCookies();
			boolean booleanLogin = false;

			if (cookies2 != null) {
				for (Cookie cookie : cookies2) {
					if (((cookie.getName()).equals("attempt")) && ((cookie.getValue()).equals("true"))) {
						String email3 = "noemail";
						if (request.getCookies() != null) {
							for (int i = 0; i < request.getCookies().length; i++) {
								if (request.getCookies()[i].getName().equals("email")) {
									email3 = request.getCookies()[i].getValue();
									if (email3.equals("duckpro@libero.it")) {
										booleanLogin = true;
		%>
		<div class="dropdown" id="mydrop">

			<a href="javascript:void(0);" class="dropbtn" onclick="myFunction2()">Manager
				Account <span class="caret"></span>
			</a>

			<div class="dropdown-content" id="myDropdown">

				<a href="manageraccount">La mia area</a> <a href="aggiungiprodotto">Inserisci
					Prodotto</a> <a href="deleteproduct">Controllo Prodotti</a> <a
					href="order">Visulizza ordini</a> <a href="banned">Elimina
					Utente</a> <a href="logout">Logout</a>
			</div>
		</div>
		<%
			} else {
		%>
		<div class="dropdown" id="mydrop">
			<a href="javascript:void(0);" class="dropbtn" onclick="myFunction2()">Il
				mio Account <span class="caret"></span>
			</a>
			<div class="dropdown-content" id="myDropdown">

				<a href="myaccount">La mia area</a> <a href="order">I Miei
					Acquisti</a> <a href="wishes">La mia lista desideri</a> <a
					href="client">I miei Dati</a> <a href="logout">Logout</a>
			</div>
		</div>

		<%
			booleanLogin = true;
									}
								}
							}
						}

					}
				}
				if (!booleanLogin) {
					out.println("<a href=" + "\"login\"" + "> Accedi </a>");
				}
			}
		%>
		<div id="ricerca" class="ric">
			<form action="search" method="post">
				<input name="searchbar" type="search" placeholder="Ricerca"
					required="required"  onchange="ValidateSearch(searchbar)"> <input id="search" type="image"
					name="search" src="immagini/search.png">
			</form>
		</div>

		<a href="cart"> <img id="carrello" src="immagini/carrello2.png">
			<input type="text" value="<%=strDouble2%>€" readonly>
		</a> <a href="javascript:void(0);" class="icon" onclick="myFunction()"><span
			id="menu">&#9776;</span> </a>
		<div id="rice" class="ric">
			<form action="search" method="post">
				<input name="searchbar" type="search" placeholder="Ricerca"
					required="required">
			</form>
		</div>
		<a href="javascript:void(0);" class="imgsearch"
			onclick="searchFunction()"><img id="search2"
			src="immagini/search.png"></a>

	</nav>

	<script>
		function myFunction() {
			var x = document.getElementById("myTopnav");
			var y = document.getElementById("mydrop");

			if (x.className == "topnav") {
				x.className += " responsive";
			} else {
				x.className = "topnav";
			}

			if (y.className == "dropdown") {
				y.className += " responsive";
			} else {
				y.className = "dropdown";
			}
		}
	</script>

	<script>
		function searchFunction() {

			var z = document.getElementById("rice");
			var x = document.getElementById("myTopnav");

			if (z.className == "ric") {
				z.className += " responsive";
			} else {
				z.className = "ric";
			}

			if (x.className == "topnav") {
				x.className += " res";
			} else {
				x.className = "topnav";
			}

		}
	</script>

	<script>
		/* When the user clicks on the button, 
		 toggle between hiding and showing the dropdown content */
		function myFunction2() {
			document.getElementById("myDropdown").classList.toggle("show");
		}

		// Close the dropdown if the user clicks outside of it
		window.onclick = function(e) {
			if (!e.target.matches('.dropbtn')) {
				var myDropdown = document.getElementById("myDropdown");
				if (myDropdown.classList.contains('show')) {
					myDropdown.classList.remove('show');
				}
			}
		}
	</script>


</header>
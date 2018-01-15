<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> oggetti = (Collection<?>) request.getAttribute("oggetti");
	GadgetBean oggetto = (GadgetBean) request.getAttribute("oggetto");
	
	String email = (String) request.getAttribute("email");

	boolean prova = true;

%>


<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>

<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title><%=oggetto.getname()%> <%=oggetto.gettype()%> -
	e-comix</title>
<meta name="description" content="Nome oggetto">
<meta name="robots" content="index,follow,noodp" />
<%@ include file="head.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp"%>

		<div id="sopra"></div>

		<%  if (oggetto.getavailability()>0){ 
						prova = true;
			} else if (oggetto.getavailability()<=0){ 
						prova = false;
						} 		
							
			if (!(email.equals("noemail"))) { %>


		<script>
				function updatewishes()
				  {
				alert("Prodotto aggiunto alla lista dei desideri");
								  }
				
				function updatecart()
				{
				alert("Prodotto aggiunto al carrello");
				}
				function updateconfront()
				{
					alert("Prodotto aggiunto al confronto");
						}
				</script>

		<% 	} else { %>

		<script>

				function updatewishes()
									  {
				 alert("Devi essere loggato prima di aggiungere articoli alla lista dei desideri");
									  }
	
					function updatecart()
					  {
				alert("Prodotto aggiunto al carrello");
					  }
					function updateconfront()
					  {
				alert("Prodotto aggiunto al confronto");
					  }
				</script>

		<% 	}  %>

		<script>
				function nope()
				  {
			alert("Il prodotto non è al momento disponibile all'acquisto");
				  }
				
			</script>

		<script>
				function nope2()
				  {
			alert("Il gestore non può aggiungere prodotti alla lista desideri e al carrello!");
				  }
				</script>



		<div id="main">
			<ul class="breadcrumb">
				<li><a href="home">Home</a></li>
				<li><a href="gadget">Gadget</a></li>
				<li class="active"><%=oggetto.getname()%> <%=oggetto.gettype()%></li>
			</ul>
			<h1><%=oggetto.getname()%>
				<%=oggetto.gettype()%></h1>
			<hr>
			<div id="table">
				<div id="row">
					<div id="oggettog">

						<img src=<%=oggetto.getimage()%>>
					</div>

					<div id="descrizione2">
						<span>Descrizione:</span>
						<p><%=oggetto.getdescription()%></p>
						<hr>


						<div id="tabledes">
							<div id="rowdes">
								<div id="specs2">
									<span>Tipo:</span>
									<%=oggetto.gettype()%>
									<p>
										<span>Publisher:</span>
										<%=oggetto.getpublisher()%>
									</p>
									<p>
										<span>Disponibilità:</span>
										<%if (prova == true){ %>
										Disponibile
										<%} else { %>
										Non Disponibile

										<%} %>


									</p>
									<p>
										<span>Peso:</span>
										<%=oggetto.getweight()%>
									</p>
									<p>
										<span>Dimensioni:</span>
										<%=oggetto.getdimension()%>
									</p>
									<p>
										<span>Prezzo:</span>
										<%=oggetto.getprice()%>
										&#8364;
									</p>
								</div>
								<div id="aggiungere">

									<div id="add">

										<%if (prova == true && !email.equals("duckpro@libero.it")){ %>
										<form action="carrello?action=read&id=<%=oggetto.getcode()%>"
											method="post">
											<input id="addcart" type="image" src="immagini/carrello2.png"
												title="Aggiungi al carrello" onclick="updatecart()">
										</form>
										<%} else if(email.equals("duckpro@libero.it")){ %>
										<input id="addcart" type="image" src="immagini/carrello2.png"
											title="Aggiungi al carrello" onclick="nope2()">

										<%} else { %>
										<input id="addcart" type="image" src="immagini/carrello2.png"
											title="Aggiungi al carrello" onclick="nope()">

										<%} %>

										<%if (!email.equals("duckpro@libero.it")){ %>
										<form action="wishlist?action=read&id=<%=oggetto.getcode()%>"
											method="post">
											<p>
												<input id="aggiungi" type="image" src="immagini/wishes.png"
													title="Aggiung alla lista dei desideri"
													onclick="updatewishes()">
											</p>
										</form>
										<%} else if(email.equals("duckpro@libero.it")){ %>
										<p>
											<input id="aggiungi" type="image" src="immagini/wishes.png"
												title="Aggiung alla lista dei desideri" onclick="nope2()">
										</p>
										<%} %>
										<form action="compare?action=read&id=<%=oggetto.getcode()%>"
											method="post">
											<p>
												<input id="aggiungi" type="image" src="immagini/shuffle.svg"
													title="Confronta con un altro prodotto"
													onclick="updateconfront()">
											</p>
										</form>
									</div>
									<div id="add2">
										<p>Aggiungi al carrello</p>
										<p>Aggiungi alla lista dei desideri</p>
										<p>Aggiungi al confronto</p>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

			<div id="consigliati">
				<h3>Consigliati</h3>
				<hr>
				<div id="newg" class="bx-viewport"
					style="width: 100%; overflow: hidden; position: relative; height: 270px;">
					<div class="slider1"
						style="width: 155%; position: relative; z-index: 0; transition-duration: 0s; transform: translate3d(-630px, 0px, 0px);">

						<%
								if (oggetti != null && oggetti.size() != 0) {
									Iterator<?> it = oggetti.iterator();
									while (it.hasNext()) {
										GadgetBean bean = (GadgetBean) it.next();
							%>
						<p class="slider" style="margin-left: 25px; margin-right: 15px;">
							<a href="oggetto?action=read&id=<%=bean.getcode()%>"> <img
								src=<%=bean.getimage()%> width="160.5" height="160.5"> <br>
								<%=bean.gettype()%> <br> <%=bean.getname()%>
							</a>
						</p>
						<%
								}
								}
							%>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>

	</div>
</body>
</html>
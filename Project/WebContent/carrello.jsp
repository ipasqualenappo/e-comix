<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> orders = (Collection<?>) request.getAttribute("orders");

	String email = (String) request.getAttribute("email");

%>

<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>

<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Carrello - e-comix</title>
<meta name="description"
	content="Questo è il tuo carrello e qui troverai tutto ciò che stai pensando di comprare">
<meta name="robots" content="noodp" />
<%@ include file="head.jsp"%>
</head>

<body>
	<div class="container">
		<%@ include file="navbar.jsp"%>
		<div id="sopra">
			<script>
			function elimina() {
				alert("Prodotto eliminato dal carrello");

			}
				
			</script>
			<%
				if (!(email.equals("noemail"))) {
					if (!(strDouble2.equals("0,00"))){
					
			%>
			<script>
				
			function acquisto() {
				alert("Acquisto effettuato con successo, verrai reinderizzato ai dettagli dell'ordine.");

			}
					

			</script>
			<%
				}else{
					
					%>
			<script>
				
			function acquisto() {
				alert("Devi aggiungere prima dei prodotti al carrello e poi effettuare l'acquisto.");

			}
					
			function noacquisto() {
				alert("Il gestore non può effettuare acquisti!");

			}
					
			
			</script>


			<%
				}} else {
			%>
			<script>

				function acquisto() {
					alert("Devi essere loggato prima di poter effettuare ordini.");
				}
			</script>
			<%
				}
			%>
		</div>
		<div id="main">
			<ul class="breadcrumb">
				<li><a href="home">Home</a></li>
				<li class="active">Il mio carrello</li>
			</ul>
			<h1>Il mio carrello</h1>
			<hr>
			<div id="tabella" style="overflow-x: auto;">
				<table>
					<tr>
						<th>Immagine</th>
						<th>Quantità</th>
						<th>Riferimento</th>
						<th>Prezzo</th>
						<th>Dettagli</th>
						<th>Elimina dal Carrello</th>
					</tr>
					<%
					if (!(strDouble2.equals("0,00"))) {
					%>
					<%
				 List<ComicsBean> comicscart2 = cart.getcomic(); 	
					   for(ComicsBean beancart: comicscart2) {
					%>
					<tr>
						<td><img src=<%=beancart.getimage()%> width=100px
							height=150px></td>
						<td><%=beancart.getquantity()%></td>
						<td><%=beancart.gettitle()%> n.<%=beancart.getnumber()%></td>
						<td><%=beancart.getprice()%> €</td>
						<td><a href="object?action=read&id=<%=beancart.getcode()%>">Dettagli</a></td>
						<% if (beancart.getquantity()==1) { %>
						<td><a href="cart?action=delete&id=<%=beancart.getcode()%>">Elimina</a></td>
						<% }else  { %>
						<td><a href="cart?action=delete&id=<%=beancart.getcode()%>">Diminuisci
								Quantità</a></td>

						<% } %>
					</tr>
					<%
						}
							
					%>
					<%
					List<GadgetBean> gadgetscart2 = cart.getgadget(); 	
					   for(GadgetBean bean2cart: gadgetscart2) {
					%>
					<tr>
						<td><img src=<%=bean2cart.getimage()%> width=150px
							height=150px></td>
						<td><%=bean2cart.getquantity()%></td>
						<td><%=bean2cart.getname()%> <%=bean2cart.gettype()%></td>
						<td><%=bean2cart.getprice()%> €</td>
						<td><a href="oggetto?action=read&id=<%=bean2cart.getcode()%>">Dettagli</a></td>
						<% if (bean2cart.getquantity()==1) { %>
						<td><a href="cart?action=delete&id=<%=bean2cart.getcode()%>">Elimina</a></td>
						<% }else  { %>
						<td><a href="cart?action=delete&id=<%=bean2cart.getcode()%>">Diminuisci
								Quantità</a></td>

						<% } %>

					</tr>

					<%
						}
							

						} else {
					%>
					<tr>
						<td colspan="7">Non sono presenti articoli nel carrello</td>
					</tr>
					<%
						}
					%>

				</table>
			</div>

			<div id="table">
				<div id="row">
					<div id="cart1"></div>

					<div id="cart2">
						<form action="cart" method="post">
							<input type="hidden" name="action" value="shop">
							<h3>
								Totale: <input name="totale" type="text" maxlength="30"
									value="<%=strDouble2%>" readonly><span
									style="font-size: 15px; font-weight: normal;">€</span>
							</h3>
							<% if(!email.equals("duckpro@libero.it")){ %>

							<input type="submit" name="shop" value="Finalizza l'acquisto"
								onclick="acquisto()">
							<%} else if(email.equals("duckpro@libero.it")){ %>
							<input type="submit" value="Finalizza l'acquisto"
								onclick="noacquisto()">
							<%}  %>

						</form>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>

	</div>
</body>
</html>
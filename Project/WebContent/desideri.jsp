<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Collection<?> orders = (Collection<?>) request.getAttribute("orders");
	Collection<?> gadgets = (Collection<?>) request.getAttribute("gadgets");
	Collection<?> comics = (Collection<?>) request.getAttribute("comics");
	String email = (String) request.getAttribute("email");
	Boolean RegisteredUserRoles = (Boolean) session.getAttribute("RegisteredUserRoles");
	 Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");

	 if ((RegisteredUserRoles == null) || (!RegisteredUserRoles.booleanValue()))
	{
		 if ((adminRoles == null) || (!adminRoles.booleanValue()))
	{	
	    response.sendRedirect("./myaccount");
	    return;
	} 
	   
	}
	

%>

<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>


<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>La tua lista dei desideri - e-comix</title>
<meta name="description"
	content="Questa è la lista di tutti i tuoi desideri su e-comix">
<meta name="robots" content="index,follow,noodp" />
<%@ include file="head.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp"%>
		<div id="sopra"></div>

		<div id="main">
			<ul class="breadcrumb">
				<li><a href="home">Home</a></li>
				<%if (email.equals("duckpro@libero.it")) {%>

				<% } else{%>
				<li><a href="myaccount">Il mio account</a></li>
				<%} %>

				<li class="active">La mia lista desideri</li>
			</ul>
			<h1>La mia lista dei desideri</h1>
			<hr>

			<script>
					function updatewishes()
									  {
				 alert("Prodotto rimosso dalla lista dei desideri");
									  }
					
					function updatecart()
					  {
				alert("Prodotto aggiunto al carrello");
					  }
				
				</script>




			<div id="tabella" style="overflow-x: auto;">
				<table>

					<tr>
						<th>Immagine</th>
						<th>Riferimento</th>
						<th>Prezzo</th>
						<th>Categoria</th>
						<th>Dettagli</th>
						<th>Cancella</th>
						<th>Carrello</th>
					</tr>
					<%
						if (comics.size() > 0 || gadgets.size() > 0) {
			%>
					<%
						if (comics != null && comics.size() != 0) {
							Iterator<?> it = comics.iterator();
							while (it.hasNext()) {
								ComicsBean bean = (ComicsBean) it.next();
					%>
					<tr>
						<td><img src=<%=bean.getimage()%> width=100px height=150px></td>
						<td><%=bean.gettitle()%> n.<%=bean.getnumber()%></td>
						<td><%=bean.getprice()%> €</td>
						<td><%=bean.getgender()%></td>
						<td><a href="object?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>
						<td><a href="wishes?action=delete&id=<%=bean.getcode()%>"
							onclick="updatewishes()">Elimina</a></td>

						<%  if (bean.getavailability()>0){ %>
						<td><a href="carrello?action=read&id=<%=bean.getcode()%>"
							onclick="updatecart()">Aggiungi</a> <% } else if (bean.getavailability()==0){  %>
						<td><a href="#" onclick="nope()">Aggiungi</a> <%	} 		
							%>
					</tr>


					<%
						}
						}
					%>
					<%
						if (gadgets != null && gadgets.size() != 0) {
							Iterator<?> it = gadgets.iterator();
							while (it.hasNext()) {
								GadgetBean bean = (GadgetBean) it.next();
					%>
					<tr>
						<td><img src=<%=bean.getimage()%> width=150px height=150px></td>
						<td><%=bean.getname()%></td>
						<td><%=bean.getprice()%> €</td>
						<td><%=bean.gettype()%></td>
						<td><a href="oggetto?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>
						<td><a href="wishes?action=delete&id=<%=bean.getcode()%>"
							onclick="updatewishes()">Elimina</a></td>

						<%  if (bean.getavailability()>0){ %>
						<td><a href="carrello?action=read&id=<%=bean.getcode()%>"
							onclick="updatecart()">Aggiungi</a> <% } else if (bean.getavailability()==0){  %>
						<td>Non disponibile</td>
						<%	} 		
							%>
					</tr>


					<%
						}
						}
			
						} else {
					%>
					<tr>
						<td colspan="7">Non sono presenti articoli nella lista dei
							desideri</td>
					</tr>
					<%
						}
					%>


				</table>
			</div>
		</div>
		<%@ include file="footer.jsp"%>

	</div>
</body>
</html>
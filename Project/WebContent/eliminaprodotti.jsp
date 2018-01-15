<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
if ((adminRoles == null) || (!adminRoles.booleanValue()))
{	
    response.sendRedirect("./login");
    return;
}


	Collection<?> gadgets = (Collection<?>) request.getAttribute("gadgets");
	Collection<?> comics = (Collection<?>) request.getAttribute("comics");
%>

<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>

<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Elimina o modifica Prodotti - e-comix</title>
<meta name="description"
	content="Qui puoi eliminare o modificare i prodotti.">
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
				<li><a href="manageraccount">Manager area</a></li>
				<li class="active">Controllo Prodotti</li>
			</ul>


			<script>
					function remove()
									  {
				 alert("Prodotto rimosso dal database");
									  }
	
		
				</script>


			<h1>Controllo Fumetti</h1>
			<hr>

			<div id="tabella" style="overflow-x: auto;">
				<table>

					<tr>
						<th>Codice</th>
						<th>Riferimento</th>
						<th>Prezzo</th>
						<th>Dettagli</th>
						<th>Cancella dal database</th>
						<th>Modifica Fumetto</th>

					</tr>
					<%
				if (comics != null) {
			%>
					<%
						if (comics != null && comics.size() != 0) {
							Iterator<?> it = comics.iterator();
							while (it.hasNext()) {
								ComicsBean bean = (ComicsBean) it.next();
					%>
					<tr>
						<td><%=bean.getcode()%></td>
						<td><%=bean.gettitle()%> n.<%=bean.getnumber()%></td>
						<td><%=bean.getprice()%> €</td>
						<td><a href="object?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>
						<td><a
							href="deleteproduct?action=delete&id=<%=bean.getcode()%>"
							onclick="remove()">Elimina</a></td>
						<td><a href="modify?action=read&id=<%=bean.getcode()%>">Modifica</a></td>


					</tr>


					<%
						}
						}
					%>
					<%
						
						} else {
					%>

					<tr>
						<td colspan="7">Non sono presenti articoli</td>
					</tr>
					<%
						}
					%>
				</table>
			</div>
			<br>
			<br>
			<%
				if (gadgets != null) {
			%>

			<h1>Controllo Gadget</h1>
			<hr>

			<div id="tabella" style="overflow-x: auto;">
				<table>

					<tr>
						<th>Codice</th>
						<th>Riferimento</th>
						<th>Prezzo</th>
						<th>Dettagli</th>
						<th>Cancella dal database</th>
						<th>Modifica Gadget</th>


					</tr>

					<%
						if (gadgets != null && gadgets.size() != 0) {
							Iterator<?> it = gadgets.iterator();
							while (it.hasNext()) {
								GadgetBean bean = (GadgetBean) it.next();
					%>
					<tr>
						<td><%=bean.getcode()%></td>
						<td><%=bean.getname()%></td>
						<td><%=bean.getprice()%> €</td>

						<td><a href="oggetto?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>
						<td><a
							href="deleteproduct?action=delete&id=<%=bean.getcode()%>"
							onclick="remove()">Elimina</a></td>
						<td><a href="modify?action=read&id=<%=bean.getcode()%>">Modifica</a></td>

					</tr>


					<%
						}
						}
			
						} else {
					%>
					<tr>
						<td colspan="7">Non sono presenti articoli</td>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> orders = (Collection<?>) request.getAttribute("orders");
	Collection<?> ordersadmin = (Collection<?>) request.getAttribute("ordersadmin");

	Collection<?> gadgets = (Collection<?>) request.getAttribute("gadgets");
	Collection<?> comics = (Collection<?>) request.getAttribute("comics");
	OrdersBean order = (OrdersBean) request.getAttribute("order");
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
<title>I tuoi ordini - e-comix</title>
<meta name="description"
	content="Questa è la lista di tutti i tuoi ordini su e-comix">
<meta name="robots" content="index,follow,noodp" />
<%@ include file="head.jsp"%>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div id="sopra"></div>


	<div id="main">
		<ul class="breadcrumb">
			<li><a href="home">Home</a></li>

			<%if (email.equals("duckpro@libero.it")) {%>
			<li><a href="manageraccount">Manager area</a></li>

			<% } else{%>
			<li><a href="myaccount">Il mio account</a></li>
			<%} %>

			<%if (email.equals("duckpro@libero.it")) {%>
			<li class="active">Visualizza ordini</li>

			<% } else{%>
			<li class="active">I Miei Acquisti</li>
			<%} %>
		</ul>
		<%if (email.equals("duckpro@libero.it")) {%>
		<h1>Visualizza Ordini</h1>
		<% } else{%>
		<h1>I miei Acquisti</h1>
		<%} %>

		<hr>

		<%if (email.equals("duckpro@libero.it")) {%>

		<div id="tabella" style="overflow-x: auto;">
			<table>
				<tr>
					<th>Email</th>
					<th>Codice</th>
					<th>Data</th>
					<th>Costo Totale</th>
					<th>Pagamento</th>
					<th>Stato</th>
					<th>Dettagli</th>
				</tr>
				<%
					
						if (ordersadmin != null && ordersadmin.size() != 0) {
							Iterator<?> it = ordersadmin.iterator();
							while (it.hasNext()) {
								OrdersBean bean = (OrdersBean) it.next();
						
					%>
				<tr>
					<td><%=bean.getemail()%></td>
					<td><%=bean.getcode()%></td>
					<td><%=bean.getdate()%></td>
					<td><%=bean.gettotal()%> €</td>
					<td><%=bean.getpayment()%></td>
					<td><%=bean.getstate()%></td>
					<td><a href="order?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>
				</tr>
				<%
						}
						} else {
					%>
				<tr>
					<td colspan="6">Non hai ancora effettuato ordini</td>
				</tr>
				<%
						}
					%>
			</table>

			<% } else {  %>
			<div id="tabella" style="overflow-x: auto;">
				<table>
					<tr>
						<th>Codice</th>
						<th>Data</th>
						<th>Costo Totale</th>
						<th>Pagamento</th>
						<th>Stato</th>
						<th>Dettagli</th>
					</tr>
					<%
					
						if (orders != null && orders.size() != 0) {
							Iterator<?> it = orders.iterator();
							while (it.hasNext()) {
								OrdersBean bean = (OrdersBean) it.next();
						
					%>
					<tr>
						<td><%=bean.getcode()%></td>
						<td><%=bean.getdate()%></td>
						<td><%=bean.gettotal()%> €</td>
						<td><%=bean.getpayment()%></td>
						<td><%=bean.getstate()%></td>
						<td><a href="order?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>
					</tr>
					<%
						}
						} else {
					%>
					<tr>
						<td colspan="6">Non hai ancora effettuato ordini</td>
					</tr>
					<%
						}
					%>
				</table>
				<% } %>


				<%
				if (comics != null || gadgets != null) {
			%>
				<br> <br>
				<h4>Dettagli</h4>
				<hr>
				<div id="tabella" style="overflow-x: auto;">
					<table>
						<tr>
							<th>Immagine</th>
							<th>Quantità</th>
							<th>Categoria</th>
							<th>Riferimento</th>
							<th>Costo</th>
							<th>Dettagli</th>
						</tr>
						<%
						if (comics != null && comics.size() != 0) {
							Iterator<?> it = comics.iterator();
							while (it.hasNext()) {
								ComicsBean bean = (ComicsBean) it.next();
					%>
						<tr>
							<td><img src=<%=bean.getimage()%> width=100px height=150px></td>
							<td><%=bean.getquantity()%></td>
							<td><%=bean.getgender()%></td>
							<td><%=bean.gettitle()%> n.<%=bean.getnumber()%></td>
							<td><%=bean.getprice()%> €</td>
							<td><a href="object?action=read&id=<%=bean.getcode()%>">Dettagli</a></td>
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
							<td><%=bean.getquantity()%></td>
							<td><%=bean.gettype()%></td>
							<td><%=bean.getname()%></td>
							<td><%=bean.getprice()%> €</td>
							<td><a href="oggetto?action=read&id=<%=bean.getcode()%>">Dettagi</a></td>
						</tr>


						<%
						}
						}
						
					%>
					</table>
					<% 
						}
					%>

					<br>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ClientBean client = (ClientBean) request.getAttribute("client");
	String email = (String) request.getAttribute("email");
	
	String	registra = (String) session.getAttribute("NewUser");
	
	String ver= (String) request.getAttribute("verifica");

%>

<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,GestioneFumetteria.*, GestioneProdotti.*, GestioneUtente.*,  GestioneOrdini.* "%>

<html lang="it-IT">
<head>
<meta charset="utf-8" />
<title>Profilo Utente - e-comix</title>
<meta name="description"
	content="Questo è il tuo profilo utente su e-comix">
<meta name="robots" content="index,follow,noodp" />
<%@ include file="head.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="navbar.jsp"%>

		<div id="sopra"></div>



		<div id="main">
			<ul class="breadcrumb">
				<li><a href="home.jsp">Home</a></li>
				<%if (email.equals("noemail")) {%>

				<% } else {%>
				<li><a href="myaccount">Il mio account</a></li>
				<% }%>

				<li class="active">I miei Dati</li>
			</ul>
			<h1>I miei Dati</h1>
			<hr>

			<form action="client" method="post">
				<input type="hidden" name="action" value="insert">

				<div id="table">
					<div id="row">

						<div id="dati1">
							<p>Nome:</p>
							<input name="nome" type="text" maxlength="30" required
								onchange="ValidateName(nome)" value="<%=client.getName()%>">


							<p>Cellulare:</p>
							<input name="phone"  type="text" maxlength="10" required
								onchange="ValidatePhone(phone)" value="<%=client.getPhone()%>">


							<p>Indirizzo:</p>
							<input name="address" type="text" maxlength="30" required
								onchange="ValidateAddress(address)" value="<%=client.getAddress()%>">

						</div>



						<div id="dati2">
							<p>Cognome:</p>
							<input name="surname" type="text" maxlength="30" required
								onchange="allLetters(surname)" value="<%=client.getSurname()%>">


							<%if (email.equals("noemail")) {%>
							<p>E-mail:</p>
							<input name="email" type="email" maxlength="30" required
								value="<%=registra%>" readonly="readonly">
							<% } else{%>
							<p>E-mail:</p>
							<input name="email" type="text" maxlength="30" required
								value="<%=client.getEmail()%>" readonly="readonly">
							<%} %>

							<p>Città:</p>
							<input name="city" type="text" maxlength="30" required
								onchange="ValidateCity(city)" value="<%=client.getCity()%>">

						</div>

						<div id="dati3">

							<p>CAP:</p>
							<input name="cap" type="text" maxlength="10" required
								onchange="ValidateCap(cap)" value="<%=client.getCap()%>"> <br>

							<p>Password:</p>
							<input name="password" type="password" maxlength="10" required onchange="ValidatePassword(password)">


						</div>

						<div id="dati4">


							<p>Provincia:</p>
							<input name="region" type="text" maxlength="30" required
								onchange="ValidateProvincia(region)"  value="<%=client.getRegion()%>">

							<p>Verifica password:</p>
							<input name="password2" type="password" maxlength="10" required  onchange="ValidatePassword(password2)">


						</div>
					</div>
				</div>




				<div id="send2">


					<input type="submit" value="Conferma">

					<% 	if (ver.equals("false")) { %>
					<h4>Le password inserite non corrispondono</h4>
					<%}%>


				</div>

			</form>
		</div>
		<%@ include file="footer.jsp"%>

	</div>
</body>
</html>
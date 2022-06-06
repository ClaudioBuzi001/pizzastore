<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Rimuovi Ordine</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Conferma Chiusura Ordine</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${ordine_delete_att.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Codice:</dt>
							  <dd class="col-sm-9">${ordine_delete_att.codice}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di Consegna:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${ordine_delete_att.data}" /></dd>
					    	</dl>
					    	
					    	
					    	<!-- info Cliente -->
					    	<p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseCliente" role="button" aria-expanded="false" aria-controls="collapseCliente">
							    Info Cliente
							  </a>
							</p>
							<div class="collapse" id="collapseCliente">
							  <div class="card card-body">
							  	<dl class="row">
								  <dt class="col-sm-3 text-right">Nome:</dt>
								  <dd class="col-sm-9">${ordine_delete_att.cliente.nome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Cognome:</dt>
								  <dd class="col-sm-9">${ordine_delete_att.cliente.cognome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Indirizzo:</dt>
								  <dd class="col-sm-9">${ordine_delete_att.cliente.indirizzo}</dd>
							   	</dl>
							    
							  </div>
							<!-- end info Cliente -->
							
					    	</div>
					    	
					    <!-- end card body -->
					    </div>
					    	
					    <!-- end card body -->
					    <div class='card-footer'>
					    	<form action="${pageContext.request.contextPath}/ExecuteDeleteFattorinoServlet" method="post">
					    		<input type="hidden" name="idOrdine" value="${ordine_delete_att.id}">
						    	<button type="submit" name="submit" id="submit" class="btn btn-danger">Conferma</button>
						        <a href="${pageContext.request.contextPath}/ExecuteListFattorinoServlet" class='btn btn-outline-secondary' style='width:80px'>
						            <i class='fa fa-chevron-left'></i> Back
						        </a>
					        </form>
					    </div>
					<!-- end card -->
					</div>	
			  
			    </div>
			  <!-- end container -->  
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>
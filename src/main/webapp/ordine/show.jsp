<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Dettagli Ordine</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="./navbarOrdine.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza dettaglio</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${ordine_visualizza_att.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Codice:</dt>
							  <dd class="col-sm-9">${ordine_visualizza_att.codice}</dd>
					    	</dl>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Costo:</dt>
							  <dd class="col-sm-9">${ordine_visualizza_att.costoTotaleOrdine}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di Consegna:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${ordine_visualizza_att.data}" /></dd>
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
								  <dd class="col-sm-9">${ordine_visualizza_att.cliente.nome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Cognome:</dt>
								  <dd class="col-sm-9">${ordine_visualizza_att.cliente.cognome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Indirizzo:</dt>
								  <dd class="col-sm-9">${ordine_visualizza_att.cliente.indirizzo}</dd>
							   	</dl>
							    
							  </div>
							<!-- end info Cliente -->
							</div>
							
							<p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseFattorino" role="button" aria-expanded="false" aria-controls="collapseFattorino">
							    Info Fattorino
							  </a>
							</p>
							<div class="collapse" id="collapseFattorino">
							  <div class="card card-body">
							  	<dl class="row">
								  <dt class="col-sm-3 text-right">Nome:</dt>
								  <dd class="col-sm-9">${ordine_visualizza_att.utente.nome}</dd>
							   	</dl>
							   	<dl class="row">
								  <dt class="col-sm-3 text-right">Cognome:</dt>
								  <dd class="col-sm-9">${ordine_visualizza_att.utente.cognome}</dd>
							   	</dl>
							    
							  </div>
							<!-- end info Fattorino -->
							
					    	</div>
					    	
					    <!-- end card body -->
					    
					    
					    <div class='card-footer'>
					        <a href="${pageContext.request.contextPath}/ExecuteListOrdineServlet" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			 </div> 
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>
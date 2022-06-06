<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	
	   <title>Visualizza Elemento</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Sicuro di eliminare?</h5>
					    </div>
					    
					<form method="post" action="ExecuteDeleteClienteServlet" class="row g-3" novalidate="novalidate">
					    <div class='card-body'>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Nome:</dt>
							  <dd class="col-sm-9">${cliente_eliminare_att.nome}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Cognome:</dt>
							  <dd class="col-sm-9">${cliente_eliminare_att.cognome}</dd>
					    	</dl>

					    	<dl class="row">
							  <dt class="col-sm-3 text-right">indirizzo:</dt>
							  <dd class="col-sm-9">${cliente_eliminare_att.indirizzo}</dd>
					    	</dl>
					    	<input type="hidden" name="idCliente" value="${cliente_eliminare_att.id}">
					    	
					    	<!-- info Regista -->
					    	<p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
							    Info Ordini
							  </a>
							</p>
							
							<input type="submit" value="Elimina">
					    
					    <!-- end card body -->
					    </div>
					     </form>	
					     
					    <div class='card-footer'>
					        <a href="ExecuteListClienteServlet" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
					    </div>
					<!-- end card -->
					</div>	
			  
			   
  
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Visualizza prezzo</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="./navbarOrdine.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Prezzo Totale Ordine</h5> 
				    </div>
				    <form action="ExecuteListOrdineServlet?operationResult=SUCCESS" method="post">	
				    <br>
				    <p>Prezzo: ${sommaTotale} </p>
				    <br><br>
				    <input type="submit" value="Conferma">
				    </form>
				<!-- end card -->
				</div>		
				
			</div>	
					  
			    
			  <!-- end container -->  
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>
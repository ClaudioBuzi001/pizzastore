<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	
		<jsp:include page="../header.jsp"/>
		
		<title>Trova Cliente</title>
	
	</head>
	<body>
		<jsp:include page="./navbarAdmin.jsp"></jsp:include>
		
		<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Ricerca Cliente</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="ExecuteSearchClienteServlet" class="row g-3" novalidate="novalidate">
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome</label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" required>
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome </label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" required>
								</div>
							
								<div class="col-md-6">
									<label for="indirizzo" class="form-label">Indirizzo</label>
									<input class="form-control" id="indirizzo" type="text" placeholder="Inserire l'indirizzo" name="indirizzo">
								</div>
	                        												
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
							</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
		
		<jsp:include page="../footer.jsp"/>
	</body>
</html>
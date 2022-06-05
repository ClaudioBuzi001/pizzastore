<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Inserisci Ordine</title>
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
				        <h5>Inserisci nuovo ordine</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="${pageContext.request.contextPath}/ExecuteInsertOrdineServlet" class="row g-3" novalidate="novalidate">
							
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice</label>
									<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" value="${ordine_insert_att.codice }">
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="data" type='date' value='${ordine_insert_att.data}' />
								
								<div class="col-md-6">
									<label for="data" class="form-label">Data di Consegna</label>
	                        		<input class="form-control" id="data" type="date" placeholder="dd/MM/yy" 
	                        				title="formato : gg/mm/aaaa"  name="data" value="${data}" >
								</div>
								
								<div class="col-md-6">
									<label for="cliente">Cliente</label>
								    <select class="form-select" id="cliente" name="cliente">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${clienti_list_att}" var="clienteItem">
								      		<option value="${clienteItem.id}" ${ordine_insert_att.cliente.id == clienteItem.id?'selected':''} >${clienteItem.nome } ${clienteItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
								
								<div class="col-md-6">
									<label for="fattorino">Fattorini</label>
								    <select class="form-select" id="fattorino" name="fattorino">
								    
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${fattorini_list_att}" var="fattorinoItem">
								      		<option value="${fattorinoItem.id}" 
								      		${ordine_insert_att.utente.id == fattorinoItem.id?'selected':''} >
								      		${fattorinoItem.nome } ${fattorinoItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
								
								<c:forEach items="${pizze_list_att}" var="pizzaItem">
									<div class="form-check form-check-inline">
											  <input class="form-check-input" type="checkbox" value="${pizzaItem.id}"
											  		 id="pizza" name="pizza">
											  <label class="form-check-label" for="pizza">
											    Pizza ${pizzaItem.descrizione}
											  </label>
									</div>
								</c:forEach>
								
								<div class="col-12">
									<button type="submit" name="insertSubmit" value="insertSubmit" id="insertSubmit" class="btn btn-primary">Conferma</button>
								</div>
		
								</form>
						</div>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>
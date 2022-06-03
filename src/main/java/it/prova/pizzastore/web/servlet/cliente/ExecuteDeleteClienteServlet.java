package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteClienteServlet
 */
@WebServlet("/ExecuteDeleteClienteServlet")
public class ExecuteDeleteClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteDeleteClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// binding id
		String idInput = request.getParameter("idCliente");
		
		if (!NumberUtils.isCreatable(idInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore. ");
			request.getRequestDispatcher("/cliente/delete.jsp").forward(request, response);
			return;
		}
		
		//carico il cliente da eliminare
		try {
			Cliente daEliminare = MyServiceFactory.getClienteServiceInstance().caricaSingoloElemento(Long.parseLong(idInput));
			//Lo elimino LOGICAMENTE, quindi ponendo il suo stato attivo = false
			daEliminare.setAttivo(false);
			
			//aggiorno il cliente nel database
			MyServiceFactory.getClienteServiceInstance().aggiorna(daEliminare);
			
			//Rimando la lista al list.jsp pero con la lista che prende solo clienti attivi
			request.setAttribute("list_clienti_att", MyServiceFactory.getClienteServiceInstance().listAllAttivi());
			request.setAttribute("successMessage", "Operazione effettuata con successo");


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore .");
			request.getRequestDispatcher("/cliente/delete.jsp").forward(request, response);
			return;
		}
		
		
		request.getRequestDispatcher("/cliente/list.jsp").forward(request, response);
		
	}

}

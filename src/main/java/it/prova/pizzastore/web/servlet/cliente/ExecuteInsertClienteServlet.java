package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteInsertClienteServlet
 */
@WebServlet("/ExecuteInsertClienteServlet")
public class ExecuteInsertClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteInsertClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Binding dei dati
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String indirizzoInput = request.getParameter("indirizzo");

		Cliente clienteInstance = UtilityForm.createClienteFromParams(nomeInput, cognomeInput, indirizzoInput);

		try {
			// controlliamo che sia accettabile
			if (!UtilityForm.validateClienteBean(clienteInstance)) {
				// allora c è un errore
				request.setAttribute("insert_cliente_att", clienteInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");

				request.getRequestDispatcher("/cliente/insert.jsp").forward(request, response);
				return;
			}

			// se son qui allora la verifcia è andata bene
			MyServiceFactory.getClienteServiceInstance().inserisciNuovo(clienteInstance);
		} catch (Exception e) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/cliente/insert.jsp").forward(request, response);
			return;

		}

		// andiamo ai risultati
		// uso il sendRedirect con parametro per evitare il problema del double save on
		// refresh
		response.sendRedirect("ExecuteListClientiServlet?operationResult=SUCCESS");

	}

}

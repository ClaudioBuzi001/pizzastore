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
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteUpdateClienteServlet
 */
@WebServlet("/ExecuteUpdateClienteServlet")
public class ExecuteUpdateClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteUpdateClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// binding deidati
		String nomeInput = request.getParameter("nome");
		String cognomeInput = request.getParameter("cognome");
		String indirizzoInput = request.getParameter("indirizzo");

		// binding id
		String idInput = request.getParameter("idCliente");

		Cliente clienteInstance = UtilityForm.createClienteFromParams(nomeInput, cognomeInput, indirizzoInput);

		if (!NumberUtils.isCreatable(idInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore. "); //FIXME
			request.getRequestDispatcher("/cliente/edit.jsp").forward(request, response);
			return;
		}

		clienteInstance.setId(Long.parseLong(idInput));

		if (!UtilityForm.validateClienteBean(clienteInstance)) {
			request.setAttribute("errorMessage",
					"Attenzione sono presenti errori di validazione, immetti correttamente i dati.");
			request.setAttribute("cliente_modificare_att", clienteInstance);
			request.getRequestDispatcher("/cliente/edit.jsp").forward(request, response);
			return;
		}

		// Aggiorno i dati
		try {
			MyServiceFactory.getClienteServiceInstance().aggiorna(clienteInstance);

			request.setAttribute("list_clienti_att", MyServiceFactory.getClienteServiceInstance().listAllAttivi());
			request.setAttribute("successMessage", "Operazione effettuata con successo");

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore .");
			request.getRequestDispatcher("/cliente/edit.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/cliente/list.jsp").forward(request, response);

	}

}

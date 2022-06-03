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
 * Servlet implementation class ExecuteShowClienteServlet
 */
@WebServlet("/ExecuteShowClienteServlet")
public class ExecuteShowClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteShowClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// binding
		String idInput = request.getParameter("idCliente");

		if (!NumberUtils.isCreatable(idInput)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/cliente/list.jsp").forward(request, response);
			return;
		}

		// setto il nuovo attributo per la pagina show
		try {
			Cliente clienteItem = MyServiceFactory.getClienteServiceInstance()
					.caricaSingoloElementoConOrdini(Long.parseLong(idInput));

			if (clienteItem == null) {

				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListClienteServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			request.setAttribute("cliente_visualizza_att", clienteItem);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/cliente/list.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/cliente/show.jsp").forward(request, response);

	}

}

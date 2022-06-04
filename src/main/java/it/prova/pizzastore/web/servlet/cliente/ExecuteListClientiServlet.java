package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListClientiServlet
 */
@WebServlet("/ExecuteListClientiServlet")
public class ExecuteListClientiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteListClientiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// se nell'url della request è presente SUCCESS significa che devo mandare un
			// messaggio di avvenuta operazione in pagina
			String operationResult = request.getParameter("operationResult");
			if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("SUCCESS"))
				request.setAttribute("successMessage", "Operazione effettuata con successo");
			if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("ERROR"))
				request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("NOT_FOUND"))
				request.setAttribute("errorMessage", "Clienti non trovati.");
			
			//gli passo la lista di clienti
			request.setAttribute("list_clienti_att", MyServiceFactory.getClienteServiceInstance().listAllAttivi());
			
			request.getRequestDispatcher("/cliente/list.jsp").forward(request, response);
			return;
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/cliente/homeAdmin.jsp").forward(request, response);
			return;
		}
		

	}

}

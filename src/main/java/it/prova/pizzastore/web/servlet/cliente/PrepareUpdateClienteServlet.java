package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareUpdateClienteServlet
 */
@WebServlet("/PrepareUpdateClienteServlet")
public class PrepareUpdateClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrepareUpdateClienteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// binding del id
		String idInput = request.getParameter("idCliente");

		// Vedo se l id è creabile
		if (!NumberUtils.isCreatable(idInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore, id errato.");
			request.getRequestDispatcher("/cliente/list.jsp").forward(request, response);
			return;
		}

		// Setto come attributo il cliente caricato con l id
		try {
			request.setAttribute("cliente_modificare_att",
					MyServiceFactory.getClienteServiceInstance().caricaSingoloElemento(Long.parseLong(idInput)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Impossibile modificare tale elemento.");
			request.getRequestDispatcher("/cliente/list.jsp").forward(request, response);
			return;
		}

		// forward to edit.jsp
		request.getRequestDispatcher("/cliente/edit.jsp").forward(request, response);

	}

}

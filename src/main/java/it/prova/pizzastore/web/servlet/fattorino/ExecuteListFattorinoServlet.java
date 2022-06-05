package it.prova.pizzastore.web.servlet.fattorino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListPizzaioloServlet
 */
@WebServlet("/ExecuteListFattorinoServlet")
public class ExecuteListFattorinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteListFattorinoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setto come attribuo la lista di ordini a carico dell user della sessione con
		// attivo = true
		Utente utenteInstance = (Utente) request.getSession().getAttribute("userInfo");
		try {
			utenteInstance = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(utenteInstance.getId());

			request.setAttribute("ordini_list_att",
					MyServiceFactory.getOrdineServiceInstance().listAllAttiviDiFattorino(utenteInstance));
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Attenzione! Si è verificato un errore.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		request.getRequestDispatcher("/fattorino/resultFattorino.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setto come attribuo la lista di ordini a carico dell user della sessione con
		// attivo = true
		Utente utenteInstance = (Utente) request.getSession().getAttribute("userInfo");
		try {
			utenteInstance = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(utenteInstance.getId());

			request.setAttribute("ordini_list_att",
					MyServiceFactory.getOrdineServiceInstance().listAllAttiviDiFattorino(utenteInstance));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione! Si è verificato un errore.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

		request.getRequestDispatcher("/fattorino/resultFattorino.jsp").forward(request, response);

	}

}

package it.prova.pizzastore.web.servlet.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteLoginServlet
 */
@WebServlet("/ExecuteLoginServlet")
public class ExecuteLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteLoginServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// binding
		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");

		// Controllo che i dati messi in input siano accettabili
		if (StringUtils.isEmpty(loginInput) || StringUtils.isEmpty(passwordInput)) {
			request.setAttribute("errorMessage", "E' necessario riempire tutti i campi.");
			request.getRequestDispatcher("login.jsp").forward(request, response);

			return;
		}

		try {
			// Eueguo il metodo accedi cher returna l utente loggato
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance().accedi(loginInput, passwordInput);
			// Controllo se esiste
			if (utenteInstance == null) {
				request.setAttribute("errorMessage", "Utente non trovato.");
				request.getRequestDispatcher("login.jsp").forward(request, response);

				return;

			} else {
				// setto come Parametro della sessione l utente loggato
				request.getSession().setAttribute("userInfo", utenteInstance);

				// controllo se
				// é un admin, lò mando a homeAdmin.jsp
				if (UtilityForm.controllaSeRuoloPresenteInRuoliDiUtente(utenteInstance.getRuoli(), "ADMIN_ROLE")) {
					request.getRequestDispatcher("ExecuteHomeAdminServlet").forward(request, response); //TODO SERVLET CHE RIPORTANO ALLA FINESTRA BASE

					return;
				}

				// è un pizzaiolo lo mando a homePizaiolo.jsp
				if (UtilityForm.controllaSeRuoloPresenteInRuoliDiUtente(utenteInstance.getRuoli(), "PIZZAIOLO_ROLE")) {
					request.getRequestDispatcher("ExecuteHomePizzaioloServlet").forward(request, response); //TODO SERVLET CHE RIPORTANO ALLA FINESTRA BASE

					return;
				}

				// è un fattorino lo mando a resultFattorino.jsp
				if (UtilityForm.controllaSeRuoloPresenteInRuoliDiUtente(utenteInstance.getRuoli(), "FATTORINO_ROLE")) {
					request.getRequestDispatcher("resultFattorino").forward(request, response);  //TODO SERVLET CHE RIPORTANO ALLA FINESTRA BASE

					return;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("errorMessage", "Attenzione! Si è verificato un errore.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}

	}

}

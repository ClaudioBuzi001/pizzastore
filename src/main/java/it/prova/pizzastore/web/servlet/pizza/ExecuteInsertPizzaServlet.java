package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteInsertPizzaServlet
 */
@WebServlet("/ExecuteInsertPizzaServlet")
public class ExecuteInsertPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteInsertPizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//binding
		String descrizioneInput = request.getParameter("descrizione");
		String ingredientiInput = request.getParameter("ingredienti");
		String prezzoBaseInput = request.getParameter("prezzoBase");
		
		Pizza pizzaDaInserire = UtilityForm.createPizzaFromParams(descrizioneInput, ingredientiInput, prezzoBaseInput);
		
		if (!UtilityForm.validatePizzaBean(pizzaDaInserire)) {
			request.setAttribute("pizza_inserire_att", pizzaDaInserire);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/pizza/insert.jsp").forward(request, response);
			return;
		}
		
		try {
			MyServiceFactory.getPizzaServiceInstance().inserisciNuovo(pizzaDaInserire);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizza/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListPizzeServlet?operationResult=SUCCESS");
	}

}

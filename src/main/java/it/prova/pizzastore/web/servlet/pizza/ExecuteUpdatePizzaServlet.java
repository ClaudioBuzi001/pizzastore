package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteUpdatePizzaServlet
 */
@WebServlet("/ExecuteUpdatePizzaServlet")
public class ExecuteUpdatePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdatePizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descrizioneInput = request.getParameter("descrizione");
		String ingredientiInput = request.getParameter("ingredienti");
		String prezzoBaseInput = request.getParameter("prezzoBase");
		
		Pizza pizzaToBeUpdated = UtilityForm.createPizzaFromParams(descrizioneInput, ingredientiInput,
				prezzoBaseInput);

		String idPizzaInput = request.getParameter("idPizza");
		
		if (!NumberUtils.isCreatable(idPizzaInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizza/list.jsp").forward(request, response);
			return;
		}
		
		pizzaToBeUpdated.setId(Long.parseLong(idPizzaInput));
		
		if (!UtilityForm.validatePizzaBean(pizzaToBeUpdated)) {
			request.setAttribute("pizza_modifica_att", pizzaToBeUpdated);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/pizza/edit.jsp").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getPizzaServiceInstance().aggiorna(pizzaToBeUpdated);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizza/edit.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListPizzeServlet?operationResult=SUCCESS");
	

	}

}

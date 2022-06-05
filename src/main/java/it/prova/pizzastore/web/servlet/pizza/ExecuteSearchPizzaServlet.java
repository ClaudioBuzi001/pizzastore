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
 * Servlet implementation class ExecuteSearchPizzaServlet
 */
@WebServlet("/ExecuteSearchPizzaServlet")
public class ExecuteSearchPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchPizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descrizioneInput = request.getParameter("descrizione");
		String ingredientiInput = request.getParameter("ingredienti");
		String prezzoBaseInput = request.getParameter("prezzoBase");
		
		Pizza example = UtilityForm.createPizzaFromParams(descrizioneInput, ingredientiInput,
				prezzoBaseInput);

		try {
			request.setAttribute("list_pizza_atte",
					MyServiceFactory.getPizzaServiceInstance().cerca(example));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizza/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/pizza/list.jsp").forward(request, response);
	
	}

}

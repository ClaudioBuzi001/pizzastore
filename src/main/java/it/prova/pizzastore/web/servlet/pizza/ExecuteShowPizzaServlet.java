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

/**
 * Servlet implementation class ExecuteShowPizzaServlet
 */
@WebServlet("/ExecuteShowPizzaServlet")
public class ExecuteShowPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteShowPizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPizzaInput = request.getParameter("idPizza");
		if (!NumberUtils.isCreatable(idPizzaInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizza/list.jsp").forward(request, response);
			return;
		}

		try {
			Pizza pizzaInstance = MyServiceFactory.getPizzaServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idPizzaInput));

			if (pizzaInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListPizzeServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			request.setAttribute("pizza_visualizza_att", pizzaInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizze/home.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/pizza/show.jsp").forward(request, response);
	
	}

	

}

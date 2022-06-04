package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeletePizzaServlet
 */
@WebServlet("/ExecuteDeletePizzaServlet")
public class ExecuteDeletePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteDeletePizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idPizzaInput = request.getParameter("idPizza");
		
		if (!NumberUtils.isCreatable(idPizzaInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizza/delete.jsp").forward(request, response);
			return;
		}

		try {

			MyServiceFactory.getPizzaServiceInstance().rimuoviLogico(Long.parseLong(idPizzaInput));
			request.setAttribute("list_pizza_att", MyServiceFactory.getPizzaServiceInstance().listAllElements());
			
			request.setAttribute("successMessage", "Operazione effettuata con successo");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizza/list.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/pizza/list.jsp").forward(request, response);
	}


}

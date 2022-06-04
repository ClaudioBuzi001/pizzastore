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
 * Servlet implementation class PrepareUpdatePizzaServlet
 */
@WebServlet("/PrepareUpdatePizzaServlet")
public class PrepareUpdatePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareUpdatePizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idPizzaInput = request.getParameter("idPizza");
		
		if (!NumberUtils.isCreatable(idPizzaInput)) {
			request.setAttribute("errorMessage", "Attenzione impossibile modificare questo elemento.");
			request.getRequestDispatcher("/pizza/list.jsp").forward(request, response);
			return;
		}

		Pizza pizza = new Pizza();

		try {
			pizza = MyServiceFactory.getPizzaServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idPizzaInput));
			
			request.setAttribute("pizza_modifica_att", pizza);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizza/list.jsp").forward(request, response);
			return;
		}

		
		request.getRequestDispatcher("/pizza/edit.jsp").forward(request, response);
	
	
	}


}

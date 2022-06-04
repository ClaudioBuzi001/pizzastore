package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteSearchClienteServlet
 */
@WebServlet("/ExecuteSearchClienteServlet")
public class ExecuteSearchClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteSearchClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String indirizzoParam = request.getParameter("indirizzo");
		Cliente example = UtilityForm.createClienteFromParams(nomeParam, cognomeParam, indirizzoParam);

		try {
			request.setAttribute("list_clienti_att",
					MyServiceFactory.getClienteServiceInstance().cerca(example));
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Impossibile trovare clienti corrispondenti.");
			request.getRequestDispatcher("/cliente/search.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/cliente/list.jsp").forward(request, response);
	}

	

}

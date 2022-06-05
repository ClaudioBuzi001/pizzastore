package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareUpdateOrdineServlet
 */
@WebServlet("/PrepareUpdateOrdineServlet")
public class PrepareUpdateOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareUpdateOrdineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdineInput = request.getParameter("idOrdine");
		
		if (!NumberUtils.isCreatable(idOrdineInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/homePizzaiolo.jsp").forward(request, response);
			return;
		}

		try {
			request.setAttribute("ordine_update_att",
					MyServiceFactory.getOrdineServiceInstance().caricaSingoloElemento(Long.parseLong(idOrdineInput)));
			
			request.setAttribute("list_utenti_att", MyServiceFactory.getUtenteServiceInstance().listAll());
			request.setAttribute("list_pizze_att", MyServiceFactory.getPizzaServiceInstance().listAllElements());
			request.setAttribute("list_clienti_att",
					MyServiceFactory.getClienteServiceInstance().listAllElements());
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/ordine/edit.jsp").forward(request, response);

	}

	
}

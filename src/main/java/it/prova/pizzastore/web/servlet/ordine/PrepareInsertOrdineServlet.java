package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareInsertOrdineServlet
 */
@WebServlet("/PrepareInsertOrdineServlet")
public class PrepareInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareInsertOrdineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("ordine_insert_att", new Ordine());
			
			request.setAttribute("fattorini_list_att",
					MyServiceFactory.getUtenteServiceInstance().trovaTuttiPerRuolo(MyServiceFactory.getRuoloServiceInstance()
							.cercaPerDescrizioneECodice("Fattorino", "FATTORINO_ROLE")));
			
			request.setAttribute("pizze_list_att", MyServiceFactory.getPizzaServiceInstance().listAllElements());
			
			request.setAttribute("clienti_list_att",
					MyServiceFactory.getClienteServiceInstance().listAllAttivi());
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/homePizzaiolo.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/ordine/insert.jsp").forward(request, response);
	}


}

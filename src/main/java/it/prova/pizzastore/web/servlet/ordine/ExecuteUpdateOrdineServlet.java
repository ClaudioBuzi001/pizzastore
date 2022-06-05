package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteUpdateOrdineServlet
 */
@WebServlet("/ExecuteUpdateOrdineServlet")
public class ExecuteUpdateOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteUpdateOrdineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceInput = request.getParameter("codice");
		String dataInput = request.getParameter("data");

		String utenteIdInput = request.getParameter("fattorino");
		String clienteIdInput = request.getParameter("cliente");

		String[] pizzeIdInput = request.getParameterValues("pizza");

		Ordine ordineInstance = new Ordine();

		try {

			// provare a utilizzare questo metodo pero non settando subito le pizze.

			// Mi costruisco l oggetto senza pizze, lo inserisco, poi aggiungo le pizze, e
			// lo aggiorno

			ordineInstance = UtilityForm.createOrdineFromParams(codiceInput, dataInput, clienteIdInput, utenteIdInput);

			if (!UtilityForm.validateOrdineBean(ordineInstance)) {

				request.setAttribute("ordine_update_att", ordineInstance);
				request.setAttribute("list_utenti_att",
						MyServiceFactory.getUtenteServiceInstance().trovaTuttiPerRuolo(MyServiceFactory
								.getRuoloServiceInstance().cercaPerDescrizioneECodice("Fattorino", "FATTORINO_ROLE")));

				request.setAttribute("list_pizze_att", MyServiceFactory.getPizzaServiceInstance().listAllElements());

				request.setAttribute("list_clienti_att", MyServiceFactory.getClienteServiceInstance().listAllAttivi());

				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/ordine/edit.jsp").forward(request, response);

				return;
			}

			// Una volta inserito setto come attributo request la somma totale ordine
			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineInstance);
			
			//ora mi setto la lista di pizze come proprieta di ordineInstance
			UtilityForm.setPizzeAdOrdine(ordineInstance, pizzeIdInput);
			
			//aggiorno
			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineInstance);
			
			Integer costo = MyServiceFactory.getOrdineServiceInstance().calcolaPrezzoOrdine(ordineInstance);
			ordineInstance.setCostoTotaleOrdine(Double.parseDouble(costo.toString()));

			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineInstance);
			
			request.setAttribute("sommaTotale",costo);


		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/ordine/edit.jsp").forward(request, response);

			return;
		}

		// rindirizzo l utente nella pagina executeShowPrezzoOrdineServlet
		response.sendRedirect("ExecuteListOrdineServlet?operationResult=SUCCESS");
	
	}

}

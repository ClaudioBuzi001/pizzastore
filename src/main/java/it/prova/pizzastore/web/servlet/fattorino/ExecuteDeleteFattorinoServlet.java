package it.prova.pizzastore.web.servlet.fattorino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteFattorinoServlet
 */
@WebServlet("/ExecuteDeleteFattorinoServlet")
public class ExecuteDeleteFattorinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteDeleteFattorinoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdineInput = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idOrdineInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/fattorino/resultFattorino.jsp").forward(request, response);
			return;
		}
		

		try {
			Ordine ordineInstance = MyServiceFactory.getOrdineServiceInstance().caricaSingoloElemento(Long.parseLong(idOrdineInput));
			ordineInstance.setClosed(true);
			
			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineInstance);
			
			Utente utenteInstance = (Utente) request.getSession().getAttribute("userInfo");
			
			
			utenteInstance = MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(utenteInstance.getId());

			request.setAttribute("ordini_list_att",
					MyServiceFactory.getOrdineServiceInstance().listAllAttiviDiFattorino(utenteInstance));
			
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/fattorino/resultFattorino.jsp").forward(request, response);
	
	}

}

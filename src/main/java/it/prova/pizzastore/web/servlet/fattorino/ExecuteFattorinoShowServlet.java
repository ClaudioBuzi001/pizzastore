package it.prova.pizzastore.web.servlet.fattorino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteFattorinoShowServlet
 */
@WebServlet("/ExecuteFattorinoShowServlet")
public class ExecuteFattorinoShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteFattorinoShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdineInput = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idOrdineInput)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/fattorino/resultFattorino.jsp").forward(request, response);
			return;
		}

		try {
			Ordine ordineInstance = MyServiceFactory.getOrdineServiceInstance()
					.caricaSingoloElementoConTutto(Long.parseLong(idOrdineInput));

			if (ordineInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListFattorinoServlet").forward(request,
						response);
				return;
			}

			request.setAttribute("ordine_show_att", ordineInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("ExecuteListFattorinoServlet").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/fattorino/show.jsp").forward(request, response);
	

	}


}

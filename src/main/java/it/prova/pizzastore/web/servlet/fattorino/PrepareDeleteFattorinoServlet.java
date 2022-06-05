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
 * Servlet implementation class PrepareDeleteFattorinoServlet
 */
@WebServlet("/PrepareDeleteFattorinoServlet")
public class PrepareDeleteFattorinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepareDeleteFattorinoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdineParam = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idOrdineParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/fattorino/resultFattorino.jsp").forward(request, response);
			return;
		}

		try {
			Ordine ordineInstance = MyServiceFactory.getOrdineServiceInstance()
					.caricaSingoloElementoConTutto(Long.parseLong(idOrdineParam));

			if (ordineInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListFattorinoSerlvet").forward(request,
						response);
				return;
			}

			request.setAttribute("ordine_delete_att", ordineInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("ExecuteListFattorinoSerlvet").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/fattorino/delete.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

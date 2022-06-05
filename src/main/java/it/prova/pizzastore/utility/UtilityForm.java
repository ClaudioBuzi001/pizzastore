package it.prova.pizzastore.utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;

public class UtilityForm {

	public static Pizza createPizzaFromParams(String descrizioneParam, String ingredientiParam,
			String prezzoBaseParam) {

		Pizza result = new Pizza(descrizioneParam, ingredientiParam);
		if (NumberUtils.isCreatable(prezzoBaseParam)) {
			result.setPrezzoBase(Integer.parseInt(prezzoBaseParam));
		}
		return result;
	}

	public static boolean validatePizzaBean(Pizza pizzaToBeValidated) {
		if (pizzaToBeValidated == null || StringUtils.isBlank(pizzaToBeValidated.getDescrizione())
				|| StringUtils.isBlank(pizzaToBeValidated.getIngredienti())
				|| pizzaToBeValidated.getPrezzoBase() == null || pizzaToBeValidated.getPrezzoBase() < 1) {

			return false;
		} else {

			return true;
		}
	}

	public static Cliente createClienteFromParams(String nomeParam, String cognomeParam, String indirizzoParam) {
		Cliente result = new Cliente(nomeParam, cognomeParam, indirizzoParam);

		return result;
	}

	public static boolean validateClienteBean(Cliente clienteToBeInserted) {
		if (clienteToBeInserted == null || StringUtils.isBlank(clienteToBeInserted.getCognome())
				|| StringUtils.isAllBlank(clienteToBeInserted.getNome())
				|| StringUtils.isAllBlank(clienteToBeInserted.getIndirizzo())) {

			return false;
		}

		return true;
	}

	public static Ordine createOrdineFromParams(String codiceInput, String dataInput, String clienteIdInput,
			String utenteIdInput) throws NumberFormatException, Exception {
		Ordine result = new Ordine();
		result.setCodice(codiceInput);

		result.setData(new SimpleDateFormat("yyyy-MM-dd").parse(dataInput));

		if (NumberUtils.isCreatable(clienteIdInput)) {
			Cliente cliente = new Cliente();
			cliente.setId(Long.parseLong(clienteIdInput));

			result.setCliente(cliente);
		}
		if (NumberUtils.isCreatable(utenteIdInput)) {
			Utente utente = new Utente();
			utente.setId(Long.parseLong(utenteIdInput));

			result.setUtente(utente);
		}
		return result;
	}

	//metodo statico che passata una lista di id di pizze, le setta come proprieta di ordineInstance
	public static Ordine setPizzeAdOrdine(Ordine ordineInstance, String[] idPizze) throws Exception {
		List<Pizza> listaPizze = new ArrayList<Pizza>();

		if (idPizze == null || idPizze.length == 0) {
			ordineInstance.setPizze(null);

		} else {
			Double somma = 0.0;
			for (String idPizza : idPizze) {
				if (NumberUtils.isCreatable(idPizza)) {
					Pizza pizzaItem = MyServiceFactory.getPizzaServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idPizza));  //FIXME

					// TODO utilizzaere il metodo aggiungiPizza
					

					MyServiceFactory.getOrdineServiceInstance().aggiungiPizza(ordineInstance, pizzaItem); // fixME
				}

			}
			//ordineInstance.setPizze(listaPizze);

		}
		return ordineInstance;
	}
	
	
	public static boolean validateOrdineBean(Ordine ordineInstance) {
		if (ordineInstance == null || StringUtils.isBlank(ordineInstance.getCodice())
				|| ordineInstance.getData() == null || ordineInstance.getCliente() == null
				|| ordineInstance.getCliente().getId() < 1 || ordineInstance.getUtente() == null
				|| ordineInstance.getUtente().getId() < 1) {

			return false;
		}
		return true;
	}

	// Mi serve un metodo che prende il set di ruoli di un utente e una
	// Stringa(codice ruolo)

	// Verifica se all intereno di quel set c è un ruolo che ha come codice quella
	// stringa

	// METODO STATICO UTILITY CHE RESITUISCE UN BOOLEAN
	public static boolean controllaSeRuoloPresenteInRuoliDiUtente(Set<Ruolo> ruoli, String codiceRuolo) {

		for (Ruolo ruoloItem : ruoli) {
			if (ruoloItem.getCodice().equals(codiceRuolo))

				return true;
		}

		return false;

	}

}

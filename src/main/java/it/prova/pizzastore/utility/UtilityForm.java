package it.prova.pizzastore.utility;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Ruolo;

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

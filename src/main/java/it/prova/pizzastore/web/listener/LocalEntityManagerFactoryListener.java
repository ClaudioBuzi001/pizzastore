package it.prova.pizzastore.web.listener;

import java.util.Date;
import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.StatoUtente;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.service.RuoloService;
import it.prova.pizzastore.service.UtenteService;

/**
 * Application Lifecycle Listener implementation class
 * LocalEntityManagerFactoryListener
 *
 */
@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {
	private static EntityManagerFactory entityManagerFactory;

	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("pizzastore_unit");
		
			initUsersAndRuoli();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return entityManagerFactory.createEntityManager();

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			try {
				if (em.isOpen()) {
					em.close();
				}
			} catch (PersistenceException ex) {
				System.err.println("Could not close JPA EntityManager" + ex);
			} catch (Throwable ex) {
				System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
			}
		}
	}

	private void initUsersAndRuoli() throws Exception {
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ADMIN_ROLE"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", "PIZZAIOLO_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo", "PIZZAIOLO_ROLE"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", "FATTORINO_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino", "FATTORINO_ROLE"));
		}

		// se il metodocheck returna false, allora ci creaiamo un nuovo admin
		if (!utenteServiceInstance.checkSeCeAlmenoUn(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE"))) {

			HashSet<Ruolo> ruolo = new HashSet<Ruolo>();
			ruolo.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE"));

			Utente admin = new Utente("admin", "admin", "Clodio", "Marcelli", new Date(), StatoUtente.ATTIVO, ruolo);

			utenteServiceInstance.inserisciNuovo(admin);

		}

		if (!utenteServiceInstance.checkSeCeAlmenoUn(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", "PIZZAIOLO_ROLE"))) {
			HashSet<Ruolo> ruolo = new HashSet<Ruolo>();
			ruolo.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", "PIZZAIOLO_ROLE"));

			Utente pizzaiolo = new Utente("pizzaiolo", "pizzaiolo", "Giorgione", "Nazionale", new Date(),
					StatoUtente.ATTIVO, ruolo);

			utenteServiceInstance.inserisciNuovo(pizzaiolo);

		}

		if (!utenteServiceInstance.checkSeCeAlmenoUn(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", "FATTORINO_ROLE"))) {
			HashSet<Ruolo> ruolo = new HashSet<Ruolo>();
			ruolo.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", "FATTORINO_ROLE"));

			Utente fattorino = new Utente("fattorino", "fattorino", "Lucilla", "Basilicata", new Date(),
					StatoUtente.ATTIVO, ruolo);

			utenteServiceInstance.inserisciNuovo(fattorino);
		}
	}
	
	
	
//	private void initAdminUserAndRuoli() throws Exception {
//		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
//		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();
//
//		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE") == null) {
//		ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ADMIN_ROLE"));
//		}
//
//		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo User", "PIZZAIOLO_ROLE") == null) {
//		ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo User", "PIZZAIOLO_ROLE"));
//		}
//
//		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino User", "FATTORINO_ROLE") == null) {
//		ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino User", "FATTORINO_ROLE"));
//		}
//
//		if (utenteServiceInstance.findByUsernameAndPassword("admin", "admin") == null) {
//		Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date());
//		admin.setStato(StatoUtente.ATTIVO);
//		utenteServiceInstance.inserisciNuovo(admin);
//		utenteServiceInstance.aggiungiRuolo(admin,
//		ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE"));
//		}
//
//		if (utenteServiceInstance.findByUsernameAndPassword("pizzaiolouser", "pizzaiolouser") == null) {
//		Utente pizzaiolouser = new Utente("pizzaiolouser", "pizzaiolouser", "Vittorio", "Scatafrulli", new Date());
//		pizzaiolouser.setStato(StatoUtente.ATTIVO);
//		utenteServiceInstance.inserisciNuovo(pizzaiolouser);
//		utenteServiceInstance.aggiungiRuolo(pizzaiolouser,
//		ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo User", "PIZZAIOLO_ROLE"));
//		}
//
//		if (utenteServiceInstance.findByUsernameAndPassword("fattorinouser", "fattorinouser") == null) {
//		Utente fattorinouser = new Utente("fattorinouser", "fattorinouser", "Maria", "Italia", new Date());
//		fattorinouser.setStato(StatoUtente.ATTIVO);
//		utenteServiceInstance.inserisciNuovo(fattorinouser);
//		utenteServiceInstance.aggiungiRuolo(fattorinouser,
//		ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino User", "FATTORINO_ROLE"));
//		}
//
//		if (utenteServiceInstance.findByUsernameAndPassword("utentefattorino", "utentefattorino") == null) {
//		Utente utentefattorino = new Utente("utentefattorino", "utentefattorino", "Franco", "Gallinari", new Date());
//		utentefattorino.setStato(StatoUtente.ATTIVO);
//		utenteServiceInstance.inserisciNuovo(utentefattorino);
//		utenteServiceInstance.aggiungiRuolo(utentefattorino,
//		ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino User", "FATTORINO_ROLE"));
//		}
//		}


}

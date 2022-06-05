package it.prova.pizzastore.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.OrdineDAO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDAO ordineDAO;

	@Override
	public List<Ordine> listAllElements() throws Exception {

		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.findOne(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine caricaSingoloElementoConUtenteECliente(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.findOneEagerClienteEUtente(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine caricaSingoloElementoConTutto(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {

			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.findOneEagerAll(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDAO.setEntityManager(entityManager);

			ordineDAO.update(ordineInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDAO.setEntityManager(entityManager);

			ordineDAO.insert(ordineInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idOrdine) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOrdineDAO(OrdineDAO ordineDAO) {
		this.ordineDAO = ordineDAO;

	}

	@Override
	public Integer calcolaPrezzoOrdine(Ordine ordineInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		Integer result = 0;
		try {
			entityManager.getTransaction().begin();

			ordineDAO.setEntityManager(entityManager);

			// inserisco il nuovo ordine
			

			// chiamo il metodo somma dirttamente su ordineInstance, usando le proprietà che
			// ci da la finestra transazionale
			result = ordineDAO.sumPizzeOrdine(ordineDAO.findOneEagerAll(ordineInstance.getId()));

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
			return result;

		}
		

	}
	
	public void aggiungiPizza(Ordine ordineInstance, Pizza pizza)  throws Exception{
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			ordineDAO.setEntityManager(entityManager);

			// inserisco il nuovo ordine
			ordineInstance = entityManager.merge(ordineInstance);
			
			pizza = entityManager.merge(pizza);
			
			ordineInstance.getPizze().add(pizza);
			

			// chiamo il metodo somma dirttamente su ordineInstance, usando le proprietà che
			// ci da la finestra transazionale

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);

		}
	}

}

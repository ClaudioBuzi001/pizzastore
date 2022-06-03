package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.pizzastore.model.Cliente;


public class ClienteDAOImpl implements ClienteDAO {

	private EntityManager entityManager;

	@Override
	public List<Cliente> list() throws Exception {
		return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
	}

	@Override
	public Optional<Cliente> findOne(Long id) throws Exception {
		Cliente result = entityManager.find(Cliente.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Cliente input) throws Exception {
		if (input == null)
			throw new Exception("Attenzione errore in input");

		input = entityManager.merge(input);
	}

	@Override
	public void insert(Cliente input) throws Exception {
		if (input == null)
			throw new Exception("Attenzione errore in input");

		entityManager.persist(input);

	}

	@Override
	public void delete(Cliente input) throws Exception {
		// TODO

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	@Override
	public Cliente findOneEager(Long id) throws Exception {
		TypedQuery<Cliente> query = entityManager
				.createQuery("select c from Cliente c left join fetch c.ordini o where c.id = :idInp", Cliente.class);
		query.setParameter("idInp", id);

		return query.getResultStream().findFirst().orElse(null);
	}
	
	
	//voglio un metodo che mi returna la lista di tutti i clienti attivi
	@Override
	public List<Cliente> findAllAttivi() throws Exception{
		
		return entityManager.createQuery("select c from Cliente c where c.attivo = true", Cliente.class).getResultList();
		
	}

}

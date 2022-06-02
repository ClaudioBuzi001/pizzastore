package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.pizzastore.model.Pizza;

public class PizzaDAOImpl implements PizzaDAO {

	private EntityManager entityManager;

	@Override
	public List<Pizza> list() throws Exception {
		return entityManager.createQuery("from Pizza", Pizza.class).getResultList();
	}

	@Override
	public Optional<Pizza> findOne(Long id) throws Exception {
		Pizza result = entityManager.find(Pizza.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Pizza input) throws Exception {
		if (input == null)
			throw new Exception("Errore valore in input");

		input = entityManager.merge(input);

	}

	@Override
	public void insert(Pizza input) throws Exception {
		if (input == null)
			throw new Exception("ERRORE problema in input");

		entityManager.persist(input);
	}

	@Override
	public void delete(Pizza input) throws Exception {
		// TODO

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;

	}

	@Override
	public Pizza findOneEager(Long id) throws Exception {

		TypedQuery<Pizza> query = entityManager
				.createQuery("select p from Pizza p left join fetch p.ordini o where p.id = :idInp", Pizza.class);
		query.setParameter("idInp", id);

		return query.getResultStream().findFirst().orElse(null);
	}

}

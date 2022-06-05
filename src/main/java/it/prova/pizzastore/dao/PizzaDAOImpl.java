package it.prova.pizzastore.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Pizza;

public class PizzaDAOImpl implements PizzaDAO {

	private EntityManager entityManager;

	@Override
	public List<Pizza> list() throws Exception {
		return entityManager.createQuery("from Pizza p where p.attivo = true", Pizza.class).getResultList();
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

	@Override
	public void deleteLogico(Pizza pizzaInstance) throws Exception {
		if(pizzaInstance == null)
			throw new Exception("Input non valido");
		
		pizzaInstance.setAttivo(false);
		
		entityManager.merge(pizzaInstance);
		
	}

	@Override
	public List<Pizza> findByExample(Pizza example) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select p from Pizza p where p.attivo = true ");

		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" p.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (StringUtils.isNotEmpty(example.getIngredienti())) {
			whereClauses.add(" p.ingredienti like :ing ");
			paramaterMap.put("ing", "%" + example.getIngredienti() + "%");
		}
		
		if (example.getPrezzoBase() != null && example.getPrezzoBase() > 0) {
			whereClauses.add(" p.prezzobase =:prezzoBase ");
			paramaterMap.put("prezzoBase", example.getPrezzoBase());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));

		TypedQuery<Pizza> typedQuery = entityManager.createQuery(queryBuilder.toString(), Pizza.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	
	}

}

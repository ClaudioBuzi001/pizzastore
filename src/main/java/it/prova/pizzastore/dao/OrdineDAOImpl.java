package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.pizzastore.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Optional<Ordine> findOne(Long id) throws Exception {

		Ordine result = entityManager.find(Ordine.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Ordine input) throws Exception {
		if(input == null)
			throw new Exception("ERRORE IN INPUT");
		
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		if(input == null)
			throw new Exception("ERRORE IN INPUT");
		
		entityManager.persist(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Ordine findOneEagerClienteEUtente(Long id) throws Exception {
		TypedQuery<Ordine> query = entityManager.createQuery("select o from Ordine o left join fetch o.cliente left join fetch o.utente where o.id = :idInp", Ordine.class );
		query.setParameter("idInp", id);
		
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public Ordine findOneEagerAll(Long id) throws Exception {
		TypedQuery<Ordine> query = entityManager.createQuery("select o from Ordine o left join fetch o.cliente left join fetch o.utente left join fetch o.pizze where o.id = :idInp", Ordine.class );
		query.setParameter("idInp", id);
		
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public Integer sumPizzeOrdine(Ordine ordineInstance) throws Exception {
		TypedQuery<Long> query = entityManager.createQuery("select sum(p.prezzoBase) from Ordine o join o.pizze p where o.id = :id", Long.class);
		
		return query.setParameter("id", ordineInstance.getId()).getSingleResult().intValue();
	}

}

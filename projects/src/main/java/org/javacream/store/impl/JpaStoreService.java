package org.javacream.store.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.javacream.store.api.StoreService;
import org.springframework.stereotype.Component;

@Component
public class JpaStoreService implements StoreService{

	@PersistenceContext private EntityManager entityManager;
	@Override
	public int getStock(String category, String id) {
		try {
			Query query = entityManager.createQuery("select stock from StoreEntry where category = :category and itemId = :id");
			query.setParameter("category", category);
			query.setParameter("id", id);
			Integer result = (Integer) query.getSingleResult();
			return result;
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	@Transactional
	public void setStock(String category, String id, int stock) {
		if (category == null || id == null) {
			throw new IllegalArgumentException("category and id must not be null");
		}
		StoreId storeId = new StoreId(category, id);
		
		StoreEntry storeEntry = entityManager.find(StoreEntry.class, storeId);
		if (storeEntry == null) {
			entityManager.persist(new StoreEntry(category, id, stock));
		}else {
			entityManager.merge(new StoreEntry(category, id, stock));
		}
	}

}
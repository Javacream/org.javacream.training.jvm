package org.javacream.books.isbngenerator.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "org.javacream.training:type=IsbnGenerator")
public class DatabaseIsbnGenerator implements IsbnGenerator{

	@PersistenceContext private EntityManager entityManager;
	public DatabaseIsbnGenerator(@Value("${isbngenerator.prefix}") String prefix, @Value("${isbngenerator.suffix}")String suffix) {
		if (prefix == null || suffix ==  null) {
			throw new IllegalArgumentException ("prefix and suffix must not be null");
		}
		this.prefix = prefix;
		this.suffix = suffix;
	}
	private String prefix;
	
	@ManagedAttribute(description = "the prefix")
	public String getPrefix() {
		return prefix;
	}


	@ManagedAttribute(description = "the prefix")
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


	@ManagedAttribute(description = "the countryCode")
	public String getSuffix() {
		return suffix;
	}


	@ManagedAttribute(description = "the countryCode")
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	private String suffix;
	

	@Override
	@Transactional
	public String next() {
		Integer key = (Integer) entityManager.createNativeQuery("select col_key from keys").getSingleResult();
		key++;
		Query query = entityManager.createNativeQuery("update keys set col_key= :key");
		query.setParameter("key", key);
		query.executeUpdate();
		return prefix + key + suffix;
	}
}

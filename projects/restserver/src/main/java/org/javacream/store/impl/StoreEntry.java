package org.javacream.store.impl;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "STORE")
@IdClass(StoreId.class)
public class StoreEntry {

	@Id
	private String category;
	@Id
	private String itemId;
	private Integer stock;

	@SuppressWarnings("unused")
	private StoreEntry() {
		
	}
	
	
	public StoreEntry(String category, String itemId, Integer stock) {
		super();
		this.category = category;
		this.itemId = itemId;
		this.stock = stock;
	}


	public String getCategory() {
		return category;
	}
	public String getItemId() {
		return itemId;
	}
	public Integer getStock() {
		return stock;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoreEntry other = (StoreEntry) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StoreEntry [category=" + category + ", id=" + itemId + ", stock=" + stock + "]";
	}
}

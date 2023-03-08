package org.javacream.store.impl;

import java.io.Serializable;

public class StoreId implements Serializable{
	private static final long serialVersionUID = 1L;

	public String category;
	public String itemId;
	public StoreId(String category, String itemId) {
		super();
		this.category = category;
		this.itemId = itemId;
	}
	public StoreId() {
		super();
	}
	
}

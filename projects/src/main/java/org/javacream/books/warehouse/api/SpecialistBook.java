package org.javacream.books.warehouse.api;

import javax.persistence.Entity;

@Entity
public class SpecialistBook extends Book{

	private String topic;
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
}

package org.javacream.books.warehouse.impl.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "org.javacream.training:type=BooksService,metric=Search")
public class BooksSearchMetrics {
	
	private int success;
	private int failure;
	@ManagedAttribute
	public int getSuccess() {
		return success;
	}
	@ManagedAttribute
	public int getFailure() {
		return failure;
	}
	
	public void incSuccess() {
		success++;
	}
	public void incFailure() {
		failure++;
	}

}

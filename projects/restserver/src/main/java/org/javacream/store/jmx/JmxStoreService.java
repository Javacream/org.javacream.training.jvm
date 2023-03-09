package org.javacream.store.jmx;

import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "org.javacream.training:type=StoreService")
public class JmxStoreService {

	@Autowired private StoreService storeService;
	
	@ManagedOperation public int storeRequest(String category, String item) {
		return storeService.getStock(category, item);
	}
	
}

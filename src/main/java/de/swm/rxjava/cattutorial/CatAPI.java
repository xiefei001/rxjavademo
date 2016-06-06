/* 
 * Copyright 2016 SWM Services GmbH
 */
package de.swm.rxjava.cattutorial;

import java.net.URI;
import java.util.List;



/**
 * @author xie.fei
 * @since 1.0
 */
public interface CatAPI {

	interface CatsQueryCallback {
		void onCatListReceived(List<Cat> cats);

		void onQueryFailed(Exception e);
	}

	interface StoreCallback {
		void onCatStored(URI uri);

		void onStoreFailed(Exception e);
	}

	void queryCats(String query, CatsQueryCallback catsQueryCallback);

	void store(Cat cat, StoreCallback storeCallback);
}

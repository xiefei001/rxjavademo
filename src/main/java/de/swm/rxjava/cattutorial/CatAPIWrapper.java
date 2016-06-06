/* 
 * Copyright 2016 SWM Services GmbH
 */
package de.swm.rxjava.cattutorial;

import rx.Observable;

import java.util.List;



/**
 * @author xie.fei
 * @since 1.0
 */
public class CatAPIWrapper {
	private CatAPI catAPI;



	public Observable<List<Cat>> queryCats(String query) {
		return Observable.create((subscriber) -> {
			this.catAPI.queryCats(query, new CatAPI.CatsQueryCallback() {

				@Override
				public void onCatListReceived(List<Cat> cats) {
					subscriber.onNext(cats);
				}



				@Override
				public void onQueryFailed(Exception e) {
					subscriber.onError(e);
				}
			});
		});
	}

	public Observable<Cat> store(Cat cat) {
		return null;
	}
}

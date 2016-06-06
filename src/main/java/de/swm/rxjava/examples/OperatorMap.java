package de.swm.rxjava.examples;

import rx.functions.Func1;



/**
 * Created by xie.fei on 14.04.2016.
 */

class OperatorMap<T, R> implements Func1<R, T> {

	private final Func1<T, R> transformer;



	public OperatorMap(Func1<T, R> transformer){
		this.transformer = transformer;
	}

	@Override
	public T call(R r) {
		transformer.call(null);
		return null;
	}
}

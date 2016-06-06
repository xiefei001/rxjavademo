package de.swm.rxjava.examples;

import rx.functions.Func1;



public class DemoOperator<T> {

	public <R> R lift(OperatorMap<R, T> operatorMap, T t){
		return operatorMap.call(t);
	}

	public final <R> R map(Func1 func, final T t) {

		return lift(new OperatorMap<R, T>(func), t);
	}
}

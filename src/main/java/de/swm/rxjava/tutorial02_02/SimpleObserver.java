package de.swm.rxjava.tutorial02_02;

import rx.Observer;
import sun.java2d.pipe.SpanShapeRenderer;



/**
 * Created by xie.fei on 12.04.2016.
 */
public class SimpleObserver implements Observer<Avariable> {

	private final String name;



	public SimpleObserver(String name) {
		this.name = name;
	}
	@Override
	public void onCompleted() {
		System.out.println(name + ": added Variable" + Driver.variables.stream().mapToDouble(x -> x.getVarValue()).sum
				());
		System.out.println("--------------------------------------");
	}



	@Override
	public void onError(Throwable e) {
		System.out.println("error...");
	}



	@Override
	public void onNext(Avariable avariable) {
		System.out.println(name +":" + avariable.getVarName() +": " + avariable.getVarValue());
	}
}

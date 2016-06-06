package de.swm.rxjava.tutorial01;

import rx.Observer;



/**
 * Created by xie.fei on 12.04.2016.
 */
public class SimpleObserver implements Observer<String> {

	public void onCompleted() {
		outputThreadname();
		System.out.println("Completed...");
	}


	public void onError(Throwable throwable) {
		outputThreadname();
		System.out.println("Error...");
	}


	public void onNext(String s) {
		outputThreadname();
		System.out.println(s);
	}

	public void outputThreadname(){
		System.out.println("current Thread:" + Thread.currentThread().getName());
	}
}

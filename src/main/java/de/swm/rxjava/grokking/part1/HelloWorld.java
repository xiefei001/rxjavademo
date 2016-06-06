package de.swm.rxjava.grokking.part1;

import rx.Observable;



/**
 * Created by xie.fei on 14.04.2016.
 */
public class HelloWorld {

	public static void main(String[] args) {
		Observable<String> myObservable = Observable.create(subscriber -> {
			subscriber.onNext("Hello world!");
			subscriber.onCompleted();
		});

		myObservable.subscribe(System.out::println);
		Observable.just("Hello world!").subscribe(System.out::println);

		// Transform Unschön
		Observable.just("Hello world!").subscribe(s -> System.out.println(s +" -Dan"));
		// Transform bessere Lösung
		Observable.just("Hello world!").map(s -> s + " -Dan").subscribe(System.out::println);

	}
}

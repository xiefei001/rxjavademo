package de.swm.rxjava.tutorial01;


import rx.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Created by xie.fei on 12.04.2016.
 */
public class SimpleDemo {
	public static void main(String[] args) {
		List<String> shapeList = Arrays.asList("rectangle", "square", "triangle");
		Observable<String> observableString = Observable.from(shapeList);
		observableString.subscribe(new SimpleObserver());
	}
}

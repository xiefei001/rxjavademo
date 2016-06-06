package de.swm.rxjava.tutorial03;


import rx.Observable;

import java.util.Arrays;
import java.util.List;



/**
 * Created by xie.fei on 12.04.2016.
 */
public class SimpleDemo {
	public static void main(String[] args) {
		String[] shapeArray = { "rectangle", "square", "triangle" };
		List<String> shapeList = Arrays.asList(shapeArray);
		System.out.println("--------------------Observable.from----------------------------");
		Observable.<String>from(shapeList).subscribe(
				System.out::println,
				err->System.err.println("error"),
				() -> System.out.println("completed -List"));

		Observable.<String>from(shapeArray).subscribe(
				System.out::println,
				err->System.err.println("error"),
				() -> System.out.println("completed -Array"));
		System.out.println("--------------------Observable.just----------------------------");
		System.out.println("--------------------Observable.create----------------------------");
	}
}

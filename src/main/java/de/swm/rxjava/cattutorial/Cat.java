/* 
 * Copyright 2016 SWM Services GmbH
 */
package de.swm.rxjava.cattutorial;

/**
 * @author xie.fei
 * @since 1.0
 */
public class Cat implements  Comparable<Cat>{
	private int cuteness;


	@Override
	public int compareTo(Cat another) {
		return Integer.compare(this.cuteness, another.cuteness);
	}
}

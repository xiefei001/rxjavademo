package de.swm.rxjava.examples;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;



/**
 * Created by xie.fei on 14.04.2016.
 */
public class ReactiveStream {

	public static final Logger LOG = Logger.getLogger(ReactiveStream.class);

	public static void main(String[] args) throws InterruptedException {
		BasicConfigurator.configure();
		Observable.just(1, 2, 3, 4, 5, 6).filter(i -> i % 2 == 0).subscribe(System.out::print);

		Scheduler scheduler = Schedulers.computation();
		Observable.just("Hi!").map(s -> {LOG.info("length: " + s);return s.length();}).observeOn(scheduler).subscribe
				(v ->	LOG.info
				("v: " + v));
		Thread.sleep(1000);

	}
}

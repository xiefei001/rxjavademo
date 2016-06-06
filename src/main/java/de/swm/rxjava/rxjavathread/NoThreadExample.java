package de.swm.rxjava.rxjavathread;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;



/**
 * Created by xie.fei on 14.04.2016.
 */
public class NoThreadExample {

	private static final Logger LOG = Logger.getLogger(NoThreadExample.class);



	public static void main(String[] args) throws InterruptedException {
		BasicConfigurator.configure();

		CountDownLatch latch = new CountDownLatch(1);

		Observable<Integer> generator = Observable.<Integer> range(1, 5).lift(new DebugOperator<Integer>("Generated"));


		Observable<Integer> shiftedUp = generator.observeOn(Schedulers.io()).map(x -> x + 10)
				.lift(new DebugOperator<>("Shifted Up"));

		Observable<Integer> shiftedDown = shiftedUp.observeOn(Schedulers.computation()).map(x -> x - 10)
				.lift(new DebugOperator<>("Shifted " + "Down"));
		shiftedDown.subscribe(x -> {
			LOG.info("Received: " + x + "\n");
			latch.countDown();
		});


		latch.await();
	}
}

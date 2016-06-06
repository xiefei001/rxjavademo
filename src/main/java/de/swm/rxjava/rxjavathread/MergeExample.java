package de.swm.rxjava.rxjavathread;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.Schedulers;



/**
 * Created by xie.fei on 14.04.2016.
 */
public class MergeExample {

	private static final Logger LOG = Logger.getLogger(MergeExample.class);


/*
   output:

0 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	1
0 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	1
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #1: 	11
2 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #2: 	11
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.MergeExample  - Received: 11

2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	2
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #1: 	12
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.MergeExample  - Received: 12

2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	3
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #1: 	13
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.MergeExample  - Received: 13

2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	4
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #1: 	14
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.MergeExample  - Received: 14

2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	5
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #1: 	15
3 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.MergeExample  - Received: 15
 */


	public static void main(String[] args) throws InterruptedException {
		BasicConfigurator.configure();

		CountDownLatch latch = new CountDownLatch(1);

		Observable<Integer> generator = Observable.<Integer> range(1, 5).lift(new DebugOperator<Integer>("Generated")
		).subscribeOn(Schedulers.io());

		Observable<Integer> generator1 = generator.map(x -> x + 10).lift(new DebugOperator<>("Shifted Up #1"));
		Observable<Integer> generator2 = generator.map(x -> x + 10).lift(new DebugOperator<>("Shifted Up #2"));

		Observable<Integer> shiftedDown = Observable.merge(generator1, generator2);

		shiftedDown.subscribe(x -> {
			LOG.info("Received: " + x + "\n");
			latch.countDown();
		});
		latch.await();
	}
}

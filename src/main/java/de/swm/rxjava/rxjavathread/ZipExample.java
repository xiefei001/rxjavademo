package de.swm.rxjava.rxjavathread;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.Schedulers;



/**
 * Created by xie.fei on 14.04.2016.
 */
public class ZipExample {

	private static final Logger LOG = Logger.getLogger(ZipExample.class);


/*
output:
0 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	1
0 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	1
1 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #1: 	11
1 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #2: 	11
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	2
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #1: 	12
2 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Zipped: 	11
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	3
2 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Down: 	1
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #1: 	13
2 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.ZipExample  - Received: 1

2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	4
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #1: 	14
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	5
2 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	2
2 [RxCachedThreadScheduler-1] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #1: 	15
2 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Up #2: 	12
2 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Zipped: 	12
2 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Shifted Down: 	2
2 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.ZipExample  - Received: 2

2 [RxCachedThreadScheduler-2] INFO de.swm.rxjava.rxjavathread.DebugOperator  - Generated: 	3
 */
	public static void main(String[] args) throws InterruptedException {
		BasicConfigurator.configure();

		CountDownLatch latch = new CountDownLatch(1);

		Observable<Integer> generator = Observable.<Integer> range(1, 5).lift(new DebugOperator<Integer>("Generated")
		).subscribeOn(Schedulers.io());

		Observable<Integer> generator1 = generator.map(x -> x + 10).lift(new DebugOperator<>("Shifted Up #1"));
		Observable<Integer> generator2 = generator.map(x -> x + 10).lift(new DebugOperator<>("Shifted Up #2"));

		Func2<Integer, Integer,Integer> func = (i1, i2) -> i1;
		Observable<Integer> zipped = generator1.zipWith(generator2, func).lift(new DebugOperator<>("Zipped"));

		Observable<Integer> shiftedDown = zipped.map(x -> x - 10).lift(new DebugOperator<>("Shifted Down"));


		shiftedDown.subscribe(x -> {
			LOG.info("Received: " + x + "\n");
			latch.countDown();
		});
		latch.await();
	}
}

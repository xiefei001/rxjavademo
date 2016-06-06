package de.swm.rxjava.rxjavathread;

import org.apache.log4j.Logger;
import rx.Observable;
import rx.Subscriber;



/**
 * Created by xie.fei on 14.04.2016.
 */
public class DebugOperator<T> implements Observable.Operator<T, T> {

	public static final Logger LOG = Logger.getLogger(DebugOperator.class);
	private String prefix;



	public DebugOperator(String prefix) {
		this.prefix = prefix;
	}



	@Override
	public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
		return new Subscriber<T>() {
			@Override
			public void onCompleted() {
				subscriber.onCompleted();
			}

			@Override
			public void onError(Throwable e) {
				subscriber.onError(e);
			}

			@Override
			public void onNext(T t) {
				LOG.info(prefix + ": \t" + t);
				subscriber.onNext(t);
			}
		};
	}
}

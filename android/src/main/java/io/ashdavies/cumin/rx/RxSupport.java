package io.ashdavies.cumin.rx;

import android.os.Looper;

import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public final class RxSupport {
    public static Subscription scheduleUnsubscription(final Action0 unsubscribe) {
        return Subscriptions.create(new Action0() {
            @Override public void call() {
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    unsubscribe.call();
                } else {
                    final Scheduler.Worker inner = AndroidSchedulers.mainThread().createWorker();
                    inner.schedule(new Action0() {
                        @Override public void call() {
                            unsubscribe.call();
                            inner.unsubscribe();
                        }
                    });
                }
            }
        });
    }
}

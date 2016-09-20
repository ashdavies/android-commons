package io.ashdavies.commons.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import io.ashdavies.commons.rx.RxSupport;
import java.util.List;
import java.util.Locale;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

public class Manager {

  private SensorManager manager;

  public Manager(Context context) {
    this.manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
  }

  public List<Sensor> getSensors() {
    return manager.getSensorList(Sensor.TYPE_ALL);
  }

  public boolean hasSensor(int type) {
    return manager.getDefaultSensor(type) != null;
  }

  public Observable<Event> observe(int type) {
    return observe(type, SensorManager.SENSOR_DELAY_NORMAL);
  }

  public Observable<Event> observe(final int type, final int period) {
    if (!hasSensor(type)) {
      throw new RuntimeException(
          String.format(Locale.getDefault(), "Sensor id %d not available", type));
    }

    return Observable.create(
        new Observable.OnSubscribe<Event>() {
          @Override
          public void call(final Subscriber<? super Event> subscriber) {
            final SensorEventListener listener =
                new SensorEventListener() {
                  @Override
                  public void onSensorChanged(SensorEvent event) {
                    subscriber.onNext(new Event(event));
                  }

                  @Override
                  public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    subscriber.onNext(new Event(sensor, accuracy));
                  }
                };

            Sensor sensor = manager.getDefaultSensor(type);
            manager.registerListener(listener, sensor, period);

            subscriber.add(
                RxSupport.scheduleUnsubscription(
                    new Action0() {
                      @Override
                      public void call() {
                        manager.unregisterListener(listener);
                      }
                    }));
          }
        });
  }
}

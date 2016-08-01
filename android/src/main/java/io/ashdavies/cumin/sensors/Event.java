package io.ashdavies.cumin.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import rx.functions.Func1;

public class Event {
  private SensorEvent event;
  private Sensor sensor;
  private int accuracy = -1;

  public Event(SensorEvent event) {
    this.event = event;
  }

  public Event(Sensor sensor, int accuracy) {
    this.sensor = sensor;
    this.accuracy = accuracy;
  }

  public static Func1<Event, Boolean> hasFilterSensorChanged() {
    return new Func1<Event, Boolean>() {
      @Override
      public Boolean call(Event event) {
        return event.hasSensorChanged();
      }
    };
  }

  public static Func1<Event, Boolean> hasFilterAccuracyChanged() {
    return new Func1<Event, Boolean>() {
      @Override
      public Boolean call(Event event) {
        return event.hasAccuracyChanged();
      }
    };
  }

  public SensorEvent getEvent() {
    return event;
  }

  public Sensor getSensor() {
    return sensor;
  }

  public int getAccuracy() {
    return accuracy;
  }

  private boolean hasSensorChanged() {
    return event != null;
  }

  private boolean hasAccuracyChanged() {
    return sensor != null && accuracy != -1;
  }
}

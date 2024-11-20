package meteorology.report;

import meteorology.model.Weather;

public abstract class MeteorologyReport {
  protected final static Class<?> weatherClass = Weather.class;

  public abstract void process(Weather weather) throws NoSuchFieldException, IllegalAccessException;
  public abstract void show();
}

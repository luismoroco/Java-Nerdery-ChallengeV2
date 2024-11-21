package meteorology.report.policy;

import meteorology.model.Report;
import meteorology.model.Weather;

public class AggregatedMeteorologyReport {
  protected Report report = new Report();
  protected final static Class<?> weatherClass = Weather.class;

  public void process(Weather weather) throws NoSuchFieldException, IllegalAccessException {
    for (var key : this.report.map.keySet()) {
      var field = weatherClass.getDeclaredField(key);
      field.setAccessible(true);

      var information = this.report.map.get(key);
      information.update((Double) field.get(weather));
    }
  }

  public void show() {
    this.report.show();
  }
}

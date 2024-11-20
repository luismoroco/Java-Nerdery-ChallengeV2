package meteorology.report.policy;

import meteorology.model.Report;
import meteorology.model.Weather;
import meteorology.report.MeteorologyReport;

public class AggregatedMeteorologyReport extends MeteorologyReport {
  protected Report report = new Report();

  @Override
  public void process(Weather weather) throws NoSuchFieldException, IllegalAccessException {
    for (var key : this.report.map.keySet()) {
      var field = weatherClass.getDeclaredField(key);
      field.setAccessible(true);

      var information = this.report.map.get(key);
      information.update((Double) field.get(weather));
    }
  }

  @Override
  public void show() {
    this.report.show();
  }
}

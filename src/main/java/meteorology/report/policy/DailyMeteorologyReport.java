package meteorology.report.policy;

import meteorology.model.Weather;

import java.util.HashMap;
import java.util.Map;

public class DailyMeteorologyReport {
  protected Map<String, AggregatedMeteorologyReport> report = new HashMap<>();

  public void process(Weather weather) throws NoSuchFieldException, IllegalAccessException {
    var meteorologyReport = this.report.get(weather.getDate());
    if (meteorologyReport == null) {
      meteorologyReport = new AggregatedMeteorologyReport();
      meteorologyReport.process(weather);

      this.report.put(weather.getDate(), meteorologyReport);
      return;
    }

    meteorologyReport.process(weather);
  }

  public void show() {
    for (var dayReport : this.report.entrySet()) {
      System.out.println(dayReport.getKey());
      dayReport.getValue().show();
    }
  }
}

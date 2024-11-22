package meteorology;

import meteorology.model.Report;
import meteorology.model.Weather;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MeteorologyReport {
  protected Map<String, Report> dailyReports = new HashMap<>();
  protected Report overview = new Report();

  public MeteorologyReport() {}

  public void process(Weather weather) throws NoSuchFieldException, IllegalAccessException {
    this.overview.process(weather);

    var report = this.dailyReports.get(weather.getDate());
    if (Objects.isNull(report)) {
      report = new Report();
      report.process(weather);

      this.dailyReports.put(weather.getDate(), report);
      return;
    }

    report.process(weather);
  }

  public void show() {
    for (var report : this.dailyReports.entrySet()) {
      System.out.println(report.getKey());
      report.getValue().show();
    }

    System.out.println("OVERVIEW");
    this.overview.show();
  }
}

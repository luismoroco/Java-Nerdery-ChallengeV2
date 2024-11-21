package meteorology.model;

import java.util.Map;

public class Report {
  public Map<String, Variable> map = Map.of(
    "gustspeed", new Variable("Gust speed"),
    "precipitation", new Variable("Precipitation"),
    "relativehumidity", new Variable("Relative humidity"),
    "solar", new Variable("Solar"),
    "strikedistance", new Variable("Strike distance"),
    "strikes", new Variable("Strikes"),
    "vapourpressure", new Variable("Vapour pressure"),
    "winddirection", new Variable("Wind direction"),
    "windspeed", new Variable("Wind speed")
  );

  public void show() {
    for (var report : this.map.entrySet()) {
      report.getValue().show();
    }
  }
}

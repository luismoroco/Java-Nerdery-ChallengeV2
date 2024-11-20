package meteorology.model;

import java.util.Map;

public class Report {
  public Map<String, Variable> map = Map.of(
    "gustspeed", new Variable("gustspeed"),
    "precipitation", new Variable("precipitation"),
    "relativehumidity", new Variable("relativehumidity"),
    "solar", new Variable("solar"),
    "strikedistance", new Variable("strikedistance"),
    "strikes", new Variable("strikes"),
    "vapourpressure", new Variable("vapourpressure"),
    "winddirection", new Variable("winddirection"),
    "windspeed", new Variable("windspeed")
  );

  public void show() {
    for (var report : this.map.entrySet()) {
      report.getValue().show();
    }
  }
}

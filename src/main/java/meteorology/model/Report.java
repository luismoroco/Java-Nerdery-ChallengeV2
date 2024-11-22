package meteorology.model;

import java.util.Map;

public class Report {
  protected final static Class<?> weatherClass = Weather.class;

  public Map<String, Variable> vars = Map.of(
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

  public Report() {}

  public void process(Weather weather) throws NoSuchFieldException, IllegalAccessException {
    for (var key : this.vars.keySet()) {
      var field = weatherClass.getDeclaredField(key);
      field.setAccessible(true);

      var information = this.vars.get(key);
      information.update((Double) field.get(weather));
    }
  }

  public void show() {
    for (var report : this.vars.entrySet()) {
      report.getValue().show();
    }
  }
}

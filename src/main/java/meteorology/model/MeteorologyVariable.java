package meteorology.model;

public enum MeteorologyVariable {

  GUST_SPEED("gustspeed"),
  PRECIPITATION("precipitation"),
  RELATIVE_HUMIDITY("relativehumidity"),
  SOLAR("solar"),
  STRIKE_DISTANCE("strikedistance"),
  STRIKES("strikes"),
  VAPOUR_PRESSURE("vapourpressure"),
  WIND_DIRECTION("winddirection"),
  WIND_SPEED("windspeed");

  public final String name;

  MeteorologyVariable(String name) {
    this.name = name;
  }
}

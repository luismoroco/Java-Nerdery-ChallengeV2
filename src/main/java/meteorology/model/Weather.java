package meteorology.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
  public String dev_id;
  public String name;
  public Location location;
  public String keep_record;
  public String time;
  public String year;
  public String month;
  public String dayofweek;
  public Double airtemp; // I
  public Double atmosphericpressure;
  public Double gustspeed;
  public Double precipitation; // I
  public Double relativehumidity; // I
  public Double solar; // I
  public Double strikedistance; // I
  public Double strikes; // I
  public Double vapourpressure;
  public Double winddirection;
  public Double windspeed;

  public String getDate() {
    return this.time.split("T")[0];
  }

  public boolean verifyFields() throws IllegalAccessException {
    var weather = Weather.class;
    var fields = weather.getDeclaredFields();

    for (var field : fields) {
      field.setAccessible(true);
      if (field.get(this) == null) {
        return false;
      }
    }

    return true;
  }
}
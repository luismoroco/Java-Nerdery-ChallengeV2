package meteorology.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
  public Double airtemp;
  public Double atmosphericpressure;
  public Double gustspeed;
  public Double precipitation;
  public Double relativehumidity;
  public Double solar;
  public Double strikedistance;
  public Double strikes;
  public Double vapourpressure;
  public Double winddirection;
  public Double windspeed;

  public String getDate() {
    return this.getTimeAsZonedDateTime().getDayOfWeek() + " " + this.time.split("T")[0];
  }

  public ZonedDateTime getTimeAsZonedDateTime() {
    return ZonedDateTime.parse(this.time, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
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
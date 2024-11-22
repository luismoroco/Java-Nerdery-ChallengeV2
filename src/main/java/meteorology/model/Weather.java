package meteorology.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
  private final static Class<?> weatherClass = Weather.class;
  private final static Field[] weatherFields = weatherClass.getDeclaredFields();

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

  public Weather() {}

  public String getDate() {
    return this.getTimeAsZonedDateTime().getDayOfWeek() + " " + this.time.split("T")[0];
  }

  public ZonedDateTime getTimeAsZonedDateTime() {
    return ZonedDateTime.parse(this.time, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }

  public boolean verifyFields() throws IllegalAccessException {
    for (var field : weatherFields) {
      field.setAccessible(true);
      if (Objects.isNull(field.get(this))) {
        return false;
      }
    }

    return true;
  }
}
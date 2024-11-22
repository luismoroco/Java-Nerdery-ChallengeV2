package meteorology.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
  public Double lon;
  public Double lat;

  public Location() {}
}

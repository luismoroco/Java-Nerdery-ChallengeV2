import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import meteorology.model.Weather;
import meteorology.report.policy.AggregatedMeteorologyReport;

import java.io.File;
import java.io.IOException;

public class ExtraChallenges {
  public static void main(String[] args) {
    var FILE = new File("weather.json");
    var factory = new JsonFactory();
    var mapper = new ObjectMapper(factory);

    var meteorologyReport = new AggregatedMeteorologyReport();

    try {
      var parser = factory.createParser(FILE);
      if (parser.nextToken() == JsonToken.START_ARRAY) {
        while (parser.nextToken() != JsonToken.END_ARRAY) {
          var weather = mapper.readValue(parser, Weather.class);
          if (!weather.verifyFields()) {
            continue;
          }

          meteorologyReport.process(weather);
        }
      }

      meteorologyReport.show();
    } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
  }
}

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import meteorology.model.Weather;
import meteorology.MeteorologyReport;

import java.io.File;
import java.io.IOException;

public class ExtraChallenges {
  final static String FILE_PATH = "weather.json";

  public static void main(String[] args) throws IOException, IllegalAccessException, NoSuchFieldException {
    var FILE = new File(FILE_PATH);
    var factory = new JsonFactory();
    var mapper = new ObjectMapper(factory);

    var meteorologyReport = new MeteorologyReport();

    var parser = factory.createParser(FILE);
    if (parser.nextToken() == JsonToken.START_ARRAY) {
      return;
    }

    while (parser.nextToken() != JsonToken.END_ARRAY) {
      var weather = mapper.readValue(parser, Weather.class);
      if (!weather.verifyFields()) {
        continue;
      }

      meteorologyReport.process(weather);
    }
  }
}

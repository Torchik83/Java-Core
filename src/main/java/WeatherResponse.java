
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.ZonedDateTime;

public class WeatherResponse {
    public String jsonString;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherResponse(String sourceString)
    {
        jsonString= sourceString;
    }

    public String getFormattedResponseForPeriod() throws JsonProcessingException {

        JsonNode jsonNode = objectMapper.readTree(jsonString).at("/DailyForecasts");
        StringBuilder response = new StringBuilder(" Погода на " + jsonNode.size() + " дней\n");

        for (int i=0; i<jsonNode.size();i++)
        {

            String globalDate =jsonNode.get(i).at("/Date").asText();

            ZonedDateTime dateTime = ZonedDateTime.parse(globalDate);
            String simpleData = dateTime.toLocalDate().toString();

            String minTemperature = jsonNode.get(i).at("/Temperature/Minimum/Value").asText();
            String maxTemperature = jsonNode.get(i).at("/Temperature/Maximum/Value").asText();

            String weatherText = jsonNode.get(i).at("/Day/IconPhrase").asText();

            response.append("На ").append(simpleData).append(" Температура от ").append(minTemperature).append(" до ").append(maxTemperature).append(" градусов, днем ").append(weatherText).append("\n");
        }

        return response.toString();
    }
}
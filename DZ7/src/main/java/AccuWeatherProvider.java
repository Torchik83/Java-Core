
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import enums.Periods;

import java.io.IOException;
import java.util.Objects;

public class AccuWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String MULTIPLE_CONDITIONS_ENDPOINT = "daily";
    private static final String DAY_CONDITION = "5day";
    private static final String API_VERSION = "v1";
    private static final String API_LANGUAGE = "ru-ru";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void getWeather(Periods periods) throws IOException {
        String cityKey = detectCityKey();
        {
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("http")
                    .host(BASE_HOST)
                    .addPathSegment(FORECAST_ENDPOINT)
                    .addPathSegment(API_VERSION)
                    .addPathSegment(MULTIPLE_CONDITIONS_ENDPOINT)
                    .addPathSegment(DAY_CONDITION)
                    .addPathSegment(cityKey)
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("language", API_LANGUAGE)
                    .addQueryParameter("metric", "true")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("accept", "application/json")
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            String responseString= Objects.requireNonNull(response.body()).string();
            WeatherResponse weatherResponse = new WeatherResponse(responseString);
            System.out.println(weatherResponse.getFormattedResponseForPeriod());
        }
    }

    public String detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();

        Response response = client.newCall(request).execute();

        response.isSuccessful();
        String jsonResponse = Objects.requireNonNull(response.body()).string();

        if (objectMapper.readTree(jsonResponse).size() <= 0) {
            throw new IOException("Server returns 0 cities");
        }

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }
}
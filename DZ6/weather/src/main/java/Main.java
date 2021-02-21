import okhttp3.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String LOCATIONS = "locations";
    private static final String API_VERSION = "v1";
    private static final String FORECAST_TYPE = "daily";
    private static final String FORECAST_PERIOD = "5day";
    private static final String API_KEY = "Kb3lwnyygtyKdBJeU9z8dLknpSzGWm4C";
    private static final String CITY = "Москва";
    private static final String FILE = "forecast.json";

    public static void main(String[] args) throws IOException {

        System.out.print("Погоду в Москве на 5 дней, вы можите посмотреть в новом json файле \n");

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();

         HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("search")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", CITY)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response responseGetLocationKey = client.newCall(request).execute();
        String responseBody = Objects.requireNonNull(responseGetLocationKey.body()).string();
        String locationKey = responseBody.split(":")[2];
        locationKey = locationKey.split(",")[0];
        locationKey = locationKey.replaceAll("[^\\p{L}\\p{Nd}]+", "");

        url = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(FORECASTS)
                .addPathSegment(API_VERSION)
                .addPathSegment(FORECAST_TYPE)
                .addPathSegment(FORECAST_PERIOD)
                .addPathSegment(locationKey)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("metric", "true")
                .build();

        request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response responseGetForecast = client.newCall(request).execute();
        responseBody = Objects.requireNonNull(responseGetForecast.body()).string();

        try (PrintWriter out = new PrintWriter(FILE)) {
            out.println(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
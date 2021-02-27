
import enums.Periods;
import enums.Functionality;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    WeatherProvider weatherProvider = new AccuWeatherProvider();
    Map<Integer, Functionality> variantResult = new HashMap();

    public Controller() {
        variantResult.put(1, Functionality.GET_WEATHER_IN_NEXT_5_DAYS);
    }

    public void onUserInput(String input) throws IOException {
        int command = Integer.parseInt(input);
        if (!variantResult.containsKey(command)) {
            throw new IOException("There is no command for command-key " + command);
        }

        if (variantResult.get(command) == Functionality.GET_WEATHER_IN_NEXT_5_DAYS) {
            getWeatherIn5Days();
        }
    }

    public void getWeatherIn5Days() throws IOException {
        WeatherProvider.getWeather(Periods.FIVE_DAYS);
    }
}

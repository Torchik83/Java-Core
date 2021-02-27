
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import enums.Periods;

public class WeatherDB {


    public static void main(String[] args) throws IOException, SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            DBActions.connection = DriverManager.getConnection("jdbc:sqlite:weather.db");
            DBActions.statement = DBActions.connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        DBActions.performCreateDB();
        for (int i = 0; i < 3; i++) {
            DBActions.preparedStatement(Objects.requireNonNull(AccuWeatherProvider.getWeather(Periods.FIVE_DAYS)),
                    ApplicationGlobalState.getInstance().getSelectedCity());
        }
        ValidateInput.checkInput();

        System.out.println("************");
        DBActions.connection.close();
    }


}
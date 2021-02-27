
import java.sql.*;

public class DBActions {
    public static Connection connection;
    public static Statement statement;
    public static PreparedStatement preparedStatement;


    public static void performCreateDB() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS forecasts (" +
                "  City STRING NOT NULL," +
                "  LocalDate STRING PRIMARY KEY NOT NULL," +
                "  WeatherText STRING NOT NULL," +
                "  Temperature DOUBLE NOT NULL" +
                "  );");
    }

    public static void preparedStatement(ForecastsResponse forecastsResponse, String cityName) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO forecasts (City,LocalDate,WeatherText,Temperature)" +
                "VALUES (?,?,?,?);");
        for (DailyForecast i : forecastsResponse.dailyForecasts) {
            preparedStatement.setString(1, cityName);
            preparedStatement.setString(2, i.date);
            preparedStatement.setString(3, DailyForecast.day.iconPhrase);
            preparedStatement.setDouble(4, i.temperature2.maximum);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        System.out.println("Данные внесены.");
    }

    public static void readFromDB() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM forecasts");
        while (resultSet.next()) {
            System.out.println(
                    resultSet.getString(1) + " | " +
                            resultSet.getString(2) + " | " +
                            resultSet.getString(3) + " | " +
                            resultSet.getDouble(4) + " | "
            );
        }
    }
}
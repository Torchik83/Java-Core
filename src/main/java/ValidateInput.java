
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ValidateInput {

    public static void checkInput() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Выполнить чтение из базы данных? " +
                "1 - прочитать.    exit  - выйти.");
        String result = scan.nextLine();

        IsExit(result);
        try {
            notifyController(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void IsExit(String result) {
        if (result.equalsIgnoreCase("выход") || result.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }


    private static void notifyController(String input) throws IOException, SQLException {
        onUserInput(input);
    }

    public static void onUserInput(String input) throws IOException, SQLException {
        int command = Integer.parseInt(input);
        if (!(command == 1)) {
            throw new IOException("Действия для команды " + command + "не предусмотрены!");
        }
        DBActions.readFromDB();
    }
}

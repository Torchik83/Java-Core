
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

    private final Controller controller = new Controller();

    public void runApplication() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите название города на английском языке");
            String city = scanner.nextLine();

            setGlobalCity(city);

            System.out.println("1 - Получить погоду на следующие 5 дней, " + "или напишите выход (exit) для завершения работы");
            String result = scanner.nextLine();

            checkIsExit(result);

            try {
                notifyController(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void checkIsExit(String result) {
        if (result.equalsIgnoreCase("выход") || result.equalsIgnoreCase("exit")) {
            System.out.println("Завершаю работу");
            System.exit(0);
        }
    }

    private void setGlobalCity(String city) {
        ApplicationGlobalState.getInstance().setSelectedCity(city);
    }

    private void notifyController(String input) throws IOException {
        controller.onUserInput(input);
    }
}

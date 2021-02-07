package DZ_2;

public class MyArrayDataException extends NumberFormatException {
    public MyArrayDataException(int i, int j) {
        super("Не удалось преобразовать элемент массива (" + i + "," + j + ")");
    }
}
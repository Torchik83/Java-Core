package DZ_2;

public class MyArraySizeException extends IndexOutOfBoundsException {
    public MyArraySizeException() {
        super("Этот массив не 4х4");
    }
}

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Integer[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        swap(arr1,1,4);

        Box<Orange> orange = new Box<>();
        Box<Orange> orange1 = new Box<>();
        Box<Apple> apple = new Box<>();
        Box<Apple> apple1 = new Box<>();
        System.out.println("Задание 2");
        System.out.println("Добавление фруктов: ");
        orange.addFruit(new Orange(),8);
        orange1.addFruit(new Orange(),10);
        apple.addFruit(new Apple(),6);
        apple1.addFruit(new Apple(),4);
        System.out.println("Коробка 1: "+orange.getWeight());
        System.out.println("Коробка 2: "+orange1.getWeight());
        System.out.println("Коробка 3: "+apple.getWeight());
        System.out.println("Коробка 4: "+apple1.getWeight());
        System.out.println("Сравниваем коробки: ");
        System.out.println("Коробка 1 равна Коробке 3: "+orange.compare(apple));
        System.out.println("Коробка 2 равна Коробке 4: "+orange1.compare(apple1));
        orange.pourTo(orange1);
        apple.pourTo(apple1);
        System.out.println("Вес коробок: ");
        System.out.println("Box 1: "+orange.getWeight());
        System.out.println("Box 2: "+orange1.getWeight());
        System.out.println("Box 3: "+apple.getWeight());
        System.out.println("Box 4: "+apple1.getWeight());

    }
    public static void swap(Object[] arr, int n1, int n2){
        System.out.println("Задание 1: "+Arrays.toString(arr));
        arr[n1]=arr[n2];
        System.out.println("Перемещаем символы: "+Arrays.toString(arr)+"\n**********");
    }
}

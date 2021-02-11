import java.util.ArrayList;

public class Box<T extends Fruit> {
    private final ArrayList<T> box = new ArrayList<>();

      public Box(){
    }
        public float getWeight(){
        float weight = 0.0f;

        for(T o : box){
            weight += o.getWeight();
        }return weight;
    }
    public boolean compare(Box anotherBox) {
        return getWeight() == anotherBox.getWeight();
    }
    public void pourTo(Box <T>anotherBox){
        anotherBox.box.addAll(box);
        box.clear();
    }
    public void addFruit(T fruit, int amount){
        for(int i=0;i<amount;i++){
            box.add(fruit);
        }
    }
}
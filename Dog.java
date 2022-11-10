package convert;

public class Dog extends Animal {
    public String name = "🐕";
    @Override
    public void run() {
        System.out.println("🐕跑的快");
    }
    public  void LookDog(){
        System.out.println("看门");
    }
}

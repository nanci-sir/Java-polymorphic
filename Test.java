package test;

public class Test {
    public static void main(String[] args) {
        Computer c = new Computer();
        USB U = new Mouse("蜘蛛蟹");
        c.installUSB( U );
        USB K = new KeyBoard( "大闸蟹" );
        c.installUSB( K );
    }
}

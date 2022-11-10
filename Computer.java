package test;

public class Computer {
    public  void installUSB(USB u){
        u.connect();
        if(u instanceof  Mouse){
            Mouse m =(Mouse) u;
            m.click();

        }else if (u instanceof  KeyBoard){
            KeyBoard k = (KeyBoard) u;
            k.keyDown();
        }
        u.unconnect();

    }

}

package os1.Registers;

/**
 *
 * @author Arturas
 */
public class TimerRegister {

    private int value;
    
    public TimerRegister(){
        this.value = 0;
    }

    public TimerRegister(int value) {
        this.value = value;
    }

    public int getTimer() {
        return this.value;
    }

    public void setTimer(int value) {
        this.value = value;
    }

    public void decTimer() { // kreiptis, kai reikia sumažinti timerio registrą
        this.value--;
    }
}

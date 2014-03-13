package os1.Registers;

/**
 *
 * @author Arturas
 */
public class TimerRegister {

    private int value;

    public TimerRegister(int value) {
        this.value = value;
    }

    public int getTimer() {
        return this.value;
    }

    public void setTimer(int value) {
        this.value = value;
    }

    public void decTimer() {
        this.value--;
    }
}

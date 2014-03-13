package os1.Registers;

/**
 *
 * @author Arturas
 */
public class TimerRegister {

    int value;

    public TimerRegister(int value) {
        this.value = value;
    }

    public void decTimer() {
        this.value--;
    }
}

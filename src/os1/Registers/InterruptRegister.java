package os1.Registers;

/**
 *
 * @author Arturas
 */
public class InterruptRegister {

    private byte value;

    public InterruptRegister() {
        this.value = 0;
    }

    public InterruptRegister(byte value) {
        this.value = value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return this.value;
    }

    public void timerElapsed(TimerRegister timReg) {
        if (timReg.value == 0) {
            this.value = 1;
        } else {
            this.value = 0;
        }
    }
}

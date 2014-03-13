package os1.Registers;

/**
 *
 * @author Arturas
 */
public class InterruptRegister extends Register1B {

    public InterruptRegister() {
        this.value = 0;
    }

    public InterruptRegister(byte value) {
        this.value = value;
    }

}

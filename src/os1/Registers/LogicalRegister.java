package os1.Registers;

/**
 *
 * @author Arturas
 */
public class LogicalRegister {

    private boolean value;

    public LogicalRegister() {
        this.value = false;
    }

    public LogicalRegister(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

}

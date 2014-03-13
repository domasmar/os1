package os1.Registers;

/**
 *
 * @author Arturas
 */
public class LogicalRegister {
    
    private byte value;

    public LogicalRegister() {
        this.value = 0;
    }

    public LogicalRegister(byte value) {
        this.value = value;
    }

    public boolean getValue() {
        boolean logicalValue = false;

        if (this.value > 0) {
            logicalValue = true;
        } else {
            logicalValue = false;
        }

        return logicalValue;
    }

    public void setValue(byte value) {
        this.value = value;
    }

}

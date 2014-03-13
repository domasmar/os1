package os1.Registers;

/**
 *
 * @author Arturas
 */
public class Register2B {

    private short value;
    
    public Register2B(){
        this.value = 0;
    }

    public Register2B(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

}

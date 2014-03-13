package os1.Registers;

/**
 *
 * @author Arturas
 */
public class Register4B {

    private int value;
    
    public Register4B(){
        this.value = 0;
    }

    public Register4B(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}

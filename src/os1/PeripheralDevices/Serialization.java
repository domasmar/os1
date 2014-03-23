package os1.PeripheralDevices;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Arturas
 */
public class Serialization {

    public static void serializeMemory(HDD memory) {
        try {
            FileOutputStream fileOut = new FileOutputStream("HDD");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(memory);
            out.close();
            fileOut.close();
        } catch (IOException i) {
        }
    }

    public static HDD deserializeMemory() {
        HDD memory = null;
        try {
            FileInputStream fileIn = new FileInputStream("HDD");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            memory = (HDD) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            return null;
        } catch (ClassNotFoundException c) {
            return null;
        }
        return memory;
    }

}

package os1;

import os1.Registers.*;

public class MainArt {

    public static void main(String[] args) {
        String bits = "0100100101001000"; 
        String cmdBits = bits.substring(0, 8);
        String valueBits = bits.substring(8, 16);
        
        System.out.println(cmdBits);
        System.out.println(valueBits);
    }
    
}

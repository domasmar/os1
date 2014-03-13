package os1.Interpreter;

public class Interpreter {

    private static final byte MOV_AX = (byte) 0b1101_0000;
    private static final byte MOV_BX = (byte) 0b1100_0000;
    private static final byte LOA_AX = (byte) 0b1000_0000;
    private static final byte LOA_BX = (byte) 0b1001_0000;
    private static final byte STO_AX = (byte) 0b1000_0001;
    private static final byte STO_BX = (byte) 0b1001_0001;
    private static final byte PUSH = (byte) 0b1000_0010;
    private static final byte POP = (byte) 0b1000_0011;
    private static final byte ADD = (byte) 0b1000_0100;
    private static final byte SUB = (byte) 0b1000_0101;
    private static final byte CMP = (byte) 0b1000_0111;
    private static final byte JA = (byte) 0b0100_0000;
    private static final byte JB = (byte) 0b0100_0001;
    private static final byte JE = (byte) 0b0100_0010;
    private static final byte JNE = (byte) 0b0100_0011;
    private static final byte OUTR_AX = (byte) 0b1110_0000;
    private static final byte OUTR_BX = (byte) 0b1111_0000;
    private static final byte OUTM = (byte) 0b1000_0111;
    // FORK. Not implemented
    private static final byte STOP = (byte) 0b0000_0000;

    private String commandStringInput;
    private Command command;
    private CmdWithVar[] cmdWithVar = new CmdWithVar[256];
    private byte opc;
    private byte adrJ;
    private byte adrO;
    private byte register; // 0 - No register, 1 = AX, 2 = BX

    public CmdWithVar Interpreter(String[] commandsArray) {
        //paduodamas programos source kodas kaip string masyvas. Nuskaito, atpažįsta ir grąžina arba null(jei klaidos) arba 
        // CmdWithVar tipo: komanda ir kintamasis šalia(jei toks yra)
        return null;
    }

    public CmdWithVar Interpreter(int[] commandsArray) {
        for (int i = 0; i <= commandsArray.length; i++) {
            recognizeIntCommand(commandsArray, cmdWithVar[i], i);
        
            
        }

        return null;
    }

    public void interpret(String command) throws Exception {

        this.commandStringInput = command.trim();
        recognizeStringCommand();
        if (this.command == null) {
            throw new Exception("Komanda neatpažinta. Duota komanda: "
                    + this.commandStringInput);

        }
    }

    private class ValidResults {

        byte adrJ;
        byte adrO;
        boolean valid;

        public ValidResults() {
            this.valid = false;
        }

        public ValidResults(byte adrJ, byte adrO) {
            this.adrJ = adrJ;
            this.adrO = adrO;
            this.valid = true;
        }
    }

    private CmdWithVar recognizeIntCommand(int[] commandsArray, CmdWithVar cmdWithVar, int i) {
        
        String bits = intToBits(commandsArray[i]);
        String cmdBits = bits.substring(0, 8);
        int cmdInt = Integer.parseInt(cmdBits, 2);
        
        if (cmdInt == ADD) {
            cmdWithVar.command = Command.ADD;   
        }
        
        if (cmdInt == SUB) {
            cmdWithVar.command = Command.SUB;
        }
        
        if (cmdInt == CMP) {
            cmdWithVar.command = Command.CMP;
        }
        
        if (cmdInt == STOP){
            cmdWithVar.command = Command.STOP;
        }
// vieno baito komandos baigėsi.
        if (cmdInt == MOV_AX) {
            cmdWithVar.command = Command.MOV_AX;
        }
        
        if (cmdInt == MOV_BX) {
            cmdWithVar.command = Command.MOV_BX;
        }
        
        if (cmdInt == LOA_AX) {
            cmdWithVar.command = Command.LOA_AX;
        }
        
        if (cmdInt == LOA_BX) {
            cmdWithVar.command = Command.LOA_BX;
        }
        
        if (cmdInt == STO_AX) {
            cmdWithVar.command = Command.STO_AX;
        }
        
        if (cmdInt == STO_BX) {
            cmdWithVar.command = Command.STO_BX;
        }

        if (cmdInt == PUSH) {
            cmdWithVar.command = Command.PUSH;
        }
        
        if (cmdInt == POP) {
            cmdWithVar.command = Command.POP;
        }
        
        
        if (cmdInt == JA) {
            cmdWithVar.command = Command.JA;
        }
        
        if (cmdInt == JB) {
            cmdWithVar.command = Command.JB;
        }
        
        if (cmdInt == JE) {
            cmdWithVar.command = Command.JE;
        }
        
        if (cmdInt == JNE) {
            cmdWithVar.command = Command.JNE;
        }
        
        if (cmdInt == OUTR_AX){
            cmdWithVar.command = Command.OUTR_AX;
        }
        
        if (cmdInt == OUTR_BX){
            cmdWithVar.command = Command.OUTR_BX;
        }
        
        if (cmdInt == OUTM){
            cmdWithVar.command = Command.OUTM;
        }
        
        return null;
    }

    private void recognizeStringCommand() {

        // Vieno baito komandos
        if (commandStringInput.equalsIgnoreCase("add")) {
            this.setOpc(Interpreter.ADD);
            this.command = Command.ADD;
        }

        if (commandStringInput.equalsIgnoreCase("sub")) {
            this.setOpc(Interpreter.SUB);
            this.command = Command.SUB;
        }

        if (commandStringInput.equalsIgnoreCase("cmp")) {
            this.setOpc(Interpreter.CMP);
            this.command = Command.CMP;
        }

        if (commandStringInput.equalsIgnoreCase("stop")) {
            this.setOpc(Interpreter.STOP);
            this.command = Command.STOP;
        }

        String opc = commandStringInput;
        String remainder = "";

        for (int i = 0; i < commandStringInput.length(); i++) {
            if (commandStringInput.charAt(i) == ' ') {
                opc = commandStringInput.substring(0, i).trim();
                if (opc.length() != commandStringInput.length()) {
                    remainder = commandStringInput
                            .substring(i, commandStringInput.length()).trim();
                }
                break;
            }
        }

        // Visos be parametru komandos patikrintos
        // jeigu nera parametru,
        // reiskias kitos komandos netiks
        if (remainder.equals("")) {
            return;
        }

        if (remainder.contains(",")) {
            String first = "";
            String second = "";
            try {
                first = remainder.split(",")[0].trim();
                second = remainder.split(",")[1].trim();
            } catch (Exception e) {
                return;
            }
            if (opc.equalsIgnoreCase("loa")) {
                if (second.equalsIgnoreCase("ax")) {
                    if (loadRemainderIfValid(first)) {
                        this.setOpc(Interpreter.LOA_AX);
                        this.command = Command.LOA_AX;
                        this.register = 1;
                    }
                }

                if (second.equalsIgnoreCase("bx")) {
                    if (loadRemainderIfValid(first)) {
                        this.setOpc(Interpreter.LOA_BX);
                        this.command = Command.LOA_BX;
                        this.register = 2;
                    }
                }
            }

            if (opc.equalsIgnoreCase("mov")) {
                if (first.equalsIgnoreCase("ax")) {
                    if (loadRemainderIfValid(second)) {
                        System.out.println("opa");
                        this.setOpc(Interpreter.MOV_AX);
                        this.command = Command.MOV_AX;
                        this.register = 1;
                    }
                }

                if (first.equalsIgnoreCase("bx")) {
                    if (loadRemainderIfValid(second)) {
                        this.setOpc(Interpreter.MOV_BX);
                        this.command = Command.MOV_BX;
                        this.register = 2;
                    }
                }
            }

            if (opc.equalsIgnoreCase("sto")) {
                if (first.equalsIgnoreCase("ax")) {
                    if (loadRemainderIfValid(second)) {
                        this.setOpc(Interpreter.STO_AX);
                        this.command = Command.STO_AX;
                        this.register = 1;
                    }
                }

                if (first.equalsIgnoreCase("bx")) {
                    if (loadRemainderIfValid(second)) {
                        this.setOpc(Interpreter.STO_BX);
                        this.command = Command.STO_BX;
                        this.register = 2;
                    }
                }
            }

        } else {
            // 1 parametro komandos
            if (opc.equalsIgnoreCase("push")) {
                if (loadRemainderIfValid(remainder)) {
                    this.setOpc(Interpreter.PUSH);
                    this.command = Command.PUSH;
                }
            }

            if (opc.equalsIgnoreCase("pop")) {
                if (loadRemainderIfValid(remainder)) {
                    this.setOpc(Interpreter.POP);
                    this.command = Command.POP;
                }
            }

            if (opc.equalsIgnoreCase("ja")) {
                if (loadRemainderIfValid(remainder)) {
                    this.setOpc(Interpreter.JA);
                    this.command = Command.JA;
                }
            }

            if (opc.equalsIgnoreCase("jb")) {
                if (loadRemainderIfValid(remainder)) {
                    this.setOpc(Interpreter.JB);
                    this.command = Command.JB;
                }
            }

            if (opc.equalsIgnoreCase("je")) {
                if (loadRemainderIfValid(remainder)) {
                    this.setOpc(Interpreter.JB);
                    this.command = Command.JB;
                }
            }

            if (opc.equalsIgnoreCase("je")) {
                if (loadRemainderIfValid(remainder)) {
                    this.setOpc(Interpreter.JE);
                    this.command = Command.JE;
                }
            }

            if (opc.equalsIgnoreCase("jne")) {
                if (loadRemainderIfValid(remainder)) {
                    this.setOpc(Interpreter.JNE);
                    this.command = Command.JNE;
                }
            }

            if (opc.equalsIgnoreCase("outm")) {
                if (loadRemainderIfValid(remainder)) {
                    this.setOpc(Interpreter.OUTM);
                    this.command = Command.OUTM;
                }
            }

            if (opc.equalsIgnoreCase("outr")) {
                if (remainder.equalsIgnoreCase("ax")) {
                    this.setOpc(Interpreter.OUTR_AX);
                    this.command = Command.OUTR_AX;
                    this.register = 1;
                }
                if (remainder.equalsIgnoreCase("bx")) {
                    this.setOpc(Interpreter.OUTR_BX);
                    this.command = Command.OUTR_BX;
                    this.register = 2;
                }
            }
        }
    }

    private boolean loadRemainderIfValid(String remainder) {
        ValidResults vr = this.isValidHex(remainder);
        if (vr.valid) {
            this.adrJ = vr.adrJ;
            this.adrO = vr.adrO;
            return true;
        } else {
            return false;
        }
    }

    private byte charToHex(char a) {
        return (byte) Character.digit(a, 16);
    }

    private String intToBits(int a) {
        String bits = Integer.toBinaryString(a);

        int length = bits.length();
        int diffLength = 32 - length;
        for (int i = 0; i < diffLength; i++) {
            bits = "0" + bits;
        }
        return bits;
    }

    private ValidResults isValidHex(String remainder) {
        if (remainder.length() == 2) {
            try {
                Integer.parseInt(remainder, 16);
            } catch (Exception e) {
                return new ValidResults();
            }
            return new ValidResults(charToHex(remainder.charAt(0)),
                    charToHex(remainder.charAt(1)));
        }
        return new ValidResults();
    }

    public String toString() {
        String s = "Įvesta komanda: " + this.commandStringInput + "\n";
        s += "Komanda: " + this.command + "\n";
        String opc = Integer.toBinaryString(this.opc);
        if (opc.length() > 8) {
            s += "OPC: " + opc.substring(opc.length() - 8) + "b\n";
        } else {
            s += "OPC: " + opc + "\n";
        }
        s += "ADRJ: " + Integer.toBinaryString(this.adrJ) + "b\n";
        s += "ADRV: " + Integer.toBinaryString(this.adrO) + "b\n";
        if (this.register == 1) {
            s += "Registras: " + "AX " + "\n";
        } else if (this.register == 2) {
            s += "Registras: " + "BX " + "\n";
        } else if (this.register == 0) {
            s += "Registras: " + this.register + "\n";
        }
        return s;
    }

    public byte getRegister() {
        return register;
    }

    public void setRegister(byte register) {
        this.register = register;
    }

    public byte getOpc() {
        return opc;
    }

    public void setOpc(byte opc) {
        this.opc = opc;
    }

    public byte getAdrJ() {
        return adrJ;
    }

    public void setAdrJ(byte adrJ) {
        this.adrJ = adrJ;
    }

    public byte getAdrO() {
        return adrO;
    }

    public void setAdrO(byte adrO) {
        this.adrO = adrO;
    }

}

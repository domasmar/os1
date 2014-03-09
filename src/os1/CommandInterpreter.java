package os1;

public class CommandInterpreter {
	
	private static final byte LOA_AX 	= (byte) 0b1000_0000;
	private static final byte LOA_BX 	= (byte) 0b1001_0000;
	private static final byte STO_AX 	= (byte) 0b1000_0001;
	private static final byte STO_BX 	= (byte) 0b1001_0001;
	private static final byte PUSH 		= (byte) 0b1000_0010;
	private static final byte POP 		= (byte) 0b1000_0011;
	private static final byte ADD		= (byte) 0b1000_0100;
	private static final byte SUB 		= (byte) 0b1000_0101;
	private static final byte CMP 		= (byte) 0b1000_0111;
	private static final byte JA 		= (byte) 0b0100_0000;
	private static final byte JB 		= (byte) 0b0100_0001;
	private static final byte JE 		= (byte) 0b0100_0010;
	private static final byte JNE 		= (byte) 0b0100_0011;
	private static final byte OUTR_AX	= (byte) 0b1110_0000;
	private static final byte OUTR_BX	= (byte) 0b1111_0000;
	private static final byte OUTM 		= (byte) 0b1000_0111;
	// FORK. Not implemented
	private static final byte STOP		= (byte) 0b0000_0000;
	
	
	private String commandInput;
	private Command command;
	private byte opc;
	private byte adrJ;
	private byte adrO;
	private byte register; // 0 = AX, 1 = BX
	
	public CommandInterpreter(String command) throws Exception {
		this.commandInput = command.trim();
		recognizeCommand();
		if (this.command == null) {
			throw new Exception("Komanda neaptaþinta. Duota komanda: " + this.commandInput);
		}
	}
	
	private void recognizeCommand() {
		
		// Vieno baito komandos
		if (commandInput.equalsIgnoreCase("add")){
			this.setOpc(CommandInterpreter.ADD);
			this.command = Command.ADD;
		}
		
		if (commandInput.equalsIgnoreCase("sub")){
			this.setOpc(CommandInterpreter.SUB);
			this.command = Command.SUB;
		}
		
		if (commandInput.equalsIgnoreCase("cmp")){
			this.setOpc(CommandInterpreter.CMP);
			this.command = Command.CMP;
		}
		
		if (commandInput.equalsIgnoreCase("stop")){
			this.setOpc(CommandInterpreter.STOP);
			this.command = Command.STOP;
		}
		
		String opc = commandInput;
		String remainder = "";
		for (int i = 0; i < commandInput.length(); i++) {
			if (commandInput.charAt(i) == ' ') {
				opc = commandInput.substring(0, i).trim();
				if (opc.length() != commandInput.length()) {
					remainder = commandInput.substring(i).trim();
				}
			}
		}		
		
		if (opc.equalsIgnoreCase("push")) {
			// TODO
			// Pabaigti
		}
		
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

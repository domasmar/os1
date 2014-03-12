package os1;

public class CommandInterpreter {

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
			throw new Exception("Komanda neaptaþinta. Duota komanda: "
					+ this.commandInput);
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

	private void recognizeCommand() {

		// Vieno baito komandos
		if (commandInput.equalsIgnoreCase("add")) {
			this.setOpc(CommandInterpreter.ADD);
			this.command = Command.ADD;
		}

		if (commandInput.equalsIgnoreCase("sub")) {
			this.setOpc(CommandInterpreter.SUB);
			this.command = Command.SUB;
		}

		if (commandInput.equalsIgnoreCase("cmp")) {
			this.setOpc(CommandInterpreter.CMP);
			this.command = Command.CMP;
		}

		if (commandInput.equalsIgnoreCase("stop")) {
			this.setOpc(CommandInterpreter.STOP);
			this.command = Command.STOP;
		}

		String opc = commandInput;
		String remainder = "";

		for (int i = 0; i < commandInput.length(); i++) {
			if (commandInput.charAt(i) == ' ') {
				opc = commandInput.substring(0, i).trim();
				if (opc.length() != commandInput.length()) {
					remainder = commandInput
							.substring(i, commandInput.length()).trim();
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
			if (opc.equalsIgnoreCase("LOA")) {
				if (second.equalsIgnoreCase("ax")) {
					if (loadRemainderIfValid(first)) {
						this.setOpc(CommandInterpreter.LOA_AX);
						this.command = Command.LOA_AX;
						this.register = 1;
					}
				}

				if (second.equalsIgnoreCase("bx")) {
					if (loadRemainderIfValid(first)) {
						this.setOpc(CommandInterpreter.LOA_BX);
						this.command = Command.LOA_BX;
						this.register = 2;
					}
				}
			}

			if (opc.equalsIgnoreCase("STO")) {
				if (first.equalsIgnoreCase("ax")) {
					if (loadRemainderIfValid(second)) {
						this.setOpc(CommandInterpreter.STO_AX);
						this.command = Command.STO_AX;
						this.register = 1;
					}
				}

				if (first.equalsIgnoreCase("bx")) {
					if (loadRemainderIfValid(second)) {
						this.setOpc(CommandInterpreter.STO_BX);
						this.command = Command.STO_BX;
						this.register = 2;
					}
				}
			}

		} else {
			// 1 parametro komandos
			if (opc.equalsIgnoreCase("push")) {
				if (loadRemainderIfValid(remainder)) {
					this.setOpc(CommandInterpreter.PUSH);
					this.command = Command.PUSH;
				}
			}

			if (opc.equalsIgnoreCase("pop")) {
				if (loadRemainderIfValid(remainder)) {
					this.setOpc(CommandInterpreter.POP);
					this.command = Command.POP;
				}
			}

			if (opc.equalsIgnoreCase("ja")) {
				if (loadRemainderIfValid(remainder)) {
					this.setOpc(CommandInterpreter.JA);
					this.command = Command.JA;
				}
			}

			if (opc.equalsIgnoreCase("jb")) {
				if (loadRemainderIfValid(remainder)) {
					this.setOpc(CommandInterpreter.JB);
					this.command = Command.JB;
				}
			}

			if (opc.equalsIgnoreCase("je")) {
				if (loadRemainderIfValid(remainder)) {
					this.setOpc(CommandInterpreter.JB);
					this.command = Command.JB;
				}
			}

			if (opc.equalsIgnoreCase("je")) {
				if (loadRemainderIfValid(remainder)) {
					this.setOpc(CommandInterpreter.JE);
					this.command = Command.JE;
				}
			}

			if (opc.equalsIgnoreCase("jne")) {
				if (loadRemainderIfValid(remainder)) {
					this.setOpc(CommandInterpreter.JNE);
					this.command = Command.JNE;
				}
			}

			if (opc.equalsIgnoreCase("outm")) {
				if (loadRemainderIfValid(remainder)) {
					this.setOpc(CommandInterpreter.OUTM);
					this.command = Command.OUTM;
				}
			}

			if (opc.equalsIgnoreCase("outr")) {
				if (remainder.equalsIgnoreCase("ax")) {
					this.setOpc(CommandInterpreter.OUTR_AX);
					this.command = Command.OUTR_AX;
					this.register = 1;
				}
				if (remainder.equalsIgnoreCase("bx")) {
					this.setOpc(CommandInterpreter.OUTR_BX);
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
		String s = "Ávesta komanda: " + this.commandInput + "\n";
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

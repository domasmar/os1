package os1.Interpreter;

import java.util.ArrayList;

public class Interpreter {

	private static final byte MOV_AX = (byte) 0b1100_0000;
	private static final byte MOV_BX = (byte) 0b1101_0000;
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
	private static final byte STOP = (byte) 0b0000_0001;

	//private String commandStringInput;
	//private Command command;
	private CmdWithVar[] cmdWithVar = new CmdWithVar[256];
	private ArrayList<CmdWithVar> commandList = new ArrayList<CmdWithVar>();

	// private byte opc;
	// private byte adrJ;
	// private byte adrO;
	// private byte register; // 0 - No register, 1 = AX, 2 = BX

	public ArrayList<CmdWithVar> interpret(String[] commandsArray) throws Exception {
		for (int i = 0; i < commandsArray.length; i++) {
			if (commandsArray[i].trim().equals("")) {
				continue;
			} else {
				CmdWithVar cmd = recognizeStringCommand(commandsArray[i], i);
				if (cmd == null) {
					throw new Exception("Komanda neatpažinta. Komanda: "
							+ commandsArray[i] + ". Eilute: " + i);
				} else {
					commandList.add(cmd);
				}
			}
		}
		return commandList;
	}

	public CmdWithVar[] interpret(int[] commandsArray) throws Exception {
		for (int i = 0; i < commandsArray.length; i++) {
			recognizeIntCommand(commandsArray, i);
			if (commandsArray[i] == 0) {
				return cmdWithVar;
			}
			if (cmdWithVar[i].command == null) {
				throw new Exception("Komanda neatpaÅ¾inta. Duota komanda: "
						+ Integer.toBinaryString(commandsArray[i]));
			}
			if ((cmdWithVar[i].command == Command.MOV_AX)
					|| (cmdWithVar[i].command == Command.MOV_BX)) {
				i++;
			}
		}
		return null;
	}

	// public void interpret(String command) throws Exception {
	//
	// this.commandStringInput = command.trim();
	// recognizeStringCommand();
	// if (this.command == null) {
	// throw new Exception("Komanda neatpaÅ¾inta. Duota komanda: "
	// + this.commandStringInput);
	//
	// }
	// }

	private class ValidResults {

		int value;
		boolean valid;

		public ValidResults() {
			this.valid = false;
		}

		public ValidResults(int value) {
			this.value = value;
			this.valid = true;
		}
	}

	private void recognizeIntCommand(int[] commandsArray, int i) {
		String bits = intToBits(commandsArray[i]);
		String cmdBits = bits.substring(0, 8);
		String valueBits = bits.substring(8, 16);
		int cmdInt = Integer.parseInt(cmdBits, 2);
		int valueInt = Integer.parseInt(valueBits, 2);

		if (cmdInt == ADD) {
			cmdWithVar[i].command = Command.ADD;
			return;
		}

		if (cmdInt == SUB) {
			cmdWithVar[i].command = Command.SUB;
			return;
		}

		if (cmdInt == CMP) {
			cmdWithVar[i].command = Command.CMP;
			return;
		}

		if (cmdInt == STOP) {
			cmdWithVar[i].command = Command.STOP;
			return;
		}
		// vieno baito komandos baigÄ—si.
		// Jei MOV Å¾iÅ«rÄ—ti ÄÆ kitÄ… masyvo elementÄ… ir iÅ� ten paimti
		// reikalingÄ… Å¾odÄÆ(4B)
		if (cmdInt == MOV_AX) {
			cmdWithVar[i].command = Command.MOV_AX;
			cmdWithVar[i].variable = commandsArray[i + 1];
			return;
		}

		if (cmdInt == MOV_BX) {
			cmdWithVar[i].command = Command.MOV_BX;
			cmdWithVar[i].variable = commandsArray[i + 1];
			return;
		}
		//
		if (cmdInt == LOA_AX) {
			cmdWithVar[i].command = Command.LOA_AX;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == LOA_BX) {
			cmdWithVar[i].command = Command.LOA_BX;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == STO_AX) {
			cmdWithVar[i].command = Command.STO_AX;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == STO_BX) {
			cmdWithVar[i].command = Command.STO_BX;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == PUSH) {
			cmdWithVar[i].command = Command.PUSH;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == POP) {
			cmdWithVar[i].command = Command.POP;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == JA) {
			cmdWithVar[i].command = Command.JA;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == JB) {
			cmdWithVar[i].command = Command.JB;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == JE) {
			cmdWithVar[i].command = Command.JE;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == JNE) {
			cmdWithVar[i].command = Command.JNE;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == OUTR_AX) {
			cmdWithVar[i].command = Command.OUTR_AX;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == OUTR_BX) {
			cmdWithVar[i].command = Command.OUTR_BX;
			cmdWithVar[i].variable = valueInt;
			return;
		}

		if (cmdInt == OUTM) {
			cmdWithVar[i].command = Command.OUTM;
			cmdWithVar[i].variable = valueInt;
			return;
		}
		cmdWithVar[i].command = null;
	}

	private CmdWithVar recognizeStringCommand(String strCommand, int row) {
		CmdWithVar cmd = new CmdWithVar();
		cmd.row = row;
		
		// Vieno baito komandos
		if (strCommand.equalsIgnoreCase("add")) {
			cmd.commandOpc = Interpreter.ADD;
			cmd.command = Command.ADD;
			return cmd;
		}

		if (strCommand.equalsIgnoreCase("sub")) {
			cmd.commandOpc = Interpreter.SUB;
			cmd.command = Command.SUB;
			return cmd;
		}

		if (strCommand.equalsIgnoreCase("cmp")) {
			cmd.commandOpc = Interpreter.CMP;
			cmd.command = Command.CMP;
			return cmd;
		}

		if (strCommand.equalsIgnoreCase("stop")) {
			cmd.commandOpc = Interpreter.STOP;
			cmd.command = Command.STOP;
			return cmd;
		}

		String opc = strCommand;
		String remainder = "";

		for (int i = 0; i < strCommand.length(); i++) {
			if (strCommand.charAt(i) == ' ') {
				opc = strCommand.substring(0, i).trim();
				if (opc.length() != strCommand.length()) {
					remainder = strCommand.substring(i, strCommand.length())
							.trim();
				}
				break;
			}
		}

		// Visos be parametru komandos patikrintos
		// jeigu nera parametru,
		// reiskias kitos komandos netiks
		if (remainder.equals("")) {
			return null;
		}

		if (remainder.contains(",")) {
			String first = "";
			String second = "";
			try {
				first = remainder.split(",")[0].trim();
				second = remainder.split(",")[1].trim();
			} catch (Exception e) {
				return null;
			}
			if (opc.equalsIgnoreCase("loa")) {
				if (second.equalsIgnoreCase("ax")) {
					if (loadRemainderIfValid(first, cmd, 2)) {
						cmd.commandOpc = Interpreter.LOA_AX;
						cmd.command = Command.LOA_AX;
						return cmd;
					}
				}

				if (second.equalsIgnoreCase("bx")) {
					if (loadRemainderIfValid(first, cmd, 2)) {
						cmd.commandOpc = Interpreter.LOA_BX;
						cmd.command = Command.LOA_BX;
						return cmd;
					}
				}
			}

			if (opc.equalsIgnoreCase("mov")) {
				if (first.equalsIgnoreCase("ax")) {
					if (loadRemainderIfValid(second, cmd, 4)) {
						cmd.commandOpc = Interpreter.MOV_AX;
						cmd.command = Command.MOV_AX;
						return cmd;
					}
				}

				if (first.equalsIgnoreCase("bx")) {
					if (loadRemainderIfValid(second, cmd, 4)) {
						cmd.commandOpc = Interpreter.MOV_BX;
						cmd.command = Command.MOV_BX;
						return cmd;
					}
				}
			}

			if (opc.equalsIgnoreCase("sto")) {
				if (first.equalsIgnoreCase("ax")) {
					if (loadRemainderIfValid(second, cmd, 2)) {
						cmd.commandOpc = Interpreter.STO_AX;
						cmd.command = Command.STO_AX;
						return cmd;
					}
				}

				if (first.equalsIgnoreCase("bx")) {
					if (loadRemainderIfValid(second, cmd, 2)) {
						cmd.commandOpc = Interpreter.STO_BX;
						cmd.command = Command.STO_BX;
						return cmd;
					}
				}
			}

		} else {
			// 1 parametro komandos
			if (opc.equalsIgnoreCase("push")) {
				if (loadRemainderIfValid(remainder, cmd, 2)) {
					cmd.commandOpc = Interpreter.PUSH;
					cmd.command = Command.PUSH;
					return cmd;
				}
			}

			if (opc.equalsIgnoreCase("pop")) {
				if (loadRemainderIfValid(remainder, cmd, 2)) {
					cmd.commandOpc = Interpreter.POP;
					cmd.command = Command.POP;
					return cmd;
				}
			}

			if (opc.equalsIgnoreCase("ja")) {
				if (loadRemainderIfValid(remainder, cmd, 2)) {
					cmd.commandOpc = Interpreter.JA;
					cmd.command = Command.JA;
					return cmd;
				}
			}

			if (opc.equalsIgnoreCase("jb")) {
				if (loadRemainderIfValid(remainder, cmd, 2)) {
					cmd.commandOpc = Interpreter.JB;
					cmd.command = Command.JB;
					return cmd;
				}
			}

			if (opc.equalsIgnoreCase("je")) {
				if (loadRemainderIfValid(remainder, cmd, 2)) {
					cmd.commandOpc = Interpreter.JB;
					cmd.command = Command.JB;
					return cmd;
				}
			}

			if (opc.equalsIgnoreCase("je")) {
				if (loadRemainderIfValid(remainder, cmd, 2)) {
					cmd.commandOpc = Interpreter.JE;
					cmd.command = Command.JE;
					return cmd;
				}
			}

			if (opc.equalsIgnoreCase("jne")) {
				if (loadRemainderIfValid(remainder, cmd, 2)) {
					cmd.commandOpc = Interpreter.JNE;
					cmd.command = Command.JNE;
					return cmd;
				}
			}

			if (opc.equalsIgnoreCase("outm")) {
				if (loadRemainderIfValid(remainder, cmd, 2)) {
					cmd.commandOpc = Interpreter.OUTM;
					cmd.command = Command.OUTM;
					return cmd;
				}
			}

			if (opc.equalsIgnoreCase("outr")) {
				if (remainder.equalsIgnoreCase("ax")) {
					cmd.commandOpc = Interpreter.OUTR_AX;
					cmd.command = Command.OUTR_AX;
					return cmd;
				}
				if (remainder.equalsIgnoreCase("bx")) {
					cmd.commandOpc = Interpreter.OUTR_BX;
					cmd.command = Command.OUTR_BX;
					return cmd;
				}
			}
		}
		return null;
	}

	private boolean loadRemainderIfValid(String remainder, CmdWithVar cmd, int length) {
		ValidResults vr = this.isValidHex(remainder, length);
		if (vr.valid) {
			cmd.variable = vr.value;
			return true;
		} else {
			return false;
		}
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

	private ValidResults isValidHex(String remainder, int length) {
		if (remainder.length() <= length) {
			try {
				Integer.parseInt(remainder, 16);
			} catch (Exception e) {
				return new ValidResults();
			}
			return new ValidResults(Integer.parseInt(remainder, 16));
		}
		return new ValidResults();
	}

	public String toString() {
		// String s = "Ä®vesta komanda: " + this.commandStringInput + "\n";
		// s += "Komanda: " + this.command + "\n";
		// String opc = Integer.toBinaryString(this.opc);
		// if (opc.length() > 8) {
		// s += "OPC: " + opc.substring(opc.length() - 8) + "b\n";
		// } else {
		// s += "OPC: " + opc + "\n";
		// }
		// s += "ADRJ: " + Integer.toBinaryString(this.adrJ) + "b\n";
		// s += "ADRV: " + Integer.toBinaryString(this.adrO) + "b\n";
		// if (this.register == 1) {
		// s += "Registras: " + "AX " + "\n";
		// } else if (this.register == 2) {
		// s += "Registras: " + "BX " + "\n";
		// } else if (this.register == 0) {
		// s += "Registras: " + this.register + "\n";
		// }
		return "";
	}
	//
	// public byte getRegister() {
	// return register;
	// }
	//
	// public void setRegister(byte register) {
	// this.register = register;
	// }
	//
	// public byte getOpc() {
	// return opc;
	// }
	//
	// public void setOpc(byte opc) {
	// this.opc = opc;
	// }
	//
	// public byte getAdrJ() {
	// return adrJ;
	// }
	//
	// public void setAdrJ(byte adrJ) {
	// this.adrJ = adrJ;
	// }
	//
	// public byte getAdrO() {
	// return adrO;
	// }
	//
	// public void setAdrO(byte adrO) {
	// this.adrO = adrO;
	// }

}

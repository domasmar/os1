package os1.CommandsConverter;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandsConverter {
	
	private static String[] machineCommands = {
		"MOV_AX",
		"MOV_BX",
		"LOA_AX",
		"LOA_BX",
		"STO_AX",
		"STO_BX",
		"PUSH",
		"POP",
		"ADD",
		"SUB",
		"CMP",
		"JA",
		"JB",
		"JE",
		"JNE",
		"OUTR_AX",
		"OUTR_BX",
		"OUTM",
		"FORK",
		"STOP"
	};
	
	private HashMap<String, Integer> data = new HashMap<String, Integer>();	
	private ArrayList<String> commands = new ArrayList<String>();
	
	private String[] sourceCode;
	
	private static int countLines(String str) {
		String[] lines = str.split("\\r?\\n");
		return lines.length;
	}
	
	public CommandsConverter(String sourceCode) {
		int lines = countLines(sourceCode);
		this.sourceCode = new String[lines];
		this.sourceCode = sourceCode.split("\n");
		saveCommands();
		saveVariables();
	}
	
	public String[] getSourceCode() {
		return this.sourceCode;
	}
	
	public HashMap<String, Integer> getData() {
		return this.data;
	}
	
	public String[] getCommands() {
		String[] commandsArray = this.commands.toArray(new String[this.commands.size()]);
		return commandsArray;
	}
	
	/* Á sàraðà iðsaugo tik tas pirminio kodo eilutes, kuriose yra komandos */
	private void saveCommands() {
		for (int i = 0; i <= this.sourceCode.length - 1; i++) {
			for (int j = 0; j < machineCommands.length - 1; j++) {
				if (sourceCode[i].contains(machineCommands[j])) {
					this.commands.add(this.sourceCode[i]);
				}
			}
		}
	}
	
	/* Á HashMap iðsaugomos kintamøjø reikðmës ir patys kintamieji (pvz.: this.data.get("var1") = FFFF) */
	private void saveVariables() {
		for (int i = 0; i <= this.sourceCode.length - 1; i++) {
			if (this.sourceCode[i].contains("DEF")) {
				String[] definition = this.sourceCode[i].split(" DEF ");
				this.data.put(definition[0], Integer.parseInt(definition[1], 16));
			}
		}
	}

}

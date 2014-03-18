package os1.CommandsConverter;

import java.util.ArrayList;
import java.util.HashMap;

import os1.CommandsConverter.*;

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
		"JUMP",
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
	
	private HashMap<String, Integer> variables = new HashMap<String, Integer>();
	private HashMap<String, Integer> labels = new HashMap<String, Integer>();
	private ArrayList<String> commands = new ArrayList<String>();
	
	private String[] sourceCode;
	
	public CommandsConverter(String sourceCode) {
		saveSourceCode(sourceCode);
		saveCommands();
		saveVariables();
		saveLabels(findJumpVariables());
	}
	
	public String[] getSourceCode() {
		return this.sourceCode;
	}
	
	public HashMap<String, Integer> getVariables() {
		return this.variables;
	}
	
	/* Ið paduoto pirminio kodo teksto paðalina visas tuðèias eilutes ir tarpus bei á masyvà "sourceCode" áraðo kiekvienà pirminio kodo eilutæ */
	private void saveSourceCode(String sourceCode) {
		String tidySourceCode = sourceCode.replaceAll("(?m)^[ \t]*\r?\n", "").trim();
		String[] tempSourceCode = tidySourceCode.split("\n");
		this.sourceCode = new String[tempSourceCode.length + 1];
		for (int i = (tempSourceCode.length - 1); i >= 0; i--) {
			this.sourceCode[i + 1] = tempSourceCode[i];
		}
	}
	
	public String[] getCommands() {
		String[] tempCommandsArray = this.commands.toArray(new String[this.commands.size()]);
		String[] commandsArray = new String[tempCommandsArray.length + 1];
		for (int i = (tempCommandsArray.length - 1); i >= 0; i--) {
			commandsArray[i + 1] = tempCommandsArray[i];
		}
		return commandsArray;
	}
	
	/* Á sàraðà iðsaugo tik tas pirminio kodo eilutes, kuriose yra komandos */
	private void saveCommands() {
		for (int i = 1; i <= this.sourceCode.length - 1; i++) {
			for (int j = 0; j < machineCommands.length - 1; j++) {
				if (sourceCode[i].contains(machineCommands[j])) {
					this.commands.add(this.sourceCode[i]);
				}
			}
		}
	}
	
	/* Á HashMap iðsaugomi kintamieji ir jø reikðmës (pvz.: this.data.get("var1") = FFFF) */
	private void saveVariables() {
		for (int i = 1; i <= this.sourceCode.length - 1; i++) {
			if (this.sourceCode[i].contains("DEF")) {
				String[] definition = this.sourceCode[i].split(" DEF ");
				this.variables.put(definition[0], Integer.parseInt(definition[1], 16));
			}
		}
//		for (String key: variables.keySet()) {
//		    System.out.println(key);
//		}
//		
//		for (int value: variables.values()) {
//			System.out.println(value);
//		}
	}
	
	/* Á sàraðà sudedami visi JUMP, JA, JB, JE komandø rasti kintamieji */
	private ArrayList<String> findJumpVariables() {
		ArrayList<String> variables = new ArrayList<String>();
		for (int i = 1; i <= this.sourceCode.length - 1; i++) {
			if (this.sourceCode[i].contains("JUMP") ||
					this.sourceCode[i].contains("JA") ||
					this.sourceCode[i].contains("JB") ||
					this.sourceCode[i].contains("JE")) {
				variables.add(this.sourceCode[i].split(" ")[1]);
			}
		}
		return variables;
	}
	
	private void saveLabels(ArrayList<String> labels) {
		for (int i = 1; i <= this.sourceCode.length - 1; i++) {
			for (int j = 0; j <= labels.size() - 1; j++) {
				if (this.sourceCode[i].trim().equalsIgnoreCase(labels.get(j))) {
					for (int k = 0; k <= this.commands.size() - 1; k++) {
						if (this.commands.get(k).equalsIgnoreCase(this.sourceCode[i + 1])) {
							this.labels.put(labels.get(j), k + 1);
						}
					}
				}
			}
		}
	}

}

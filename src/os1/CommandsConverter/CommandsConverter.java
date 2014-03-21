package os1.CommandsConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import os1.CPU.CPU;
import os1.Memory.VMMemory;

public class CommandsConverter {
	
	private static String[] machineCommands = {
		"MOV",
		"LOA",
		"STO",
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
	
	private ArrayList<Variable> variables = new ArrayList<Variable>();
	private ArrayList<Label> labels = new ArrayList<Label>();
	
	private String[] sourceCode;
	private String[] commands;
	
	private CPU cpu;
	private VMMemory vmm;
	
	public CommandsConverter(String sourceCode, CPU cpu, VMMemory vmm) {
		this.cpu = cpu;
		this.vmm = vmm;
		saveSourceCode(sourceCode);
		saveCommands();
		saveVariables();
		saveLabels(findJumpVariables());
		replaceVarNameWithAddress();
		replaceLabelNameWithAddress();
		removeEmptyLines();
	}
	
	public String[] getSourceCode() {
		return this.sourceCode;
	}
	
	public ArrayList<Variable> getVariables() {
		return this.variables;
	}
	
	public ArrayList<Label> getLabels() {
		return this.labels;
	}
	
	/* Ið paduoto pirminio kodo teksto paðalina visas tuðèias eilutes ir tarpus bei á masyvà "sourceCode" áraðo pirminio kodo eilutæs. */
	private void saveSourceCode(String sourceCode) {
		String tidySourceCode = sourceCode.replaceAll("(?m)^[ \t]*\r?\n", "").trim();
		this.sourceCode = tidySourceCode.split("\n");
	}
	
	public String[] getCommands() {
		return this.commands;
	}
	
	
	/* Á sàraðà iðsaugo tik tas pirminio kodo eilutes, kuriose yra komandos. Gràþina komandø masyvà. */
	private void saveCommands() {
		ArrayList<String> commands = new ArrayList<String>();
		for (int i = 0; i <= this.sourceCode.length - 1; i++) {
			for (int j = 0; j < machineCommands.length - 1; j++) {
				if (this.sourceCode[i].contains(machineCommands[j])) {
					commands.add(this.sourceCode[i]);
					if (this.sourceCode[i].contains("MOV")) {
						commands.add("");
					}
				}
			}
		}
		this.commands = commands.toArray(new String[commands.size()]);
	}
	
	/* Á sàraðà iðsaugomi kintamieji ir jø reikðmës. */
	private void saveVariables() {
		for (int i = 0; i <= this.sourceCode.length - 1; i++) {
			if (this.sourceCode[i].contains("DEF")) {
				String[] definition = this.sourceCode[i].split(" DEF ");
				Variable variable = new Variable(definition[0], Integer.parseInt(definition[1], 16));
				this.variables.add(variable);
			}
		}
		for (int i = 0; i <= this.variables.size() - 1; i++) {
			vmm.setValue(cpu.getDS() + i, this.variables.get(i).getValue());
		}
	}
	
	/* Á sàraðà sudedami visi JUMP, JA, JB, JE komandø rasti kintamieji. */
	private ArrayList<String> findJumpVariables() {
		ArrayList<String> variables = new ArrayList<String>();
		for (int i = 0; i <= this.sourceCode.length - 1; i++) {
			if (this.sourceCode[i].contains("JUMP") ||
					this.sourceCode[i].contains("JA") ||
					this.sourceCode[i].contains("JB") ||
					this.sourceCode[i].contains("JE")) {
				variables.add(this.sourceCode[i].split(" ")[1]);
			}
		}
		return variables;
	}
	
	/* Á „Label“ tipo elementø sàraðà áraðom visi label'iai ir jø eilutës. */
	private void saveLabels(ArrayList<String> labels) {
		for (int i = 0; i <= this.sourceCode.length - 1; i++) {
			for (int j = 0; j <= labels.size() - 1; j++) {
				if (this.sourceCode[i].trim().equalsIgnoreCase(labels.get(j))) {
					for (int k = 0; k <= this.commands.length - 1; k++) {
						if (this.commands[k].equalsIgnoreCase(this.sourceCode[i + 1])) {
							Label label = new Label(labels.get(j), k);
							this.labels.add(label);
						}
					}
				}
			}
		}
	}
	
	/* Komanduose esanèius kintamuosius pakeièia adresais duomenø segmente. */
	private void replaceVarNameWithAddress() {
		for (int i = 0; i <= this.commands.length - 1; i++) {
			for (int j = 0; j <= this.variables.size() - 1; j++) {
				if (this.commands[i].matches(".*\\b(" + this.variables.get(j).getName() + ")\\b.*")) {
					this.commands[i] = this.commands[i].replace(this.variables.get(j).getName(), Integer.toString(j));
				}
			}
		}
	}
	
	/* Komanduose esanèius label'ius pakeièia adresais kodo segmente. */
	private void replaceLabelNameWithAddress() {
		for (int i = 0; i <= this.commands.length - 1; i++) {
			for (int j = 0; j <= this.labels.size() - 1; j++) {
				if (this.commands[i].contains(this.labels.get(j).getName())) {
					this.commands[i] = this.commands[i].replace(this.labels.get(j).getName(), Integer.toString(this.labels.get(j).getValue()));
				}
			}
		}
	}
	
	/* Panaikina tuðèias eilutes ið komandø masyvo */
	private void removeEmptyLines() {
		ArrayList<String> commandsList = new ArrayList<String>(Arrays.asList(this.commands));
		commandsList.removeAll(Collections.singleton(""));
		this.commands = commandsList.toArray(new String[commandsList.size()]);		
	}

}

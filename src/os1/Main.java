package os1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import os1.CPU.CPU;
import os1.CommandsConverter.CommandsConverter;
import os1.Interpreter.*;
import os1.Memory.RMMemory;
import os1.Memory.Stack;
import os1.Memory.VMMemory;

public class Main {

	public static void main(String args[]) {

		CPU cpu = new CPU();
		RMMemory rmm = new RMMemory(cpu);
		VMMemory vmm = rmm.createVMMemory(16);
		Interpreter interpreter = new Interpreter();
		Stack stack = new Stack(cpu, vmm);
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader("program.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String programCode = "";
		String line;
		try {
			while ((line = file.readLine()) != null) {
				programCode += line + '\n';
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CommandsConverter cc = new CommandsConverter(programCode, cpu, vmm);
		String[] sourceCode = cc.getCommands();
		
		
		try {
			int[] commandsByteCode = interpreter.interpret(sourceCode);
			int blocksNeed = commandsByteCode.length / 16;
			if (commandsByteCode.length % 16 > 0) {
				blocksNeed++;
			}
			cpu.setCS((short) (16 - blocksNeed));
			cpu.setIP((short)0);
			for (int i = 0; i < commandsByteCode.length; i++) {
				System.out.println(commandsByteCode[i]);
				vmm.setValue(16 * cpu.getCS() + i, commandsByteCode[i]);
			}			
			cpu.setDS((short)0);
			cpu.setSS((short) ((16 - cpu.getCS()) / 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(rmm.getMemory());
		ProgramExecutor pe = new ProgramExecutor(cpu, vmm, stack, true);
		
	}
}

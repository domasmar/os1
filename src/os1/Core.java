package os1;

import os1.CPU.CPU;
import os1.CommandsConverter.CommandsConverter;
import os1.GUI.MainGUI;
import os1.GUI.RMMemoryGUI;
import os1.GUI.VMMemoryGUI;
import os1.Interpreter.Interpreter;
import os1.Interpreter.ProgramExecutor;
import os1.Memory.RMMemory;
import os1.Memory.Stack;
import os1.Memory.VMMemory;

public class Core {
	
	private CPU cpu;
	private RMMemory rmm;
	private VMMemory vmm;
	private Interpreter interpreter;
	private Stack stack;
	private ProgramExecutor programExecutor;
	private int[] commandsByteCode;
	
	public Core() {		
		cpu = new CPU();
		rmm = new RMMemory(cpu);
		vmm = rmm.createVMMemory(16);
		interpreter = new Interpreter();
		stack = new Stack(cpu, vmm);
		programExecutor = new ProgramExecutor(cpu, vmm, stack, false);
		
	}
	
	public void loadProgram(String program) throws Exception {		
		CommandsConverter cc = new CommandsConverter(program, cpu, vmm);
		String[] sourceCode = cc.getCommands();
		
		commandsByteCode = interpreter.interpret(sourceCode);

		allocateMemorySegments();
		
		for (int i = 0; i < commandsByteCode.length; i++) {
			vmm.setValue(16 * cpu.getCS() + i, commandsByteCode[i]);
		}			
	}
	
	private void initGUI() {
		VMMemoryGUI vmmGUI = new VMMemoryGUI(vmm);
		RMMemoryGUI rmmGUI = new RMMemoryGUI(rmm);
		MainGUI mainGUI = new MainGUI(this);
	}
	
	public void startVM(boolean debug) {
		initGUI();
		
	}
	
	public void executeNext() {
		if (programExecutor.executeNext()) {
			
		} else {
			System.out.println("The End");
		}
	}	
	
	private void allocateMemorySegments() {
		int blocksNeed = commandsByteCode.length / 16;
		if (commandsByteCode.length % 16 > 0) {
			blocksNeed++;
		}
		cpu.setCS((short) (16 - blocksNeed));
		cpu.setIP((short)0);
		cpu.setDS((short)0);
		cpu.setSS((short) ((16 - cpu.getCS()) / 2));
	}


	
}

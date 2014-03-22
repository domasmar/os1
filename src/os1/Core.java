package os1;

import os1.CPU.CPU;
import os1.CommandsConverter.CommandsConverter;
import os1.GUI.CpuGUI;
import os1.GUI.MainGUI;
import os1.GUI.RMMemoryGUI;
import os1.GUI.VMLogger;
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
	private MainGUI mainGUI;
	private VMMemoryGUI vmmGUI;
	private RMMemoryGUI rmmGUI;
	private CpuGUI cpuGUI;

	public Core() {
		cpu = new CPU();
		rmm = new RMMemory(cpu);
		vmm = rmm.createVMMemory(16);
		interpreter = new Interpreter();
		stack = new Stack(cpu, vmm);
		programExecutor = new ProgramExecutor(cpu, vmm, stack);

	}

	public void loadProgram(String program) throws Exception {
		CommandsConverter cc = new CommandsConverter(program, cpu, vmm);
		String[] sourceCode = cc.getCommands();

		commandsByteCode = interpreter.interpret(sourceCode);

		allocateMemorySegments();

		for (int i = 0; i < interpreter.getProgramSize(); i++) {
			vmm.setValue(cpu.getCS() + i, commandsByteCode[i]);
		}
	}

	private void updateGUI() {
		cpuGUI.update();
		rmmGUI.update();
		vmmGUI.update();
	}

	private void initGUI() {
		vmmGUI = new VMMemoryGUI(vmm);
		rmmGUI = new RMMemoryGUI(rmm);
		cpuGUI = new CpuGUI(cpu);

		mainGUI = new MainGUI(this);
		mainGUI.addMem(vmmGUI.getPanel());
		mainGUI.addMem(rmmGUI.getPanel());
		mainGUI.addCPU(cpuGUI.getPanel());

		mainGUI.setVisible(true);

	}

	public void startVM(boolean debug) {
		initGUI();

	}

	public void executeNext() {
		if (programExecutor.executeNext()) {
			this.updateGUI();
			VMLogger.newMessage("Command executed: " + this.getLastCommand());
		} else {
			VMLogger.newMessage("The end");
		}
	}
	
	public void executeAll() {
		while (programExecutor.executeNext()) {
			this.updateGUI();
			VMLogger.newMessage("Command executed: " + this.getLastCommand());
		}
		VMLogger.newMessage("The end");
	}

	public String getLastCommand() {
		return programExecutor.getLastCommand();
	}

	private void allocateMemorySegments() {
		int blocksNeed = interpreter.getProgramSize() / 16;
		blocksNeed += interpreter.getProgramSize() % 16 > 0 ? 1 : 0;
		System.out.println();
		cpu.setCS((short) ((16 - blocksNeed) * 16));
		cpu.setIP((short) 0);
		cpu.setDS((short) 0);
		cpu.setSS((short) (cpu.getCS() / 2));
	}

}

package os1;

import os1.GUI.VMLogger;
import os1.Interpreter.ProgramExecutor;
import os1.Memory.Stack;
import os1.Memory.VMMemory;

public class VM {

	private VMMemory vmm;
	private ProgramExecutor programExecutor;
	private Stack stack;
	private Core core;
	
	public VM(VMMemory vmm, Stack stack, ProgramExecutor programExecutor, Core core) {
		this.vmm = vmm;
		this.stack = stack;
		this.programExecutor = programExecutor;
		this.core = core;
	}
	
	public void executeNext() {
		try {
			if (programExecutor.executeNext()) {
				core.updateGUI();
				VMLogger.newMessage("Command executed: "
						+ this.getLastCommand());
			} else {
				VMLogger.newMessage("The end");
			}
			core.getInterruptChecker().checkInterrupts();
		} catch (Exception e) {
			VMLogger.newErrorMessage("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void executeAll() {
		try {
			while (programExecutor.executeNext()) {
				core.updateGUI();
				VMLogger.newMessage("Command executed: "
						+ this.getLastCommand());
				core.getInterruptChecker().checkInterrupts();
			}
		} catch (Exception e) {
			VMLogger.newErrorMessage("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
		VMLogger.newMessage("The end");
	}
	
	public void stop() {
		VMLogger.newSuccessMessage("VM stopped");
	}

	public String getLastCommand() {
		return programExecutor.getLastCommand();
	}

	
}
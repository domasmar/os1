package os1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

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
import os1.PeripheralDevices.HDD;
import os1.PeripheralDevices.InputDevice;
import os1.PeripheralDevices.Serialization;

public class Core {

	private CPU cpu;
	private RMMemory rmm;
	private Interpreter interpreter;
	private int[] commandsByteCode;
	private MainGUI mainGUI;
	private VMMemoryGUI vmmGUI;
	private RMMemoryGUI rmmGUI;
	private CpuGUI cpuGUI;
	private VM vm;
	private InterruptChecker ic;
	private HDD hdd;
	
	public Core() {
		VMLogger.init();
		cpu = new CPU();
		rmm = new RMMemory(cpu);	
		interpreter = new Interpreter();		
		ic = new InterruptChecker(this);
		initGUI();
	}

	public void updateGUI() {
		cpuGUI.update();
		rmmGUI.update();
		if (vmmGUI != null) {
			vmmGUI.update();
		}
	}

	private void initGUI() {
		rmmGUI = new RMMemoryGUI(rmm);
		cpuGUI = new CpuGUI(cpu);

		mainGUI = new MainGUI(this);
		mainGUI.addMem(rmmGUI.getPanel());
		mainGUI.addCPU(cpuGUI.getPanel());

		mainGUI.setVisible(true);
	}
	
	public void loadHDD() {
		hdd = Serialization.deserializeMemory();
		if (hdd == null) {
			VMLogger.newErrorMessage("Nepavyko u�krauti HDD");
			VMLogger.newErrorMessage("Kuriame tuscia HDD");
			Serialization.serializeMemory(new HDD());
			loadHDD();
		} else {
			VMLogger.newSuccessMessage("HDD sekmingai u�krautas");
		}
		
	}
	
	public void loadFromExternalFile(String path) {
		try {
			int length = hdd.getFilesList().length;
			new InputDevice(path, cpu, hdd);
			VMLogger.newSuccessMessage("\"Flash\" �ranginys u�krautas. U�kraut� fail� skai�ius: " + (hdd.getFilesList().length - length));
			mainGUI.loadHddData(hdd.getFilesList());
		} catch (Exception e) {
			VMLogger.newErrorMessage("Nepavyko u�krauto \"Flash\" �ranginio");
		}
	}
	
	public void loadVM(String program) {
		VMMemory vmm = rmm.createVMMemory(16);
		Stack stack = new Stack(cpu, vmm);
		ProgramExecutor programExecutor = new ProgramExecutor(cpu, vmm, stack);		
		
		vm = new VM(vmm, stack, programExecutor, this);
		
		CommandsConverter cc = null;
		try {
			cc = new CommandsConverter(program, cpu, vmm);
			String[] sourceCode = cc.getCommands();
			commandsByteCode = interpreter.interpret(sourceCode);
			allocateMemorySegments();

			for (int i = 0; i < interpreter.getProgramSize(); i++) {
				vmm.setValue(cpu.getCS() + i, commandsByteCode[i]);
			}			
		} catch (Exception e) {
			VMLogger.newErrorMessage(e.getMessage());
		} 
		updateGUI();
		mainGUI.addMem((new VMMemoryGUI(vmm)).getPanel());
		VMLogger.newSuccessMessage("Programa sekmingai u�krauta!");
	}
	
	private void allocateMemorySegments() {
		int blocksNeed = interpreter.getProgramSize() / 16;
		blocksNeed += interpreter.getProgramSize() % 16 > 0 ? 1 : 0;
		cpu.setCS((short) ((16 - blocksNeed) * 16));
		cpu.setIP((short) 0);
		cpu.setDS((short) 0);
		cpu.setSS((short) (cpu.getCS() / 2));
	}
	
	public VM getVM() {
		return this.vm;
	}
	
	public CPU getCPU() {
		return this.cpu;
	}
	
	public InterruptChecker getInterruptChecker() {
		return this.ic;
	}

}

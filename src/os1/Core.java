package os1;

import java.io.UnsupportedEncodingException;

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
import os1.PeripheralDevices.ChannelsDevice;
import os1.PeripheralDevices.HDD;
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
	private ChannelsDevice cd;
	private HDD hdd;

	public Core() {
		VMLogger.init();
		cpu = new CPU();
		rmm = new RMMemory(cpu);
		interpreter = new Interpreter();
		ic = new InterruptChecker(this);
		cd = new ChannelsDevice(cpu);
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
			VMLogger.newErrorMessage("Nepavyko užkrauti HDD");
			VMLogger.newMessage("Kuriame tuščia HDD");
			Serialization.serializeMemory(new HDD());
			loadHDD();
		} else {
			try {
				VMLogger.newSuccessMessage("HDD sekmingai užkrautas. Užkrauti failai: "
						+ hdd.getFilesList().length);
				mainGUI.loadHddData(hdd.getFilesList());
			} catch (UnsupportedEncodingException e) {
				VMLogger.newErrorMessage("HDD užkrauti nepavyko, nes jame raste neatpažintų simbolių");
			}
		}
	}

	public void loadFromExternalFile(String path) {
		try {
			int l = hdd.getFilesList().length;
			cd.stickInputDevice(path, hdd);
			mainGUI.loadHddData(hdd.getFilesList());
			Serialization.serializeMemory(hdd);
			VMLogger.newSuccessMessage("\"Flash\" užkrautas. Įkelta failų: "
					+ (hdd.getFilesList().length - l));
		} catch (Exception e) {
			VMLogger.newSuccessMessage("\"Flash\" užkrauti nepavyko. " + e.getMessage());
		}
	}

	public void loadVM(int index) {
		if (vm != null) {
			VMLogger.newErrorMessage("Negalima užkrauti kelių programų vienu metu");
			return;
		}
		String fileName;
		String program = "";
		try {
			fileName = hdd.getFilesList()[index];
			program = hdd.get(fileName).getFileContent();
		} catch (UnsupportedEncodingException e1) {
			VMLogger.newErrorMessage("Įvyko klaida " + e1.getMessage());
		} catch (Exception e1) {
			VMLogger.newErrorMessage("Įvyko klaida. " + e1.getMessage());
		}

		VMMemory vmm = rmm.createVMMemory(16);
		Stack stack = new Stack(cpu, vmm);
		ProgramExecutor programExecutor = new ProgramExecutor(cpu, vmm, stack,
				cd);

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
		vmmGUI = new VMMemoryGUI(vmm);
		mainGUI.addMem(vmmGUI.getPanel());
		VMLogger.newSuccessMessage("Programa sekmingai užkrauta!");
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

	public void destroyVM() {
		mainGUI.removePanel(vmmGUI.getPanel());;
		vm = null;		
	}

	public void deleteFileFromHDD(int selectedRow) {
		try {
			String name = this.hdd.getFilesList()[selectedRow];
			hdd.delete(name);
			Serialization.serializeMemory(hdd);
		} catch (UnsupportedEncodingException e) {
			VMLogger.newErrorMessage("Įvyko klaida trinant failą. " + e.getMessage());
		} catch (Exception e) {
			VMLogger.newErrorMessage("Įvyko klaida trinant failą. " + e.getMessage());
		}
		
	}

}
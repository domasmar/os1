package os1;

import os1.CPU.CPU;
import os1.GUI.VMLogger;

public class InterruptChecker {

	private CPU cpu;
	private Core core;

	public InterruptChecker(Core core) {
		this.cpu = core.getCPU();
		this.core = core;
	}

	public void checkInterrupts() {
		if (cpu.getPI() == 1) {
			VMLogger.newErrorMessage("PI = 1. Bloga adresacija");
			core.getVM().stop();
		}

		if (cpu.getPI() == 2) {
			VMLogger.newErrorMessage("PI = 2. Blogas operacijos kodas");
			core.getVM().stop();
		}
		
		if (cpu.getSI() == 1) {
			VMLogger.newErrorMessage("SI = 1. Skaitom duomenis iš įvedimo įrenginio");
			return;
		}
		
		if (cpu.getSI() == 2) {
			VMLogger.newErrorMessage("SI = 2. įrašom duomenis į išvedimo įrengino");
			return;
		}
		
		if (cpu.getSI() == 3) {
			VMLogger.newErrorMessage("SI = 3. Stabdom programą");
			core.getVM().stop();
		}
		
		if (cpu.getIOI() == 7) {
			VMLogger.newMessage("IOI = 7. 0, 1 , 2 kanalų petraukimai");
		}
		if (cpu.getIOI() == 6) {
			VMLogger.newMessage("IOI = 6. 1, 2 kanalų petraukimai");
		}
		if (cpu.getIOI() == 5) {
			VMLogger.newMessage("IOI = 5. 0, 2 kanalų petraukimai");
		}
		if (cpu.getIOI() == 4) {
			VMLogger.newMessage("IOI = 4. 2 kanalo petraukimas");
		}
		if (cpu.getIOI() == 3) {
			VMLogger.newMessage("IOI = 3. 0, 1 kanalų petraukimai");
		}
		if (cpu.getIOI() == 2) {
			VMLogger.newMessage("IOI = 3. 1 kanalo petraukimas");
		}
		if (cpu.getIOI() == 1) {
			VMLogger.newMessage("IOI = 3. 0 kanalo petraukimas");
		}
		
		


	}
}

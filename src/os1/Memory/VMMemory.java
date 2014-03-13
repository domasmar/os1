package os1.Memory;

import os1.CPU.CPU;

public class VMMemory {

	private RMMemory memory;
	private CPU cpu;

	public VMMemory(RMMemory memory, CPU cpu) {
		this.memory = memory;
		this.cpu = cpu;
	}

	public int getValue(int index) {
		if (index / 16 <= cpu.getPTR() / (16*16)) {
			int ptrA2 = cpu.getPTR() / 16 % 16; 
			int ptrA3 = cpu.getPTR() % 16;							
			int realBlock =  memory.getValue((16 * ptrA2 + ptrA3) * 16 + index / 16);
			return memory.getValue(realBlock * 10 + index % 16);			
		}
		return 0;
	}

	public void setValue(int index, int value) {
		if (index / 16 <= cpu.getPTR() / (16*16)) {
			
		}
	}

}

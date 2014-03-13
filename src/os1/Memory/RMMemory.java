package os1.Memory;

import os1.CPU.CPU;

public class RMMemory {

	private final Memory memory;
	
	private CPU cpu;
	
	public RMMemory(CPU cpu) {
		this.cpu = cpu;
		this.memory = new Memory(4096, 16);
	}

	public int getFromMemory(int index) {
		return memory.getValueByIndex(index);
	}

	public VMMemory createVMMemory(int blocks) {
		int ptr = 0;
		try {
			ptr = this.memory.getAndFillFreePage(blocks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cpu.setPTR(ptr);
		return new VMMemory(this, cpu);
	}
	
	public int getValue(int adress) {
		return memory.getValue(adress);
	}
	
	public void setValue(int adress, int value) {
		memory.setValue(adress, value);
	}
	
	public Memory getMemory() {
		return memory;
	}

}

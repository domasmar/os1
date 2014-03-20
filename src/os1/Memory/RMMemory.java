package os1.Memory;

import os1.CPU.CPU;

public class RMMemory {

	private final Memory memory;

	private CPU cpu;
	private int size;
	
	public RMMemory(CPU cpu) {
		this.cpu = cpu;
		this.setSize(1024);
		this.memory = new Memory(1024, 16);
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
		return new VMMemory(this, cpu, blocks * 16);
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}

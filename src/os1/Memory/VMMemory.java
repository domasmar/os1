package os1.Memory;

import os1.CPU.CPU;

public class VMMemory {

	private RMMemory memory;
	private CPU cpu;
	private int size;

	public VMMemory(RMMemory memory, CPU cpu, int size) {
		this.memory = memory;
		this.cpu = cpu;
		this.setSize(size);
	}

	private int getRealAddress(int index) {
		int ptrA2 = cpu.getPTR() / 16 % 16;
		int ptrA3 = cpu.getPTR() % 16;
		int realBlock = memory.getValue((16 * ptrA2 + ptrA3) * 16 + index / 16);

		return realBlock * 16 + index % 16;
	}

	public int getValue(int index) {
		if (index / 16 <= cpu.getPTR() / (16 * 16)) {
			return memory.getValue(getRealAddress(index));
		} else {
			cpu.setPI((byte) 1);
			throw new RuntimeException("PI = 1");
		}
	}

	public void setValue(int index, int value) {
		if (index / 16 <= cpu.getPTR() / (16 * 16)) {
			memory.setValue(getRealAddress(index), value);
		} else {
			cpu.setPI((byte) 1);
			throw new RuntimeException("PI = 1");
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}

package os1.Memory;

public class VMMemory {
	
	private Memory memory;
	private int from;
	private int size;
	
	public VMMemory(Memory memory, int size) {
		this.memory = memory;	
		this.from = this.memory.getFreeBlocks(16);
		this.size = size;
	}
	
}

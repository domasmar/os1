package os1.Memory;

public class RMMemory {

	private static final int VIRTUAL_MASCHINE_BLOCKS_COUNT = 16;
	
	private final Memory memory;
	
	public RMMemory() {
		this.memory = new Memory(4096, 16);
	}
	
	public int loadToMemory() {
		return 0;
	}
	
	public int getFromMemory(int index) {
		return memory.getValueByIndex(index);
	}
	
	public VMMemory getVMMemory() {
		return new VMMemory(this.memory, RMMemory.VIRTUAL_MASCHINE_BLOCKS_COUNT);
	}
	
}

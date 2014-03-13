package os1.Memory;

public class Memory {

	private static final int DEFAULT_MEMORY_SIZE = 4096;
	private static final int DEFAULT_BLOCK_SIZE = 16;
	
	
	private int memorySize;
	private int blockSize;
	private int[] allMemory;
	
	public Memory() {
		this.createDefaultMemory();
	}
	
	private void createDefaultMemory() {
		this.memorySize = Memory.DEFAULT_MEMORY_SIZE;
		this.blockSize = Memory.DEFAULT_BLOCK_SIZE;
		this.allMemory = new int[Memory.DEFAULT_MEMORY_SIZE];
	}
	
	public Memory(int memorySize, int blockSize) {
		if (memorySize < 0) {
			this.createDefaultMemory();
		} else {
			this.memorySize = memorySize;
			this.blockSize = blockSize;
			this.allMemory = new int[memorySize];		
		}
	}
	
	public int getValueByIndex(int index) {
		return allMemory[index];
	}

	public int getFreeBlocks(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

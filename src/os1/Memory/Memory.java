package os1.Memory;

import java.util.Random;

public class Memory {

	public static int DEFAULT_MEMORY_SIZE = 4096;
	public static int DEFAULT_BLOCK_SIZE = 16;
	public static int PAGES_COUNT = 16;

	private int memorySize;
	private int blockSize;
	private int[] allMemory;

	public Memory() {
		this.createDefaultMemory();
	}

	private void createDefaultMemory() {
		this.setMemorySize(Memory.DEFAULT_MEMORY_SIZE);
		this.setBlockSize(Memory.DEFAULT_BLOCK_SIZE);
		this.allMemory = new int[Memory.DEFAULT_MEMORY_SIZE];
	}

	public Memory(int memorySize, int blockSize) {

		if (memorySize < 0) {
			this.createDefaultMemory();
		} else {
			this.setMemorySize(memorySize);
			this.setBlockSize(blockSize);
			this.allMemory = new int[memorySize];
		}
	}

	public void freePage(int index) {

	}

	private boolean isPageBlockFree(int index) {
		for (int i = 0; i < blockSize; i++) {
			if (allMemory[index + i] != 0) {
				return false;
			}
		}
		return true;
	}

	public int getAndFillFreePage(int blocks) throws Exception {
		for (int i = 0; i < Memory.PAGES_COUNT; i++) {
			int blockNumber = this.blockSize * i;
			if (isPageBlockFree(blockNumber)) {
				this.fillFreePage(blockNumber, blocks);
				return blocks * 15 * 16 + blockNumber;
			}
		}
		throw new Exception("Atmintyje nebëra vietos sukurti VM!");
	}

	private boolean blockNotTaken(int block) {
		for (int i = 0; i < PAGES_COUNT * blockSize; i++)
			if (allMemory[i] == block)
				return false;
		return true;
	}

	private void fillFreePage(int blockNumber, int blocks) {
		int i = 0;

		while (blocks > i) {
			Random random = new Random();
			int randomBlock = random.nextInt(memorySize / blockSize
					- PAGES_COUNT)
					+ PAGES_COUNT;
			if (blockNotTaken(randomBlock)) {
				allMemory[blockNumber + i] = randomBlock;
				i++;
			}
		}
	}

	public int getValueByIndex(int index) {
		return allMemory[index];
	}

	public int getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public String toString() {
		String a = "";
		a += "Memory size: " + memorySize + "\n";
		a += "Block size: " + blockSize + "\n";
		for (int i = 0; i < allMemory.length; i++) {
			a += i + " : " + allMemory[i] + "\n";
		}
		return a;
	}

	public int getValue(int adress) {
		return this.allMemory[adress];
	}

	public void setValue(int adress, int value) {
		this.allMemory[adress] = value;
	}

}

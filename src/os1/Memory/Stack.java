package os1.Memory;

import os1.CPU.CPU;

/**
 *
 * @author Arturas
 */
public class Stack {

    private CPU cpu;
    private VMMemory memory;

    public Stack(CPU cpu, VMMemory memory) {
        this.cpu = cpu;
        this.memory = memory;
    }

    public void Push(int value) throws Exception {
        int maxValue = 9999; //pakeisti į ribas
        if (cpu.getSP() <= maxValue) {
            throw new Exception("Stack is full!");
        }
        memory.setValue(cpu.getSS() + cpu.getSP() + 1, value);
        cpu.setSP((short) (cpu.getSP() + 1));
    }

    public int Pop() throws Exception {
        if (cpu.getSP() <= 0) {
            throw new Exception("Stack is empty!");
        }
        int value = memory.getValue(cpu.getSS() + cpu.getSP());
        cpu.setSP((short) (cpu.getSP() - 1));
        return value;
    }
}

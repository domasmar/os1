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
    
    public void push(int value) throws Exception {
        if (cpu.getSS() + cpu.getSP() >= cpu.getCS()) {
            cpu.setSTI((byte) 1);
            throw new Exception("Stekas pilnas!");
        }
        cpu.setSP((short) (cpu.getSP() + 1));
        memory.setValue(cpu.getSS() + cpu.getSP(), memory.getValue(value));
    }
    
    public int pop(int addrDS) throws Exception {
        if (cpu.getSP() <= 0) {
            cpu.setSTI((byte) 1);
            throw new Exception("Stekas tuščias!");
        }
        int value = memory.getValue(cpu.getSS() + cpu.getSP());
        cpu.setSP((short) (cpu.getSP() - 1));     
        memory.setValue(addrDS, value);
        return value;
    }
}

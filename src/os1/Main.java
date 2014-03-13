package os1;

import java.util.Random;

import os1.CPU.CPU;
import os1.Interpreter.CommandInterpreter;
import os1.Memory.RMMemory;
import os1.Memory.VMMemory;

public class Main {

	public static void main(String args[]) {

		CPU cpu = new CPU();
		RMMemory rmMem = new RMMemory(cpu);
		VMMemory vmMem = rmMem.createVMMemory(16);

		for (int i = 0; i < 258; i++) {
			Random random = new Random();
			int value = random.nextInt(102424424);
			vmMem.setValue(i, value);
			if (vmMem.getValue(i) != value) {
				System.out.println("kazkas blogai");
			}
		}

		System.out.println(rmMem.getMemory());

	}

}

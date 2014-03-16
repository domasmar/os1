package os1;

import java.util.ArrayList;
import java.util.Random;

import os1.CPU.CPU;
import os1.Interpreter.Interpreter;
import os1.Memory.RMMemory;
import os1.Memory.VMMemory;

public class Main {

	public static void main(String args[]) {
//		try { 
//			Interpreter ci = new Interpreter();//vietoj parametrų paduot string arba int masyvą(source kodas arba baitkodas)
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
//                
//		CPU cpu = new CPU();
//		RMMemory rmMem = new RMMemory(cpu);
//		VMMemory vmMem = rmMem.createVMMemory(16);

//		for (int i = 0; i < 258; i++) {
//			Random random = new Random();
//			int value = random.nextInt(102424424);
//			vmMem.setValue(i, value);
//			if (vmMem.getValue(i) != value) {
//				System.out.println("kazkas blogai");
//			}
//		}

//		System.out.println(rmMem.getMemory());
		
		System.out.println(0b1000_0010);
		
		String[] commands = {new String("ADD"),
				new String ("POP 12"),
				new String ("MOV ax, 4343"),
				new String ("MOV ax, 1234"),
				new String ("MOV ax, 1234"),
				new String ("STO ax, 12")
				};
		
		try {
			Interpreter inter = new Interpreter();
			int[] c = inter.interpret(commands);
			for (int i = 0; i < 10; i++) {
				System.out.println(Integer.toBinaryString(c[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

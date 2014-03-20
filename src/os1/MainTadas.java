package os1;

import os1.CPU.*;
import os1.CommandsConverter.*;
import os1.Memory.RMMemory;
import os1.Memory.VMMemory;

public class MainTadas {
	
	public static void main(String[] args) {
		
		CPU cpu = new CPU();
		RMMemory rmm = new RMMemory(cpu);
		VMMemory vmm = rmm.createVMMemory(16);
		
		String sourceCode =
				"as DEF FF\n"
			  + "labas DEF EE\n"
			  + "petras DEF F0\n"
			  + "astra DEF AA\n"
			  + "\n"
			  + "\n"
			  + "MOV ax, FFFF\n"
			  + "sudetis\n"
			  + "STO ax, as\n"
			  + "MOV bx, EEEE\n"
			  + "PUSH AX\n"
			  + "PUSH BX\n"
			  + "ADD\n"
			  + "JUMP sudetis\n"
			  + "\n"
			  + "\n"
			  + "atimtis\n"
			  + "STO ax, petras\n"
			  + "STO bx, astra\n"
			  + "SUB\n"
			  + "JUMP atimtis";
		
		CommandsConverter cc = new CommandsConverter(sourceCode, cpu, vmm);
		
		/* Visas programos pirminis kodas */
		System.out.println("PIRMINIS KODAS:");
		for(int i = 0; i <= cc.getSourceCode().length - 1; i++) {
			System.out.println(cc.getSourceCode()[i]);
		}
		
		System.out.println();
		
		/* Visos programos pirminio kodo komandos */
		System.out.println("KOMANDOS:");
		for(int i = 0; i <= cc.getCommands().length - 1; i++) {
			System.out.println(cc.getCommands()[i]);
		}
		
		System.out.println();
		
		/* Kintam�j� reik�m�s */
		System.out.println("KINTAMIEJI IR J� REIK�M�S:");
		for(int i = 0; i <= cc.getVariables().size() - 1; i++) {
//			vmm.setValue(cpu.getDS() + i, cc.getVariables().get("var" + i));
			System.out.println(cc.getVariables().get(i).getName() + ": " + cc.getVariables().get(i).getValue());
//			System.out.println(vmm.getValue(cpu.getDS() + i));
		}
		
		System.out.println();
		
		/* Label'iai ir komanda, � kuri� �okama */
		System.out.println("LABEL'IAI IR KOMAND� INDEKSAI:");
		for (int i = 0; i <= cc.getLabels().size() - 1; i++) {
			System.out.println(cc.getLabels().get(i).getName() + " => " + cc.getLabels().get(i).getValue());
		}
		
//		System.out.println(rmm.getMemory());
		
	}

}

package os1;

import os1.CPU.*;
import os1.CommandsConverter.*;
import os1.Memory.RMMemory;
import os1.Memory.VMMemory;

public class MainTadas {
	
	public static void main(String[] args) throws Exception {
		
		CPU cpu = new CPU();
		RMMemory rmm = new RMMemory(cpu);
		VMMemory vmm = rmm.createVMMemory(16);
		
		String sourceCode =
				"as DEF ff\n"
			  + "labas DEF EE\n"
			  + "petras DEF F0\n"
			  + "astra DEF AA\n"
			  + "\n"
			  + "\n"
			  + "MOV ax, FFFF\n"
			  + "sudetis\n"
			  + "STO ax, as\n"
			  + "MOV bx, EEEE\n"
			  + "PUSH labas\n"
			  + "PUSH petras\n"
			  + "ADD\n"
			  + "JMP sudetis\n"
			  + "\n"
			  + "\n"
			  + "atimtis\n"
			  + "STO ax, petras\n"
			  + "STO bx, astra\n"
			  + "LOA astra, bx\n"
			  + "SUB\n"
			  + "JMP atimtis";
		
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
		
		/* Kintamųjų reikšmės */
		System.out.println("KINTAMIEJI IR JŲ REIKŠMĖS:");
		for(int i = 0; i <= cc.getVariables().size() - 1; i++) {
			System.out.println(cc.getVariables().get(i).getName() + ": " + cc.getVariables().get(i).getValue());
		}
		
		System.out.println();
		
		/* Label'iai ir komanda, į kurią šokama */
		System.out.println("LABEL'IAI IR KOMANDŲ INDEKSAI:");
		for (int i = 0; i <= cc.getLabels().size() - 1; i++) {
			System.out.println(cc.getLabels().get(i).getName() + " => " + cc.getLabels().get(i).getValue());
		}
		
	}

}

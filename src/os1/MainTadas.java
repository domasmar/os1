package os1;

import os1.CPU.*;
import os1.CommandsConverter.*;

public class MainTadas {
	
	public static void main(String[] args) {
		String sourceCode =
				"var1 DEF FF\n"
			  + "var2 DEF EE\n"
			  + "var3 DEF F0\n"
			  + "var4 DEF AA\n"
			  + "\n"
			  + "\n"
			  + "sudetis\n"
			  + "MOV_AX var1\n"
			  + "MOV_BX var2\n"
			  + "PUSH AX\n"
			  + "PUSH BX\n"
			  + "ADD\n"
			  + "JUMP sudetis\n"
			  + "\n"
			  + "\n"
			  + "atimtis\n"
			  + "MOV_AX var3\n"
			  + "MOV_BX var4\n"
			  + "SUB\n"
			  + "JUMP atimtis";
		
		CommandsConverter cc = new CommandsConverter(sourceCode);
		
		/* Visas programos pirminis kodas */
		System.out.println("PIRMINIS KODAS:");
		for(int i = 1; i <= cc.getSourceCode().length - 1; i++) {
			System.out.println(i + 1 + ". " + cc.getSourceCode()[i]);
		}
		
		System.out.println();
		
		/* Visos programos pirminio kodo komandos */
		System.out.println("KOMANDOS:");
		for(int i = 1; i <= cc.getCommands().length - 1; i++) {
			System.out.println(i + ". " + cc.getCommands()[i]);
		}
		
		System.out.println();
		
		/* Kintamøjø reikðmës */
		System.out.println("KINTAMIEJI IR JØ REIKÐMËS:");
		for(int i = 1; i <= cc.getVariables().size(); i++) {
			System.out.println("var" + i + ": " + cc.getVariables().get("var" + i));
		}
	}

}

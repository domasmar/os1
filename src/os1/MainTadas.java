package os1;

import os1.CPU.*;
import os1.CommandsConverter.*;

public class MainTadas {
	
	public static void main(String[] args) {
		String sourceCode =
				"var1 DEF FF\n"
			  + "var2 DEF EE\n"
			  + "var3 DEF F0\n"
			  + "priskyrimas\n"
			  + "MOV_AX var1\n"
			  + "MOV_BX var2\n"
			  + "PUSH AX\n"
			  + "PUSH BX\n"
			  + "ADD\n"
			  + "JUMP priskyrimas";
		
		CommandsConverter cc = new CommandsConverter(sourceCode);
		
		/* Visas programos pirminis kodas */
		for(int i = 0; i <= cc.getSourceCode().length - 1; i++) {
			System.out.println(i + ". " + cc.getSourceCode()[i]);
		}
		
		System.out.println();
		
		/* Visos programos pirminio kodo komandos */
		for(int i = 0; i <= cc.getCommands().length - 1; i++) {
			System.out.println(i + ". " + cc.getCommands()[i]);
		}
		
		System.out.println();
		
		/* Kintamøjø reikðmës */
		for(int i = 1; i <= cc.getData().size(); i++) {
			System.out.println(cc.getData().get("var" + i));
		}
	}

}

package os1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import os1.CPU.CPU;
import os1.CommandsConverter.CommandsConverter;
import os1.Interpreter.*;
import os1.Memory.RMMemory;
import os1.Memory.Stack;
import os1.Memory.VMMemory;

public class Main {

	public String getProgram(String fileName) {
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String programCode = "";
		String line;

		try {
			while ((line = file.readLine()) != null) {
				programCode += line + '\n';
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return programCode;
	}

	public static void main(String args[]) {

		Main main = new Main();

		String programCode = main.getProgram("program.txt");

		Core core = new Core();

		// core.startVM(programCode);

	}
}

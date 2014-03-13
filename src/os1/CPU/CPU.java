package os1.CPU;
import os1.Registers.*;

public class CPU {
	
	Register4B AX;
	Register4B BX;
	Register4B PTR;
	
	Register2B IP;
	Register2B SP;
	TimerRegister TIMER;
	
	Register1B C;
	Register1B[] CHST;
	Register1B MODE;
	
	InterruptRegister PI;
	InterruptRegister SI;
	InterruptRegister IOI;
	InterruptRegister TI;
	
	public CPU() {
		this.AX = new Register4B();
		this.BX = new Register4B();
		this.PTR = new Register4B();
		
		this.IP = new Register2B();
		this.SP = new Register2B();
		this.TIMER = new TimerRegister();
		
		this.C = new LogicalRegister();
		this.CHST = new LogicalRegister[3];
		this.MODE = new LogicalRegister();
		
		this.PI = InterruptRegister(0);
		this.SI = InterruptRegister(0);
		this.IOI = InterruptRegister(0);
		this.TI = InterruptRegister(0);
	}
	
}

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
	
	@Override
	public String toString() {
		String info = 
				"";
		return info;
	}
	
// GETTERS AND SETTERS OF 4 BYTE REGISTERS
	
	public int getAX() {
		return this.AX.getValue();
	}
	
	public void setAX(int value) {
		this.AX.setValue(value);
	}
	
	public int getBX() {
		return this.BX.getValue();
	}
	
	public void setBX(int value) {
		this.BX.setValue(value);
	}
	
	public int getPTR() {
		return this.PTR.getValue();
	}
	
	public void setPTR(int value) {
		this.PTR.setValue(value);
	}
	
	
// GETTERS AND SETTERS OF 2 BYTE REGISTERS
	
	public short getIP() {
		return this.IP.getValue();
	}
	
	public void setIP(short value) {
		this.IP.setValue(value);
	}
	
	public short getSP() {
		return this.SP.getValue();
	}
	
	public void setSP(short value) {
		this.SP.setValue(value);
	}
	
// GETTER AND SETTER OF TIMER REGISTER
	
	public TimerRegister getTIMER() {
		return this.TIMER;
	}
	
}

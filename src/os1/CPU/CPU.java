package os1.CPU;
import os1.Registers.*;

public class CPU {
	
	private Register4B AX;
	private Register4B BX;
	private Register4B PTR;
	
	private Register2B IP;
	private Register2B SP;
	private TimerRegister TIMER;
	
	private LogicalRegister C;
	private LogicalRegister[] CHST;
	private LogicalRegister MODE;
	
	private InterruptRegister PI;
	private InterruptRegister SI;
	private InterruptRegister IOI;
	private InterruptRegister TI;
	
	public CPU() {
		this.AX = new Register4B();
		this.BX = new Register4B();
		this.PTR = new Register4B();
		
		this.IP = new Register2B();
		this.SP = new Register2B();
		
		this.TIMER = new TimerRegister(30);
		
		this.C = new LogicalRegister();
		this.CHST = new LogicalRegister[3];
		this.MODE = new LogicalRegister();
		
		this.PI = new InterruptRegister();
		this.SI = new InterruptRegister();
		this.IOI = new InterruptRegister();
		this.TI = new InterruptRegister();
		
		this.CHST[0] = new LogicalRegister();
		this.CHST[1] = new LogicalRegister();
		this.CHST[2] = new LogicalRegister();
		
	}
	
	@Override
	public String toString() {
		String info = 
				"AX = " + getAX() + "\n"
			  + "BX = " + getBX() + "\n"
			  + "PTR = " + getPTR() + "\n"
			  + "IP = " + getIP() + "\n"
			  + "SP = " + getSP() + "\n"
			  + "TIMER = " + getTIMER() + "\n"
			  + "C = " + getC() + "\n"
			  + "CHST[0] = " + getCHST((byte) 0) + "\n"
			  + "CHST[1] = " + getCHST((byte) 1) + "\n"
			  + "CHST[2] = " + getCHST((byte) 2) + "\n"
			  + "MODE = " + getMODE() + "\n"
			  + "PI = " + getPI() + "\n"
			  + "SI = " + getSI() + "\n"
			  + "IOI = " + getIOI() + "\n"
			  + "TI = " + getTI() + "\n";
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
	
	public int getTIMER() {
		return this.TIMER.getTimer();
	}
	
	public void setTIMER(int value) {
		this.TIMER.setTimer(value);
	}
	
// GETTER AND SETTER OF LOGICAL 2 BYTES REGISTERS
	
	public boolean getC() {
		return this.C.getValue();
	}
	
	public void setC(byte value) {
		this.C.setValue(value);
	}
	
	public boolean getCHST(byte index) {
		return this.CHST[index].getValue();
	}
	
	public void setCHST(byte index, byte value) {
		this.CHST[index].setValue(value);
	}
	
	public boolean getMODE() {
		return this.MODE.getValue();
	}
	
	public void setMODE(byte value) {
		this.MODE.setValue(value);
	}
	
// GETTER AND SETTER OF INTERRUPTS
	
	public byte getPI() {
		return this.PI.getValue();
	}
	
	public void setPI(byte value) {
		this.PI.setValue(value);
	}
	
	public byte getSI() {
		return this.SI.getValue();
	}
	
	public void setSI(byte value) {
		this.SI.setValue(value);
	}
	
	public byte getIOI() {
		return this.IOI.getValue();
	}
	
	public void setIOI(byte value) {
		this.IOI.setValue(value);
	}
	
	public byte getTI() {
		return this.TI.getValue();
	}
	
	public void setTI(byte value) {
		this.TI.setValue(value);
	}
	
}

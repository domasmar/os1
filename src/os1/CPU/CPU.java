package os1.CPU;
import os1.Registers.*;

public class CPU {
	
	private Register4B AX;
	private Register4B BX;
	private Register4B PTR;
	
	private Register2B IP;
	private Register2B SP;
	
	private Register2B DS;
	private Register2B CS;
	private Register2B SS;
	
	private TimerRegister TIMER;
	
	private StatusRegister C;
	
	private LogicalRegister CHST0;
	private LogicalRegister CHST1;
	private LogicalRegister CHST2;
	private LogicalRegister MODE;
	
	private InterruptRegister PI;
	private InterruptRegister SI;
	private InterruptRegister IOI;
	private InterruptRegister TI;
	private InterruptRegister STI;
	
	public CPU() {
		this.AX = new Register4B();
		this.BX = new Register4B();
		this.PTR = new Register4B();
		
		this.IP = new Register2B();
		this.SP = new Register2B();
		
		this.DS = new Register2B();
		this.CS = new Register2B();
		this.SS = new Register2B();
		
		this.TIMER = new TimerRegister(30);
		
		this.C = new StatusRegister();
		
		this.CHST0 = new LogicalRegister();
		this.CHST1 = new LogicalRegister();
		this.CHST2 = new LogicalRegister();
		this.MODE = new LogicalRegister();
		
		this.PI = new InterruptRegister();
		this.SI = new InterruptRegister();
		this.IOI = new InterruptRegister();
		this.TI = new InterruptRegister();
		this.STI = new InterruptRegister();
		
	}
	
	@Override
	public String toString() {
		String info = 
				"AX = " + getAX() + "\n"
			  + "BX = " + getBX() + "\n"
			  + "PTR = " + getPTR() + "\n"
			  + "IP = " + getIP() + "\n"
			  + "SP = " + getSP() + "\n"
			  + "DS = " + getDS() + "\n"
			  + "CS = " + getCS() + "\n"
			  + "SS = " + getSS() + "\n"
			  + "TIMER = " + getTIMER() + "\n"
			  + "C = " + getC() + "\n"
			  + "CHST[0] = " + getCHST0() + "\n"
			  + "CHST[1] = " + getCHST1() + "\n"
			  + "CHST[2] = " + getCHST2() + "\n"
			  + "MODE = " + getMODE() + "\n"
			  + "PI = " + getPI() + "\n"
			  + "SI = " + getSI() + "\n"
			  + "IOI = " + getIOI() + "\n"
			  + "TI = " + getTI() + "\n"
			  + "SO = " + getSTI();
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
	
	public short getDS() {
		return this.DS.getValue();
	}
	
	public void setDS(short value) {
		this.DS.setValue(value);
	}
	
	public short getCS() {
		return this.CS.getValue();
	}
	
	public void setCS(short value) {
		this.CS.setValue(value);
	}
	
	public short getSS() {
		return this.SS.getValue();
	}
	
	public void setSS(short value) {
		this.SS.setValue(value);
	}
	
// GETTER AND SETTER OF TIMER REGISTER
	
	public int getTIMER() {
		return this.TIMER.getTimer();
	}
	
	public void setTIMER(int value) {
		this.TIMER.setTimer(value);
	}
	
// GETTER AND SETTER OF LOGICAL 2 BYTES REGISTERS
	
	public byte getC() {
		return this.C.getValue();
	}
	
	public void setC(byte value) {
		this.C.setValue(value);
	}
	
	public boolean getCHST0() {
		return this.CHST0.getValue();
	}
	
	public boolean getCHST1() {
		return this.CHST1.getValue();
	}
	
	public boolean getCHST2() {
		return this.CHST2.getValue();
	}
	
	public void setCHST0(boolean value) {
		this.CHST0.setValue(value);
	}
	
	public void setCHST1(boolean value) {
		this.CHST1.setValue(value);
	}
	
	public void setCHST2(boolean value) {
		this.CHST2.setValue(value);
	}
	
	public boolean getMODE() {
		return this.MODE.getValue();
	}
	
	public void setMODE(boolean value) {
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
	
	public byte getSTI() {
		return this.STI.getValue();
	}
	
	public void setSTI(byte value) {
		this.STI.setValue(value);
	}
	
}

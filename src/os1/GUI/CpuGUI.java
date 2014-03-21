package os1.GUI;

import java.awt.GridLayout;

import javax.swing.JFrame;

import os1.CPU.CPU;

public class CpuGUI {

	private CPU cpu;

	private CPUInformationInterface[] cpuInformation = new CPUInformationInterface[] { 
			new CPUInformationInterface() {
				public String getName() { return "AX"; }
				public int getValue() { return cpu.getAX(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "BX"; }
				public int getValue() { return cpu.getBX(); }
				public void setValue(int value) { cpu.setAX(value); }
			},
			new CPUInformationInterface() {
				public String getName() { return "PTR"; }
				public int getValue() { return cpu.getPTR(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "IP"; }
				public int getValue() { return cpu.getIP(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "SP"; }
				public int getValue() { return cpu.getSP(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "DS"; }
				public int getValue() { return cpu.getDS(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "CS"; }
				public int getValue() { return cpu.getCS(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "SS"; }
				public int getValue() { return cpu.getSS(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "TIMER"; }
				public int getValue() { return cpu.getTIMER(); }
				public void setValue(int value) { cpu.setAX(value); }
			},
			new CPUInformationInterface() {
				public String getName() { return "C"; }
				public int getValue() { return cpu.getC(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "CHST1"; }
				public int getValue() { return cpu.getCHST((byte)1) ? 1 : 0; }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "CHST2"; }
				public int getValue() { return cpu.getCHST((byte)2) ? 1 : 0; }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "CHST3"; }
				public int getValue() { return cpu.getCHST((byte)3) ? 1 : 0; }
				public void setValue(int value) { cpu.setAX(value); }
			},
			new CPUInformationInterface() {
				public String getName() { return "MODE"; }
				public int getValue() { return cpu.getMODE() ? 1 : 0; }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "PI"; }
				public int getValue() { return cpu.getPI(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "SI"; }
				public int getValue() { return cpu.getSI(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "IOI"; }
				public int getValue() { return cpu.getIOI(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "TI"; }
				public int getValue() { return cpu.getTI(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "STI"; }
				public int getValue() { return cpu.getSTI(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			
	};

	public CpuGUI(CPU cpu) {
		this.cpu = cpu;
		JFrame frame = new JFrame("CPU registers");
		frame.setLayout(new GridLayout(5, 4));
		initAndAddToForm();
	}

	private void initAndAddToForm() {

	}

}

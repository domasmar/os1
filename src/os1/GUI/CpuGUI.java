package os1.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import os1.CPU.CPU;

public class CpuGUI {

	private CPU cpu;
	private JFrame frame;

	private CPUInformationInterface[] cpuInformation = new CPUInformationInterface[] { 
			new CPUInformationInterface() {
				public String getName() { return "AX"; }
				public int getValue() { return cpu.getAX(); }
				public void setValue(int value) { cpu.setAX(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "BX"; }
				public int getValue() { return cpu.getBX(); }
				public void setValue(int value) { cpu.setBX(value); }
			},
			new CPUInformationInterface() {
				public String getName() { return "PTR"; }
				public int getValue() { return cpu.getPTR(); }
				public void setValue(int value) { cpu.setPTR(value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "IP"; }
				public int getValue() { return cpu.getIP(); }
				public void setValue(int value) { cpu.setIP((short) value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "SP"; }
				public int getValue() { return cpu.getSP(); }
				public void setValue(int value) { cpu.setSP((short) value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "DS"; }
				public int getValue() { return cpu.getDS(); }
				public void setValue(int value) { cpu.setDS((short) value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "CS"; }
				public int getValue() { return cpu.getCS(); }
				public void setValue(int value) { cpu.setCS((short) value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "SS"; }
				public int getValue() { return cpu.getSS(); }
				public void setValue(int value) { cpu.setSS((short) value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "TIMER"; }
				public int getValue() { return cpu.getTIMER(); }
				public void setValue(int value) { cpu.setTIMER(value); }
			},
			new CPUInformationInterface() {
				public String getName() { return "C"; }
				public int getValue() { return cpu.getC(); }
				public void setValue(int value) { cpu.setC((byte) value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "CHST0"; }
				public int getValue() { return cpu.getCHST0() ? 1 : 0; }
				public void setValue(int value) { cpu.setCHST0(value > 0 ? true: false); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "CHST1"; }
				public int getValue() { return cpu.getCHST1() ? 1 : 0; }
				public void setValue(int value) { cpu.setCHST1(value > 0 ? true: false); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "CHST2"; }
				public int getValue() { return cpu.getCHST2() ? 1 : 0; }
				public void setValue(int value) { cpu.setCHST2(value > 0 ? true: false); }
			},
			new CPUInformationInterface() {
				public String getName() { return "MODE"; }
				public int getValue() { return cpu.getMODE() ? 1 : 0; }
				public void setValue(int value) { cpu.setMODE(value > 0 ? true : false); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "PI"; }
				public int getValue() { return cpu.getPI(); }
				public void setValue(int value) { cpu.setPI((byte) value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "SI"; }
				public int getValue() { return cpu.getSI(); }
				public void setValue(int value) { cpu.setSI((byte) value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "IOI"; }
				public int getValue() { return cpu.getIOI(); }
				public void setValue(int value) { cpu.setIOI((byte) value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "TI"; }
				public int getValue() { return cpu.getTI(); }
				public void setValue(int value) { cpu.setTI((byte) value); }
			}, 
			new CPUInformationInterface() {
				public String getName() { return "STI"; }
				public int getValue() { return cpu.getSTI(); }
				public void setValue(int value) { cpu.setSTI((byte) value); }
			}, 
			
	};

	public CpuGUI(CPU cpu) {
		this.cpu = cpu;
		frame = new JFrame("CPU registers");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridLayout(5, 4));
		initAndAddToForm();
		frame.pack();
		frame.setVisible(true);
	}

	private void initAndAddToForm() {
		for (int i = 0; i < cpuInformation.length; i++) {
			JPanel panel = new JPanel();
			panel.setSize(200, 20);
			
			JLabel label = new JLabel(cpuInformation[i].getName());
			label.setHorizontalAlignment(JLabel.LEFT);
			panel.add(label);
			
			JTextField textField = new JTextField(cpuInformation[i].getValue() + "", 5);
			panel.add(textField);
			frame.add(panel);
		}
		frame.add(new JButton("Update"));
	}

}

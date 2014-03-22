package os1.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import os1.Core;

public class MainGUI {

	private Core core;
	private JFrame frame;
	private JPanel memoryPanel;
	private JPanel leftPanel;
	private JPanel loggerPanel;
	
	private ActionListener runByBabySteps = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			core.executeNext();
		}		
	};
	
	private ActionListener runAll = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			core.executeAll();			
		}		
	};

	public MainGUI(Core core) {

		this.core = core;
		frame = new JFrame("VM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loggerPanel = new JPanel();
		loggerPanel.setLayout(new BoxLayout(loggerPanel, BoxLayout.PAGE_AXIS));

		memoryPanel = new JPanel();
		memoryPanel.setLayout(new GridLayout(1, 2));

		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(1, 1));

		frame.setLayout(new GridLayout(1, 2));
		frame.add(leftPanel);
		frame.add(memoryPanel);

		initLogger();
	}

	private void initLogger() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		
		JButton oneStopButton = new JButton("Run by step");
		oneStopButton.addActionListener(runByBabySteps);

		JButton allStepsButton = new JButton("Run");
		allStepsButton.addActionListener(runAll);
		
		buttonsPanel.add(oneStopButton);
		buttonsPanel.add(allStepsButton);

		JTextPane textArea = VMLogger.getTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);

		loggerPanel.add(scrollPane, BorderLayout.NORTH);
		loggerPanel.add(buttonsPanel);

		leftPanel.add(loggerPanel);
	}

	public void addMem(JPanel panel) {
		memoryPanel.add(panel);
	}

	public void addCPU(JPanel panel) {
		memoryPanel.add(panel);
	}

	public void setVisible(boolean a) {
		frame.pack();
		frame.setVisible(a);
	}

}

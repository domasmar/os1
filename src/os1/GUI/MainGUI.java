package os1.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import os1.Core;

public class MainGUI {

	private Core core;
	private JFrame frame;
	private JPanel memoryPanel;
	private JPanel leftPanel;
	private JPanel loggerPanel;
	private final JFileChooser fc = new JFileChooser();

	private ActionListener runByBabySteps = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (core.getVM() != null) {
				core.getVM().executeNext();
			} else {
				JOptionPane.showMessageDialog(null, "VM nerasta");
			}

		}
	};

	private ActionListener runAll = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (core.getVM() != null) {
				core.getVM().executeAll();
			} else {
				JOptionPane.showMessageDialog(null, "VM nerasta");
			}
		}
	};

	private ActionListener loadVM = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			fc.setCurrentDirectory(new File(
					"C:\\Users\\Domas\\workspace\\oop\\os1"));
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				file.getAbsolutePath();
				core.loadFromExternalFile(file.getAbsolutePath());
			} else {
				VMLogger.newErrorMessage("Nepasirinktas failas");
			}
		}
	};

	public MainGUI(Core core) {
		this.core = core;
		frame = new JFrame("OS1");
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

		JButton loadProgramButton = new JButton("Load program");
		loadProgramButton.addActionListener(loadVM);

		buttonsPanel.add(oneStopButton);
		buttonsPanel.add(allStepsButton);
		buttonsPanel.add(loadProgramButton);

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

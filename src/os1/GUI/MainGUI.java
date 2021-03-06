package os1.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import os1.Core;

public class MainGUI {

	private Core core;
	private JFrame frame;
	private JPanel memoryPanel;
	private JPanel leftPanel;
	private JPanel loggerPanel;
	private final JFileChooser fc = new JFileChooser();
	private DefaultTableModel tableModel;
	private JTable table;

	private ActionListener runByBabySteps = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (core.getVM() != null) {
				core.getVM().executeNext();
			} else {
				JOptionPane.showMessageDialog(null, "VM neužkrauta į atmintį!");
			}

		}
	};

	private ActionListener runAll = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (core.getVM() != null) {
				core.getVM().executeAll();
			} else {
				JOptionPane.showMessageDialog(null, "VM neužkrauta į atmintį!");
			}
		}
	};

	private ActionListener loadHDD = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			core.loadHDD();
		}
	};

	private ActionListener loadSelectedFile = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRowCount() == 1) {
				core.loadVM(table.getSelectedRow());
			} else {
				JOptionPane.showMessageDialog(null, "Pasirinkite programą, kurią pageidaujate užkrauti");
			}
		}
	};
	
	private ActionListener deleteSelectedFile = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRowCount() == 1) {
				core.deleteFileFromHDD(table.getSelectedRow());
				core.loadHDD();
			} else {
				JOptionPane.showMessageDialog(null, "Pasirinkite programą, kurią pageidaujate ištrinti");
			}
		}
	};

	private ActionListener loadVM = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			fc.setCurrentDirectory(new File(
					"C:\\Users\\Domas\\workspace\\oop\\os1"));
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = fc.showOpenDialog(frame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				file.getAbsolutePath();
				core.loadFromExternalFile(file.getAbsolutePath());
			} else {
				VMLogger.newErrorMessage("Nepasirinktas failas!");
			}
		}
	};

	public MainGUI(Core core) {
		this.core = core;
		frame = new JFrame("OS1");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loggerPanel = new JPanel();
		loggerPanel.setLayout(new BoxLayout(loggerPanel, BoxLayout.PAGE_AXIS));

		memoryPanel = new JPanel();
		memoryPanel.setLayout(new GridLayout(1, 2));

		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

		frame.setLayout(new GridLayout(1, 2));
		frame.add(leftPanel);
		frame.add(memoryPanel);

		initGUI();
	}

	private void initGUI() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());

		JButton oneStopButton = new JButton("Žingsninis režimas");
		oneStopButton.addActionListener(runByBabySteps);

		JButton allStepsButton = new JButton("Paleisti");
		allStepsButton.addActionListener(runAll);

		JButton loadHDDButton = new JButton("Užkrauti išorinę atmintį");
		loadHDDButton.addActionListener(loadHDD);

		JButton loadProgramButton = new JButton("Užkrauti \"Flash\" atmintinę");
		loadProgramButton.addActionListener(loadVM);

		buttonsPanel.add(oneStopButton);
		buttonsPanel.add(allStepsButton);
		buttonsPanel.add(loadHDDButton);
		buttonsPanel.add(loadProgramButton);

		JTextPane textArea = VMLogger.getTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(200, 200));

		loggerPanel.add(scrollPane, BorderLayout.NORTH);
		loggerPanel.add(buttonsPanel);

		leftPanel.add(loggerPanel);

		addHDDTable();
	}

	private void addHDDTable() {
		JPanel hddPanel = new JPanel();
		hddPanel.setLayout(new BoxLayout(hddPanel, BoxLayout.Y_AXIS));

		JPanel buttonPanel = new JPanel();

		JButton loadButton = new JButton("Užkrauti");
		loadButton.addActionListener(loadSelectedFile);
		buttonPanel.add(loadButton);

		JButton deleteButton = new JButton("Ištrinti");
		deleteButton.addActionListener(deleteSelectedFile);
		buttonPanel.add(deleteButton);

		
		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		tableModel.addColumn("Failo pavadinimas");

		JScrollPane listScroller = new JScrollPane(table);
		listScroller.setPreferredSize(new Dimension(300, 100));

		JPanel tablePanel = new JPanel();
		tablePanel.add(listScroller);

		hddPanel.add(tablePanel);
		hddPanel.add(buttonPanel);
		leftPanel.add(hddPanel);
	}

	public void loadHddData(String[] data) {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			tableModel.removeRow(i);	
		}
		for (int i = 0; i < data.length; i++) {
			Vector<String> v = new Vector<String>();
			v.add(data[i]);
			tableModel.addRow(v);
		}
	}

	public void addMem(JPanel panel) {
		memoryPanel.add(panel);
		frame.pack();
	}

	public void addCPU(JPanel panel) {
		memoryPanel.add(panel);
	}

	public void setVisible(boolean a) {
		frame.pack();
		frame.setVisible(a);
	}
	
	public void removePanel(JPanel panel) {
		memoryPanel.remove(panel);
		frame.pack();
		frame.setVisible(true);
	}

}

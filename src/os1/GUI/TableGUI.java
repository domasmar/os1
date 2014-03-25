package os1.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import os1.Memory.VMMemory;

public abstract class TableGUI {

	private DefaultTableModel tableModel;
	private JTable table;
	private JPanel mainPanel;
	private JPanel tablePanel;
	private String[] columnNames;

	private ActionListener saveList = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < getSize(); i++) {
				int newValue = 0;
				try {
					newValue = Integer.parseInt(tableModel.getValueAt(i, 1)
							.toString());
				} catch (Exception ex) {
					newValue = 0;
				} finally {
					setValue(i, newValue);
				}
			}
			update();
		}
	};

	protected abstract int getInfo(int i);

	protected abstract int getSize();

	protected abstract void setValue(int index, int value);

	protected abstract String getName(int index);

	public TableGUI(String name, int width) {

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		tablePanel = new JPanel();

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(80, 40));
		buttonPanel.setLayout(new GridLayout(1, 1));

		JButton saveButton = new JButton("Iðsaugoti");
		saveButton.addActionListener(saveList);
		buttonPanel.add(saveButton);

		tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1 ? true : false;
			}
		};
		table = new JTable(tableModel);
		JScrollPane listScroller = new JScrollPane(table);
		listScroller.setPreferredSize(new Dimension(width, 280));
		tablePanel.add(listScroller);

		mainPanel.add(new JLabel(name));
		mainPanel.add(tablePanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	public JPanel getPanel() {
		return mainPanel;
	}

	public void update() {
		for (int i = 0; i < getSize(); i++) {
			tableModel.setValueAt(getInfo(i), i, 1);
		}
	}

	public void setColumnNames(String[] names) {
		this.columnNames = names;
	}

	protected void initList() {
		for (int i = 0; i < columnNames.length; i++) {
			tableModel.addColumn(columnNames[i]);
		}
		table.getColumnModel().getColumn(0).setMaxWidth(60);

		for (int i = 0; i < getSize(); i++) {
			Vector<String> v = new Vector<String>();
			v.add(getName(i));
			v.add(String.valueOf(getInfo(i)));
			tableModel.insertRow(i, v);
		}
	}

}

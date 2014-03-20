package os1.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import os1.Memory.VMMemory;

public abstract class MemoryGUI implements ActionListener {

	private DefaultListModel<String> listModel;
	private JList list;
	private JPanel listPanel;
	private JFrame memoryFrame;
	
	
	public MemoryGUI(String name, int position) {
	
		memoryFrame = new JFrame(name);
		memoryFrame.setSize(new Dimension(175,400));
		memoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		memoryFrame.setLocation(position, 0);
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(150, 320));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setSize(new Dimension(100, 100));
		
		JButton button = new JButton("Update");
		button.setSize(100, 100);
		button.addActionListener(this);
		buttonPanel.add(button);
		memoryFrame.add(buttonPanel, BorderLayout.SOUTH);
		memoryFrame.add(listPanel, BorderLayout.NORTH);		
		listModel = new DefaultListModel<String>();
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(155, 300));		
		listPanel.add(listScroller);
	}
	
	protected void initList() {
		//listModel = new DefaultListModel<String>();
		for (int i = 0; i < getSize(); i++) {
			listModel.addElement(i + ": "+ getInfo(i));
		}		
	}
	
	protected void setVisible() {
		memoryFrame.pack();
		memoryFrame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		listModel.removeAllElements();
		for (int i = 0; i < getSize(); i++) {
			listModel.addElement(i + ": "+ getInfo(i));
		}	
	}

	protected abstract String getInfo(int i);
	protected abstract int getSize();
	
}

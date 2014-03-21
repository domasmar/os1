package os1.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import os1.Core;

public class MainGUI implements ActionListener {

	private final static String NEW_LINE = "\n";
	
	private Core core;	
	private JTextArea textArea;
	
	public MainGUI(Core core) {
		
		this.core = core;
		JFrame frame = new JFrame("Main program");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(0, 150);
		JButton button = new JButton("Next command");
		button.addActionListener(this);
		
		textArea = new JTextArea(20, 50);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(false);
		
		frame.add(scrollPane, BorderLayout.NORTH);		
		frame.add(button, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		core.executeNext();		
		textArea.append(core.getLastCommand() + NEW_LINE);
	}
	
}

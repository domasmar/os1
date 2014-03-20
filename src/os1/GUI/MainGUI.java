package os1.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import os1.Core;

public class MainGUI implements ActionListener {

	private Core core;
	
	public MainGUI(Core core) {
		
		this.core = core;
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(0, 150);
		JButton button = new JButton("Next command");
		button.addActionListener(this);
		
		frame.add(button);
		frame.pack();
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		core.executeNext();		
	}
	
}

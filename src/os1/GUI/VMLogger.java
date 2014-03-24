package os1.GUI;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class VMLogger {

	private final static String NEW_LINE = "\n";
	private static JTextPane textPane;

	public static JTextPane getTextArea() {
		if (textPane == null) {
			init();
		}
		return textPane;
	}
	
	public static void init() {
		if (textPane == null) {
			textPane = new JTextPane();  
			textPane.setBackground(Color.BLACK);
			textPane.setContentType("text/html");
			textPane.setEditable(false);
		}
	}

	private static void sendMessage(String message) {
		HTMLDocument doc = (HTMLDocument) textPane.getDocument();
		HTMLEditorKit editorKit = (HTMLEditorKit) textPane.getEditorKit();
		try {
			editorKit.insertHTML(doc, doc.getLength(), message, 0, 0, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void newMessage(String message) {
		sendMessage("<html><font color=\"white\" face=\"consolas\">" + message + "</font></html>" + NEW_LINE);
	}

	public static void newErrorMessage(String message) {
		sendMessage("<html><font face=\"consolas\" color=\"red\">" + message + "</font></html>");
	}

	public static void newSuccessMessage(String message) {
		sendMessage("<html><font face=\"consolas\" color=\"green\">" + message + "</font></html>");
	}

	public static void newVMMessage(String message) {
		sendMessage("<html><font face=\"consolas\" color=\"orange\">" + message + "</font></html>");
	}
}

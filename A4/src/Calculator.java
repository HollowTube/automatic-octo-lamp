import acm.gui.TableLayout;
import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends ConsoleProgram{
	public void init() {
		setLayout(new TableLayout(2,2));
		Calculator display = new Calculator();
		add(display, "gridwidth=4 height=5");
		add(new JButton("1"));
		add(new JButton("2"));
		add(new JButton("3"));
		add(new JButton("4"));
		addActionListeners();
	}
	private JTextField textField;
}

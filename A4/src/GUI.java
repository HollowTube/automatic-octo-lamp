
import acm.gui.TableLayout;
import acm.program.*;

import java.awt.TextField;
import java.awt.event.*;
import javax.swing.JButton;


public class GUI extends Program {
		JCalc myCalc = new JCalc();
		public void init() {

			String constraint = "width=" + BUTTONWIDTH + " height=" + BUTTONHEIGHT;
			setLayout(new TableLayout(6,4));
			add(field , "gridwidth=4 height="+DISPLAYHEIGHT);
			add(new JButton("C"), constraint);
			add(new JButton("Del"),constraint);
			add(new JButton("(-)"),constraint);
			add(new JButton("/"),constraint);
			add(new JButton("7"),constraint);	
			add(new JButton("8"),constraint);
			add(new JButton("9"),constraint);
			add(new JButton("*"),constraint);
			add(new JButton("4"),constraint);
			add(new JButton("5"),constraint);
			add(new JButton("6"),constraint);
			add(new JButton("-"),constraint);
			add(new JButton("1"),constraint);
			add(new JButton("2"),constraint);
			add(new JButton("3"),constraint);
			add(new JButton("+"),constraint);
			add(new JButton("0"),constraint);
			add(new JButton("."),constraint);
			add(new JButton("="),"gridwidth=2");

			addActionListeners();
		}
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			String output;
			switch(cmd) {
			case "C":input = ""; field.setText(input); break;
			case "Del":break;
			case "(-)":break;
			case "=":
				output = JCalc.evaluateExpression(input); 
				field.setText(output); 
				input = "";
				break;
			default: input+=cmd;
				field.setText(input); 
				break;
			}
		}
		private String input = "";
		TextField field = new TextField(20);
		private final int BUTTONWIDTH = 75;
		private final int BUTTONHEIGHT = 50;
		private final int DISPLAYHEIGHT = 60;
		
}

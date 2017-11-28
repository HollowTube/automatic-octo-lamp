//Tritin Truong Id:260806112
import acm.gui.TableLayout;
import acm.program.*;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class GUI extends Program {
		JCalc myCalc = new JCalc();
		JSlider mycontrol = new JSlider (0,10,0);
		public void init() {
			
			//size of the window
			setSize(BUTTONWIDTH*4+20,BUTTONHEIGHT*7 +20);

			String constraint = "width=" + BUTTONWIDTH + " height=" + BUTTONHEIGHT;
			JSlider mycontrol = new JSlider (0,15,6);
			
			setLayout(new TableLayout(8,4));
			add(field , "gridwidth=4 height="+DISPLAYHEIGHT);
			add(outField , "gridwidth=4 height="+DISPLAYHEIGHT);
			add(new JButton("C"),constraint);
			add(new JButton("("),constraint);
			add(new JButton(")"),constraint);
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
			add(new JButton("(-)"),constraint);
			add(new JButton("="), constraint);
			add(mycontrol, "gridwidth=2");
			add(new JButton("%"),constraint);
			add(new JButton("del"), constraint);
			mycontrol.addChangeListener(new ChangeListener() {
			      public void stateChanged(ChangeEvent event) {
			        precision = mycontrol.getValue();
			        formattedOutput = String.format("%." + precision + "f", Double.valueOf(output));
			        outField.setText(formattedOutput);
			      }
			    });
			addActionListeners();
		}
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			switch(cmd) {
			//Clears the input and the output
			case "C":
				input = ""; 
				output = "0";
				field.setText(input); 
				outField.setText(output);
				
				break;
			// only time program allows consecutive operators or starting with an operator
			case "(-)":
				input += "-";
				field.setText(input);
				break;
			//calls the jcalc object to perform calculations and evaluate the expression
			case "=":
				output = JCalc.evaluateExpression(input);
				formattedOutput = String.format("%." + precision + "f", Double.valueOf(output));
				outField.setText(formattedOutput); 
				input = "";
				
				break;
			//removes the  last token
			case "del":
				if (input.equals("")) break;
				input = input.substring(0, input.length()-1);
				field.setText(input);
				break;
			case "%":
				formattedOutput = String.format("%." + precision + "f", Double.valueOf(output)*100);;
				outField.setText(formattedOutput + " %");
				break;
			// by default will concatenate the symbol associated to the button to the input string
			// also prevents the user from putting 2 operators in a row or from starting with an operator
			default: 
				if(JCalc.isAnOperator(prevInput) && JCalc.isAnOperator(cmd)) {
					break;
				}
				else {
					input+=cmd;
					prevInput = cmd;
					field.setText(input); 
					break;
				}
			}
		}
		private String formattedOutput;
		private String output = "0";
		private int precision = 6;
		private String input = "";
		private String prevInput = "";
		TextField field = new TextField(20);
		TextField outField = new TextField(20);
		private final int BUTTONWIDTH = 75;
		private final int BUTTONHEIGHT = 50;
		private final int DISPLAYHEIGHT = 30;
		
}

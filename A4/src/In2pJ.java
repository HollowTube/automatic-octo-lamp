//Tritin Truong,  Id: 260806112
import java.util.*;


public class In2pJ {
	
	//simple print function to make printing less verbose
	public static void print(String num) {
		System.out.println(num);
	}
	//checks if token is a valid decimal number
	private static boolean isANumber(String token) {
		for (char c : token.toCharArray())
	    {
	        if (!Character.isDigit(c) && !(c == '.')) return false;
	    }
	    return true;
	}
	//Checks if token is an operator
	public static boolean isAnOperator(String token) {
		if( token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) return true;
		else return false;
	}
	//checks if the token is a unary operator using previous tokens
	private static boolean isUnary(String token, String prev) {
		if (!token.equals("-")) return false;
		if (prev.equals("") || isAnOperator(prev) || prev.equals("("))return true;
		else return false;
	}
	//returns the precedence of the current token
	private static int getOpValue(String token) {
		switch(token) {
		case "(": return 1;
		case "+": return 2;
		case "-": return 2;
		case "*": return 3;
		case "/": return 3;	
		default: return 4;
		}
	}
	
	//Takes user input and places it inside input queue and returns the queue
	private static Queue inputBuffer(String in){
		Queue input = new Queue();
		StringTokenizer st = new StringTokenizer(in,"+-*/()",true);
		while (st.hasMoreTokens()){
	    	input.enQueueToken(st.nextToken());
		}
		return input;
	}
	
	// "main" function of class
	public static Queue in_post(String in){
		
		Queue input = inputBuffer(in);
		Queue output = new Queue();
		Stack operation = new Stack();
		String token;
		String prevToken = "";
		int counter = 0;
		
//	    print("Starting input Queue");
//	    input.printQueue();
//	    System.out.println("");
	    	
	    //Going through input queue, Shunting yard algorithm
	    while (!input.isEmpty()) 
	    {
	    	token = input.deQueueToken();
	    	//puts token in output if its a number
	    	if(isANumber(token)) {
	    		output.enQueueToken(token);
	    	}
	    	//pushed unary operator onto stack
	    	//added new symbol # to distinguish between unary and binary minus
	    	else if (isUnary(token, prevToken)) {
	    		operation.push("#");
	    	}
	    	//checks if its a token
	    	else if(isAnOperator(token))
	    	{
	    		//will keep on popping out tokens as long as the current token is smaller or equal then the token 
	    		//on top of the stack, or if until the stack is empty
		    	while(!operation.isEmpty() && getOpValue(token) <= getOpValue(operation.getTop()) ) 
		    	{
		    		output.enQueueToken(operation.popToken());
		   		}
		    	//finally push the token
		   		operation.push(token);
	    	}
	    	//push all left parentheses
	    	else if(token.equals("(")) {
	    		operation.push(token);
	    	}
	    	//pop off operators until left parentheses found
	    	else if (token.equals(")")) 
	    	{
	    		while (!operation.getTop().equals("("))
	    		{
	    			output.enQueueToken(operation.popToken());
	    		}
	    		//make sure to discard the left parentheses
	    		operation.popToken();
	    	}
	    	
	    	print("Current Token:" + token);
	    	print("Current operation Stack: ");
	 	    operation.printStack();
	 	    print("Current output Queue: ");
	 	    output.printQueue();
	 	    System.out.println("");
		    System.out.println("");
		    prevToken = token;
	    }
	    //when the input is exhausted, pop the operator stack into the output queue
	    while(!operation.isEmpty())
	    {
	    	output.enQueueToken(operation.popToken());
	    }
//	    System.out.println("Final output Queue");
//	    output.printQueue();
//	    
	    return output;   
	}
	public static String evaluatePF(Queue in) {
		Stack buffer = new Stack();
		String token;
		Double op1;
		Double op2;
		while (!in.isEmpty()) {
			
			//if its a unary minus (represented by #), pop top of stack, multiply by -1 and push it back to
			//stack
			token = in.deQueueToken();
			if(token.equals("#")) {
				op1 = Double.valueOf(buffer.popToken()) * -1;
				buffer.push(Double.toString(op1));
			}
			//if an operator, pop first 2 operands and apply operation, push back to stack
			else if(isAnOperator(token)) {
				op1 = Double.valueOf(buffer.popToken());
				op2 = Double.valueOf(buffer.popToken());
				switch (token) {
				case "+": buffer.push(Double.toString(op2+op1)); break;
				case "-": buffer.push(Double.toString(op2-op1)); break;
				case "*": buffer.push(Double.toString(op2*op1)); break;
				case "/": buffer.push(Double.toString(op2/op1)); break;
				}
			}
			else buffer.push(token);
		}
		return buffer.popToken();
	}
	
	public static void main(String[] args) {
		String str;
		String result;
		Queue postfix;
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("Enter string: ");
			str = scan.nextLine();
			postfix = in_post(str);
			result = postfix.queue2String();
			System.out.println("Postfix: " + result);
			result = evaluatePF(postfix);
			System.out.println(result);
		}
	}
		
}


import java.util.*;

public class In2pJ {
	
	//simple print function to make printing less verbose
	public static void print(String num) {
		System.out.println(num);
	}
	public enum operator{
		PLUS("+",0), MINUS("-",1), MULTIPLY("-",2), DIVIDE("/",3), LEFT("-",4), RIGHT("-",5);
		private String type;
		private int precedence;
		private operator(String type, int precedence) {
			this.precedence = precedence;
			this.type = type;
		}
	}
	static operator op;
	//assigns a integer value depending on the tokens
	
	//Checks if token is an operator
	public static boolean isAnOperator(String token) {
		if( token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) return true;
		else return false;
	}
	public static boolean isOp(String token) {
		switch(token) {
		case "+": op = operator.PLUS;return true;
		case "-": op = operator.MINUS;return true;
		case "*": op = operator.MULTIPLY;return true;
		case "/": op = operator.DIVIDE;return true;
		case "(": op = operator.LEFT;return true;
		case ")": op = operator.RIGHT;return true;	
		default: return false;
		}
	}
	public static int getOpValue(String token) {
		switch(token) {
		case "+": op = operator.PLUS;return 1;
		case "-": op = operator.MINUS;return 2;
		case "*": op = operator.MULTIPLY;return 3;
		case "/": op = operator.DIVIDE;return 4;
		case "(": op = operator.LEFT;return -1;
		case ")": op = operator.RIGHT;return -1;	
		default: return 0;
		}
	}
	public static boolean isABracket(String token) {
		if(token.equals("(")||token.equals(")")) return true;
		else return false;
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
		
	    print("Starting input Queue");
	    input.printQueue();
	    System.out.println("");
	    	
	    //Going through input queue, Shunting yard algorithm
	    while (!input.isEmpty()) 
	    {
	    	token = input.deQueueToken();
	    	if(isAnOperator(token))
	    	{
		    	while(!operation.isEmpty() && getOpValue(token) <= getOpValue(operation.getTop()) ) 
		    	{
		    		output.enQueueToken(operation.popToken());
		   		}
		   		operation.push(token);
	    	}
	    	else if(token.equals("(")) {
	    		operation.push(token);
	    	}
	    	else if (token.equals(")")) 
	    	{
	    		while (!operation.getTop().equals("("))
	    		{
	    			output.enQueueToken(operation.popToken());
	    		}
	    		operation.popToken();
	    	}
	    	else 
	    	{
	    		output.enQueueToken(token);
	    	}
	    	
	    	print("Current operation Stack: ");
	 	    operation.printStack();
	 	    print("Current output Queue: ");
	 	    output.printQueue();
	 	    System.out.println("");
		    System.out.println("");
	    }
	   
	    while(!operation.isEmpty())
	    {
	    	output.enQueueToken(operation.popToken());
	    }
	    System.out.println("Final output Queue");
	    output.printQueue();
	    
	    return output;
	    
	}
	public static String evaluatePF(Queue in) {
		Stack buffer = new Stack();
		String token;
		float op1;
		float op2;
		while (!in.isEmpty()) {
			token = in.deQueueToken();
			if(isAnOperator(token)) {
				op1 = Float.valueOf(buffer.popToken());
				op2 = Float.valueOf(buffer.popToken());
				switch (token) {
				case "+": buffer.push(Float.toString(op1+op2)); break;
				case "-": buffer.push(Float.toString(op1-op2)); break;
				case "*": buffer.push(Float.toString(op1*op2)); break;
				case "/": buffer.push(Float.toString(op2/op1)); break;
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
			System.out.println("Enter string");
			str = scan.nextLine();
			postfix = in_post(str);
			result = evaluatePF(postfix);
			System.out.println(result);
		}
	}
		
}


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
	private static boolean isAnOperator(String token) {
		if( token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) return true;
		else return false;
	}

	private boolean isUnary(int count, String token, String prev) {
		if (!token.equals("-")) return false;
		if (count  == 0 || isAnOperator(prev) || prev.equals("("))return true;
		else return false;
	}
	//returns the precedence of the current token
	private int getOpValue(String token) {
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
	public Queue in_post(String in){
		
		Queue input = inputBuffer(in);
		Queue output = new Queue();
		Stack operation = new Stack();
		int count = 0;
		String token;
		String prevToken = "";
		
	    print("Starting input Queue");
	    input.printQueue();
	    System.out.println("");
	    	
	    //Going through input queue, Shunting yard algorithm
	    while (!input.isEmpty()) 
	    {
	    	token = input.deQueueToken();
	    	//puts token in output if its a number
	    	if(isANumber(token)) {
	    		output.enQueueToken(token);
	    	}
	    	//added new symbol # to distinguish between unary and binary minus
	    	else if (isUnary(count, token, prevToken)) {
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
	    		operation.popToken();
	    	}
	    	
	    	print("Current operation Stack: ");
	 	    operation.printStack();
	 	    print("Current output Queue: ");
	 	    output.printQueue();
	 	    System.out.println("");
		    System.out.println("");
		    count ++;
		    prevToken = token;
	    }
	    //when the input is exhausted, pop the operator stack into the output queue
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
			
			//if its a unary minus (represented by #), pop top of stack, multiply by -1 and push it back to
			//stack
			token = in.deQueueToken();
			if(token.equals("#")) {
				op1 = Float.valueOf(buffer.popToken()) * -1;
				buffer.push(Float.toString(op1));
			}
			
			else if(isAnOperator(token)) {
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
	public void main(String[] args) {
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


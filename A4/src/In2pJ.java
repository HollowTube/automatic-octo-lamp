import java.util.*;

public class In2pJ {
	
	//simple print function to make printing less verbose
	public static void print(String num) {
		System.out.println(num);
	}
	
	//assigns a integer value depending on the tokens
	private static int CheckPrecedence(String token, String that) {
		if(token.equals(that)) return 0;
		else if((token.equals("*") || token.equals("/")) && (that.equals("+") || that.equals("-")))
		return -1;
		else return 1;
	}
	//Checks if token is an operator
	public static boolean isAnOperator(String token) {
		if( token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) return true;
		else return false;
	}
	public static boolean isABracket(String token) {
		if(token.equals("(")||token.equals(")")) return true;
		else return false;
	}
	
	//Takes user input and places it inside input queue and returns the queue
	private static Queue inputBuffer(String in){
		Queue input = new Queue();
		StringTokenizer st = new StringTokenizer(in,"+-*/",true);
		while (st.hasMoreTokens()) {
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
	    while (input.front != null) 
	    {
	    	token = input.deQueueToken();
	    	if(isAnOperator(token))
	    	{
		    		while(CheckPrecedence(token, operation.getTop()) != -1 && !operation.isEmpty()) 
		    		{
		    			output.enQueueToken(operation.popToken());
		    		}
		    		operation.push(token);
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
		str = scan.nextLine();
		postfix = in_post(str);
		result = evaluatePF(postfix);
		System.out.println(result);
	}
		
}


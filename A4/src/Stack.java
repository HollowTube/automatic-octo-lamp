public class Stack {
	Node top = null;
	public void print(int num) {
		System.out.print(num);
	}
	public void push(String data) {
		Node node = new Node();
		node.token = data;
		node.next = top;
		top = node;
	}
	public String popToken() {
		String data;
		if(top != null) {
			data= top.token;
			top = top.next;
			return data;
		}
		else {
			System.out.println("ERROR STACK EMPTY");
			return "0";
		}
	}
	public Boolean isEmpty(){
		if (top == null) return true;
		else return false;
	}
	public void printStack() {
		Node buffer = top;
//		System.out.println("Stack is:");
		while(buffer!=null) {
			System.out.print(buffer.token + " ");
			buffer = buffer.next;
		}
		System.out.println("");
	}
	public String getTop() {
		if(top == null) {
			return "empty";
		}
		else
		return top.token;
	}
}

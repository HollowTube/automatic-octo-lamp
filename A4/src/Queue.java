
public class Queue {
	Node front = null;
	Node rear = null;
	
	public void print(int num) {
		System.out.println(num);
	}
	public void enQueueToken(String token) {
		Node node = new Node();
		node.token = token;
		node.next = null;
		if (rear == null) {
			front = node;
			rear = node;
		}
		else {
			rear.next = node;
		}
		rear = node;
	}
	public String deQueueToken(){
		String data = "";
		if(front != null) {
			data = front.token;
			front = front.next;
			if(front == rear) {
				rear = null;
			}
			return data;
		}
		System.out.println("ERROR: QUEUE EMPTY");
		return null;
	}
	public void printQueue() {
		Node buffer = front;
//		System.out.println("current queue is: ");
		while (buffer !=null) {
			System.out.print(buffer.token + " ");
			buffer = buffer.next;
		}
		System.out.println("");
	}
	public String queue2String() {
		Node buffer = front;
		String output ="";
//		System.out.println("current queue is: ");
		while (buffer !=null) {
			output += buffer.token + " ";
			buffer = buffer.next;
		}
		return output;
	}
	public boolean isEmpty() {
		if(front == null) return true;
		else return false;
	}
}

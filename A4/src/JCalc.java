//Tritin Truong,  Id: 260806112
import java.util.Scanner;
public class JCalc extends In2pJ{
	//works with both unary operators and parentheses
	public static void main(String[] args) {
		String str;
		String result;
		Queue postfix;
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print("Enter expression: ");
			str = scan.nextLine();
			postfix = in_post(str);
			result = postfix.queue2String();
			result = evaluatePF(postfix);
			System.out.println(str +"=" + result);
		}
	}
}

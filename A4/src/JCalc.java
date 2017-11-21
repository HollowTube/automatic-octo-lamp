//Tritin Truong,  Id: 260806112
import java.util.Scanner;
public class JCalc extends In2pJ{
	public static String evaluateExpression(String in) {
		String result;
		Queue postfix;
		postfix = in_post(in);
		result = postfix.queue2String();
		result = evaluatePF(postfix);
		return result;
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String result;
		String str;
		while (true) {
			System.out.print("Enter expression: ");
			str = scan.nextLine();
			result = evaluateExpression(str);
			System.out.println(str +"=" + result);
		}
	}
}

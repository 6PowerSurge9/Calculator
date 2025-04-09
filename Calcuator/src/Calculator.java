import java.util.Scanner;
import java.util.Stack;




//Code belongs to David Zhang
public class Calculator {
	public static void main(String args[]) {
		Scanner scnr = new Scanner(System.in);
		String x;
		System.out.println("Please enter a math eqation: ");
		x = scnr.nextLine();
		System.out.println(converter(x));
		
		
	}

	private static void calulator(String stringBuilder) {
		Stack<String> value = new Stack <String>();


		String input = stringBuilder;

		Scanner read = new Scanner(System.in);
		String  [] parts = input.split(" ");

		for (int i = 0; i<parts.length; i++){
			char [] parts1 = parts[i].toCharArray();
			if (Character.isDigit(parts1[0])) {
				value.push(parts[i]);
			}
			else{
			Double num1 = Double.parseDouble(value.pop());
	        Double num2 = Double.parseDouble(value.pop());
	        Double result;
			switch (parts1[0]) {
				case '*':
					result = num2*num1;
					value.push(Double.toString(result));
					break;
			
				case '/':
					result = num2/num1;
					value.push(Double.toString(result));
					break;
				
			
				case '+':
					result = num2 + num1;
					value.push(Double.toString(result));
					break;
					
				case '-':
					result = num2 - num1;
					value.push(Double.toString(result));
					break;
				
				}
			}	
		}
		System.out.println(value.pop());
		
	}
	
//Code belongs to Sergio Hernandez
	private static String converter( String x) {
		x = x + " ";
		Stack<Character> charstack = new Stack<>();
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0; i < x.length() - 1; i++) {
			
			if (Character.isDigit(x.charAt(i)) || x.charAt(i) == '.' ) {     
				int intnum = i;// first implement ignore spaces
				while(Character.isDigit(x.charAt(i)) || x.charAt(i) == '.') {
					i++;
				}
				
				stringBuilder.append(x.substring(intnum, i)).append(" ");
				i--;
			}
			else {
				
				if(x.charAt(i) == '(') {
					charstack.push(x.charAt(i));
				}
				else if (x.charAt(i) == '*' || x.charAt(i) ==  '/' || x.charAt(i) ==  '+' || x.charAt(i) == '-') {
					while(!charstack.isEmpty() && charstack.peek() != '(' && hasPrecedence(charstack.peek(), x.charAt(i))) {
						
						stringBuilder.append(charstack.pop().toString()).append(' ');//write it to operator
					}
					charstack.push(x.charAt(i));

				}
				else if(x.charAt(i) == ')') {
					while(!charstack.isEmpty() && charstack.peek() != '(') {
						stringBuilder.append(charstack.pop().toString()).append(' ');//write it to the operator
					}
					charstack.pop();
				}
			}
			
		}
		while(!charstack.isEmpty()) {
			stringBuilder.append(charstack.pop().toString()).append(' ');
		}
		calulator(stringBuilder.toString());
		return stringBuilder.toString();
	}
	
	public static boolean hasPrecedence(char c1, char c2){
		if(c1 == '*' || c1 == '/') {
			return true;
		}
		
		if((c1 == '+' || c1 == '-') && (c2 == '+' || c2 == '-')) {
			return true;
		}
		
		return false;
		
	}
}

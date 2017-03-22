/* Stefany Carrillo
 * Vladik Kreinovich
 * CS3350 Automata
 * This program is meant to write a program that, given an arithmetic expression,
 *    - first transforms it to a postfix form, and then
 *    - computes its value (by using the stack-based algorithms from class)
 * It can be assumed that all the numbers are one-digit ones and parenthesis
 * don't have to be taken into consideration.
 */

import java.util.*;

public class PostfixandSolution {
	static String output = "";
	
	public static void main(String[] args){
		readInput(); 
	}//end main method
	
	public static void readInput(){
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		//user inputs arithmetic expression
		System.out.println("Input arithmetic expression: ");
		String expression = input.next();
		String postfixExp = postfix(expression);
		
		System.out.printf("\nPostfix: %s", postfixExp);
		
		double solution = solvePostfix(postfixExp);
		if(solution != -9178276.7)
			System.out.println("\nSolved postfix: " + solvePostfix(postfixExp));
	}//end readInput
	
	public static double solvePostfix(String post){
		double result = 0;
		double num1 = 0;
		double num2 = 0;
		Stack<Double> solving = new Stack<Double>();
		
		for(int i = 0; i < post.length(); i++){
			char ch = post.charAt(i);
			
			if(ch == ' '){
			} else{
				if(ch > '0' && ch < '9'){
					solving.push((double)Character.getNumericValue(ch));}
				else{
					//check if expression is valid to solve, if there is not 
					//2 numbers in the stack, then no operation can take place
					if(solving.isEmpty()){
						System.out.println("\nInvalid expression,"
								   + " can't solve.");
						return -9178276.7;
					}
					else
						num1 = solving.pop();
					if(solving.isEmpty()){
						System.out.println("\nInvalid expression,"
								   + " can't solve.");
						return -9178276.7;
					}
					else
						num2 = solving.pop();
					
					//if passed, it will go through the operations to solve
					//until stack is empty and the end value can be passed
					switch(ch){
					case '+':
						solving.push((num1 + num2));
						break;
					case '-':
						solving.push((num1 - num2));
						break;
					case '*':
						solving.push((num1 * num2));
						break;
					case '/':
						solving.push((num1 / num2));
						break;
					default:
						System.out.println("Not a valid expression.");
					}//end switch
				}//end else
			}//end if character is not empty
		}//end for
		result = Double.parseDouble("" + solving.pop());
		return result;
	}//end solvePostfix
	
	public static String postfix(String exp){
		Stack<Character> expStack = new Stack<Character>();
		
		for(int i = 0; i < exp.length(); i++){
			char ch = exp.charAt(i);
			
			if(ch == '+' || ch == '-'){
				if(expStack.isEmpty())
					expStack.push(ch);
				else{
					char op = (char)expStack.peek();
					while(!(expStack.isEmpty() || op == ')' || op == '(')){
						expStack.pop();
						output += op;
						if(!expStack.isEmpty()) 
							op = (char) expStack.peek();
					}//end while
					expStack.push(ch);
				}//end else
			}//end else if plus/minus
			
			else if(ch == '*' || ch == '/'){
				if(expStack.isEmpty())
					expStack.push(ch);
				else{
					char op = (char) expStack.peek();
					while(!(expStack.isEmpty() || op == '+' || op == '-')){
						expStack.pop();
						output += op;
						if(!expStack.isEmpty()) 
							op = (char) expStack.peek();
					}//end while loop
					expStack.push(ch);
				}//end else
			}//end else if times/by
			
			else{
				output += ch;
			}//end else if
		}//end for loop
		
		while(!expStack.isEmpty()){
			char op = (char) expStack.peek();
			if(!(op == '(')){
				output += op;
				expStack.pop();
			}//end if
		}//end while loop
		
		return output;
	}//end postfix2
	
	
}//end main class

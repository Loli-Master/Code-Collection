package kalkulator;

public class Calculator {

	private String token;

	// check if character token is a number
	private boolean isNumber(char c) {
		if ((c > 47 && c < 58) || c == 46) {
			return true;
		} else {
			return false;
		}
	}

	// check if string is double
	private boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// delete all whitespace and change to dot decimal separator
	private String process(String a) {
		a = a.replaceAll(" ", "");
		a = a.replaceAll(",", ".");
		return a;
	}

	// just a debug method function
	public void hello() {
		System.out.println("HELLO");
	}

	public Calculator() {
		token = null;
	}

	public void set(String token) {
		this.token = process(token);
	}

	public Calculator(String token) {
		this.token = process(token);
	}

	// convert infix input to postfix with comma separator
	// shunting-yard algorithm
	// https://en.wikipedia.org/wiki/Shunting-yard_algorithm
	private ListChar postfix(String input) {
		ListChar ans = new ListChar();// queue of tokens
		ListChar ops = new ListChar();// stack of waiting operators
		int index = 0;
		while (index < input.length()) {
			if (isNumber(input.charAt(index))) {
				while (index < input.length() && isNumber(input.charAt(index))) {
					ans.insertLast(input.charAt(index));
					index++;
				}
				ans.insertLast(' ');
			} else {
				if (input.charAt(index) == '+' || input.charAt(index) == '-') {
					while (!ops.isEmpty()) {
						if (ops.getFirst().data == '+' || ops.getFirst().data == '-' || ops.getFirst().data == '*'
								|| ops.getFirst().data == '/') {
							ans.insertLast(ops.deleteFirst().data);
							ans.insertLast(' ');
						} else {
							break;
						}
					}
					ops.insertFirst(input.charAt(index));
				} else if (input.charAt(index) == '*' || input.charAt(index) == '/') {
					while (!ops.isEmpty()) {
						if (ops.getFirst().data == '*' || ops.getFirst().data == '/') {
							ans.insertLast(ops.deleteFirst().data);
							ans.insertLast(' ');
						} else {
							break;
						}
					}
					ops.insertFirst(input.charAt(index));
				} else if (input.charAt(index) == '(') {
					ops.insertFirst(input.charAt(index));
				} else if (input.charAt(index) == ')') {
					while (!ops.isEmpty()) {
						if (ops.getFirst().data != '(') {
							ans.insertLast(ops.deleteFirst().data);
							ans.insertLast(' ');
						} else {
							break;
						}
					}
					if (ops.getFirst().data == '(') {
						ops.deleteFirst();
					} else {
						System.err.println("ERROR: BRACKET MISSING");
					}
				}
				index++;
			}
		}
		while (!ops.isEmpty()) {
			if (ops.getFirst().data != '(') {
				ans.insertLast(ops.deleteFirst().data);
				ans.insertLast(' ');
			} else {
				System.err.println("ERROR: BRACKET MISSING");
				break;
			}
		}
		return ans;
	}

	// calculate using postfix
	// https://en.wikipedia.org/wiki/Reverse_Polish_notation
	public double calculate() {
		double ans = 0d;
		ListDouble proc = new ListDouble();// stack of doubles
		ListChar input = postfix(token);// queue of tokens
		String[] post = input.toString().split(" ");
		// System.out.print(input);

		for (String ins : post) {
			if (isDouble(ins)) {
				proc.insertFirst(Double.parseDouble(ins));
			} else {
				if (ins.equalsIgnoreCase("+")) {
					double temp2;
					double temp1;
					double tempAns;
					if (proc.size() > 1) {
						temp2 = proc.deleteFirst().data;
						temp1 = proc.deleteFirst().data;
						tempAns = temp1 + temp2;
						proc.insertFirst(tempAns);
					} else {
						System.err.println("NUMBER MISSING");
					}
				} else if (ins.equalsIgnoreCase("-")) {
					double temp2;
					double temp1;
					double tempAns;
					if (proc.size() > 1) {
						temp2 = proc.deleteFirst().data;
						temp1 = proc.deleteFirst().data;
						tempAns = temp1 - temp2;
						proc.insertFirst(tempAns);
					} else {
						System.err.println("NUMBER MISSING");
					}
				} else if (ins.equalsIgnoreCase("*")) {
					double temp2;
					double temp1;
					double tempAns;
					if (proc.size() > 1) {
						temp2 = proc.deleteFirst().data;
						temp1 = proc.deleteFirst().data;
						tempAns = temp1 * temp2;
						proc.insertFirst(tempAns);
					} else {
						System.err.println("NUMBER MISSING");
					}
				} else if (ins.equalsIgnoreCase("/")) {
					double temp2;
					double temp1;
					double tempAns;
					if (proc.size() > 1) {
						temp2 = proc.deleteFirst().data;
						temp1 = proc.deleteFirst().data;
						tempAns = temp1 / temp2;
						proc.insertFirst(tempAns);
					} else {
						System.err.println("NUMBER MISSING");
					}
				}
			}
		}
		if (proc.size() == 1) {
			ans = proc.deleteFirst().data;
		}
		return ans;
	}
}

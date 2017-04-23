package rbt;

public class Contoh {

	public static void main(String[] args) {
		RBT test = new RBT();
		test.insert(20);
		test.insert(10);
		test.insert(30);
		test.insert(25);
		test.insert(35);
		test.insert(21);
		test.insert(40);
		test.delete(21);
		test.delete(40);
		test.delete(10);
		System.out.printf("prefix: %s\n", TreePrinter.prefix(test));
		System.out.printf("infix: %s\n", TreePrinter.infix(test));
		System.out.printf("postfix: %s\n", TreePrinter.postfix(test));
	}

}

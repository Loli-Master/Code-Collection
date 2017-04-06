package kalkulator;

import java.util.Locale;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Calculator calculator = new Calculator();
		Scanner scan = new Scanner(System.in);
		calculator.hello();
		calculator.set(scan.nextLine());
		double ans = calculator.calculate();
		System.out.printf("Answer: %.2f",ans);
		scan.close();
	}
}

package package1;

import java.util.Scanner;

public class InputScanner {
	double num1;
	double num2;
	double ans;
	char op;


	public void scaninput() {
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the first numbers: ");
		num1 = reader.nextDouble();
		System.out.println("\nEnter an operator (+, -, *, /): ");
		op = reader.next().charAt(0);
		System.out.println("Enter the second numbers: ");
		num2 = reader.nextDouble();
		reader.close();

	}
	
	
}

package package1;

public class Operations extends InputScanner {

	public void PerformOP() {
		double num1 = this.num1;
		double num2 = this.num2;
		double result;
		char op = this.op;
		switch (op) {
		case '+':
			result = Additions(num1, num2);
			System.out.println("The reult of operation " + num1 + " + " + num2 + " = " + result);
			break;

		case '-':
			result = Subtraction(num1, num2);
			System.out.println("The reult of operation " + num1 + " - " + num2 + " = " + result);
			break;
		case '*':
			result = Multiply(num1, num2);
			System.out.println("The reult of operation " + num1 + " * " + num2 + " = " + result);
			break;

		case '/':
			result = Divide(num1, num2);
			System.out.println("The reult of operation " + num1 + " / " + num2 + " = " + result);
			break;
			
	
		default:
			System.out.println("The operation selected is not valied");
			break;
		}

	}

	private double Additions(double a, double b) {
		double sum = a + b;
		return sum;
	}

	private double Subtraction(double a, double b) {
		double diff = a - b;
		return diff;
	}

	private double Multiply(double a, double b) {
		double product = a * b;
		return product;
	}

	private double Divide(double a, double b) {
		double quotient = a / b;
		return quotient;
	}
}

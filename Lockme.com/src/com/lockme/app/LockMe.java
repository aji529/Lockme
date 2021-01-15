package com.lockme.app;

import java.util.Scanner;

public class LockMe {

	public static void main(String[] args) {
		System.out.println("\t" + "\t" + "\t" + "\t" + "************LOCKME.COM************" + "\t");
		System.out.println("\t" + "\t" + "\t" + "\t" + "----------------------------------" + "\t");
		System.out.println("Please Enter the root directory");
		@SuppressWarnings("resource")
		Scanner sc1 = new Scanner(System.in);
		String rootpath= sc1.nextLine();
		OperationsCall.testroot(rootpath);
		while (true) {
			int opt = OperationsCall.mainOptions();
			OperationsCall.operations(opt);
		}
	}

}

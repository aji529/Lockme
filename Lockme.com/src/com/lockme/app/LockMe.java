package com.lockme.app;

public class LockMe {

	public static void main(String[] args) {
		System.out.println("\t" + "\t" + "\t" + "\t" + "************LOCKME.COM************" + "\t");
		System.out.println("\t" + "\t" + "\t" + "\t" + "----------------------------------" + "\t");
		
		OperationsCall op= new OperationsCall();
		op.getroot();
	
		while (true) {
			op.parentOptions();
		}
		
	}

}

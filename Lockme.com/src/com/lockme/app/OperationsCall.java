package com.lockme.app;

import java.io.File;
import java.util.Scanner;

import com.lockme.filehandler.FileHandler;

public class OperationsCall {
	static String root;
	static Scanner input;
	static Scanner name;
	public static void testroot(String rootpath) {
		File file;
		while(true) {
		file=new File(rootpath);
		System.out.println(file.getAbsolutePath());
		
			if (file.isDirectory()) {
				System.out.println("Root Path Accepted");
				root = rootpath;
				break;
			
			} else {
				
				System.out.println("Invalid directory path, please enter the correct rootpath");
				rootpath = readFile();
				System.out.println(rootpath);
			}
		}
	}

	public static int mainOptions() {
		System.out.println("Select from the below list of operations: ");
		System.out.println("1.Retrieve the file names in an ascending order");
		System.out.println("2.Business-level operations:");
		System.out.println("3.CLose the aplication");
		return readInput();
	}

	public static int readInput() {
		input = new Scanner(System.in);
		int choice;
		if (input.hasNext()) {
			choice = input.nextInt();
		} else {
			choice = 1;
		}
		return choice;
	}

	public static String readFile() {
		name = new Scanner(System.in);
		String file;
		if (name.hasNext()) {
			file = name.nextLine();
		} else {
			file = null;
		}
		return file;
	}

	public static void operations(int choice) {
		switch (choice) {
		case 1:
			System.out.println("Diplay file to in ascending order:");
			break;
		case 2:
			System.out.println("Choose from the below bussiness opertaions");
			FileHandler fh = new FileHandler(root);
			secondaryOptions(fh);
			break;
		case 3:
			input.close();
			name.close();
			System.exit(0);
			break;
		default:
			System.out.println("Please enter a valid choice");
			break;
		}
	}

	public static void secondaryOptions(FileHandler fh) {
		System.out.println("Select from the below list of operations: ");
		System.out.println("1.Add a file to the existing directory list");
		System.out.println("2.Delete a file from the application");
		System.out.println("3.Search a file from the main directory");
		System.out.println("4.Navigate back to the main context");
		String filename;
		int choice = readInput();
		switch (choice) {
		case 1:
			System.out.println("Options 1 selected");
			System.out.println("Enter File name : ");
			filename = readFile();
			fh = new FileHandler(root);
			fh.addFile(filename);
			break;
		case 2:
			System.out.println("Options 2 selected");
			System.out.println("Enter File name : ");
			filename = readFile();
			fh = new FileHandler(root);
			fh.deletefile(filename);
			break;
		case 3:
			System.out.println("Options 3 selected");
			System.out.println("Enter File name : ");
			filename = readFile();
			fh = new FileHandler(root);
			fh.searchFile(filename);
			break;
		case 4:
			System.out.println("Options 4 selected");
			mainOptions();
			break;
		default:
			System.out.println("Please choose from the given options");
			break;
		}
	}
}

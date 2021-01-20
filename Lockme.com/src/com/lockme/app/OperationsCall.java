package com.lockme.app;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.lockme.filehandler.FileHandler;
import com.lockme.filehandler.SearchType;

public class OperationsCall {
	String rootpath;
	static Scanner input;
	static Scanner name;
	static FileHandler fh;

	/**
	 * This function accepts user input for root directory and exits on providing, a
	 * valid directory path.
	 */
	public void getroot() {
		System.out.println("Please Enter the root directory:");
		while (true) {
			
			String rootpath = readStringInput();
			File file = new File(rootpath);
			
			if (file.isDirectory()) {
				
				System.out.println("Root Path Accepted : " + file.getAbsolutePath());
				this.rootpath = rootpath;
				break;

			} else {
				
				System.err.println("Invalid directory path, please enter the correct rootpath");
				
			}
		}
	}

	/**
	 * This reads the options for selecting choice, reads integer options for both
	 * main and sub-level functions.
	 * 
	 * @return integer option selected.
	 */
	public static int readInput() {
		
		input = new Scanner(System.in);
		int choice;
		
		while (true) {
			
			while (!input.hasNextInt()) {
				
				input.nextLine(); // Discard junk entries
				System.err.println("...Invalid input, please enter a valid input: ");
				
			}
			try {
				
				choice = input.nextInt();
				break;
				
			} catch (InputMismatchException m) {
				
				System.err.println("...Invalid input, please enter a valid input:");
				
			}
			
		}
		
		return choice;
	}

	/**
	 * Accepts string input, for directory path and file name as string from user
	 * 
	 * @return return string path or filename read.
	 */
	public static String readStringInput() {
		name = new Scanner(System.in);
		String inString;
		if (name.hasNext()) {
			inString = name.nextLine();
		} else {
			inString = null;
		}
		return inString;
	}

	/**
	 * Calls function corresponding to user choice, perform the action chosen by the
	 * user.
	 * 
	 * @param choice
	 */
	public void parentOptions() {
		
		System.out.println("************************************************");
		System.out.println("Select from the below list of operations: ");
		System.out.println("************************************************");
		System.out.println("1.Retrieve the file names in an ascending order.");
		System.out.println("2.Business-level operations.");
		System.out.println("3.Change the root path.");
		System.out.println("4.Close the application.");
		System.out.println("************************************************");
		int choice = readInput();
		
		switch (choice) {
		
		case 1:
			
			// Display file to in ascending order:
			fh = new FileHandler(rootpath);
			fh.sortFile();
			break;
			
		case 2:
			
			// Displays the sub-level feature.
			secondaryOptions();
			break;

		case 3:
			
			getroot();
			break;

		case 4:
			
			input.close();
			name.close();
			System.out.println(
					"\t" + "\t" + "\t" + "\t" + "************LOCKME.COM APPLICATION CLOSED************" + "\t");
			System.exit(0);
			break;
			
		default:
			
			System.err.println("...invalid choice.");
			break;
			
		}
	}

	/**
	 * Calls sub-level functions as per the input of the user. User must choose by
	 * typing in the integer value against the desired operation.
	 */
	public void secondaryOptions() {
		
		boolean loop = true;
		while (loop) {
			
			System.out.println("*********************************************");
			System.out.println("Select from the below business operations : ");
			System.out.println("*********************************************");
			System.out.println("1.Add a file to the existing directory list");
			System.out.println("2.Delete a file from the application");
			System.out.println("3.Search a file from the main directory");
			System.out.println("4.Navigate back to the main context");
			System.out.println("*********************************************");
			String filename;
			int choice = readInput();

			// switch logic to choose operation according to choice.
			switch (choice) {
			
			case 1:
				
				System.out.println("-------------------");
				System.out.println("Options 1 selected");
				System.out.println("-------------------");
				System.out.println("Enter File name : ");
				filename = readStringInput();
				fh = new FileHandler(rootpath);
				fh.addFile(filename);
				break;
				
			case 2:
				
				System.out.println("-------------------");
				System.out.println("Options 2 selected");
				System.out.println("-------------------");
				System.out.println("Enter File name : ");
				filename = readStringInput();
				fh = new FileHandler(rootpath);
				fh.deleteFile(filename, rootpath);
				break;
				
			case 3:
				
				System.out.println("-------------------");
				System.out.println("Options 3 selected");
				System.out.println("-------------------");
				System.out.println("Please enter the type of search from the below options:");
				SearchType type = searchMode();
				boolean subSearch = subSearch();
				System.out.println("Enter Search Phrase or extension: ");
				filename = readStringInput();
				fh = new FileHandler(rootpath);
				boolean found = false;
				found = fh.searchFile(filename, type, this.rootpath, subSearch);
				if(!found) {
					System.out.println("Matches not found");
				}
				break;
				
			case 4:
				
				System.out.println("-----------------------------------------------------");
				System.out.println("Options 4 selected, Navigating back to the main menu");
				System.out.println("-----------------------------------------------------");
				loop = false;
				break;
				
			default:
				
				System.out.println("Please choose from the given options");
				break;
				
			}
			
		}
		
	}

	/**
	 * Asks whether user prefers a top level search, a detailed search that searches
	 * the sub-folder.
	 * 
	 * @return boolean value.
	 */
	public boolean subSearch() {
		
		System.out.println("Do you wish to search the sub-folders for matches? Type true or false (default set to false) :");
		
		boolean sub = false;
		if (readStringInput().toLowerCase().contentEquals("true")) {
			
			sub = true;
			
		}
		return sub;
	}

	
	
	/**
	 * This method allows the user to choose from type of search. "EXACT --search
	 * returns result that is an exact match "CONTAINS --search returns file that
	 * contains search value `* "STARTS_WITH --returns files starting with search
	 * phrase"); "BY_EXTENSION -- returns file of the extension searched
	 * 
	 * @return type selected by user, in case of illegal argument type passed is
	 *         EXACT.
	 */
	public SearchType searchMode() {

		System.out.println("EXACT        --search returns file that is an exact match to search value.");
		System.out.println("CONTAINS     --search returns file that contains search value.");
		System.out.println("STARTS_WITH  --search returns files starting with search phrase.");
		System.out.println("BY_EXTENSION --search returns file of the extension searched.");
		SearchType type;
		
		while (true) {
			
			try {
				
				type = SearchType.valueOf(readStringInput().toUpperCase());
				return type;
				
			} catch (IllegalArgumentException e) {
				
				System.err.println("..invalid search type entered, Choose from the given option.");
				
			}

		}

	}
	
	
	
}

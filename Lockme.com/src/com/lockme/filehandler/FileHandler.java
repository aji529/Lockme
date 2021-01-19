package com.lockme.filehandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
	String rootpath = null;

	public FileHandler(String rootpath) {

		this.rootpath = rootpath;

	}

	/**
	 * This method generates a list of contents of a directory and displays it in
	 * sorted order.
	 */
	public void sortFile() {

		File file = new File(rootpath);
		if (file.canRead()) {

			File[] dircnt = file.listFiles();
			List<String> dircontent = new ArrayList<String>();
			if (dircnt == null) {

				System.out.println("The given directory is empty");

			} else {

				for (File f : dircnt) {

					String fname = f.getName();
					dircontent.add(fname);

				}
				dircontent.sort(String::compareToIgnoreCase);
				dircontent.forEach((cnt) -> {
					String append = " --> is  a Directory";
					if ((new File(rootpath + "\\" + cnt)).isDirectory()) {
						System.out.println(cnt + append);
					} else {
						System.out.println(cnt);
					}
				});

			}

		} else {

			System.out.println("File specified by the root path cannot be read.");

		}

	}

	/**
	 * Adds a file of the given name to the directory pointed towards by the root
	 * path.
	 * 
	 * @param filename
	 */
	public void addFile(String filename) {

		File file = new File(rootpath + '\\' + filename);
		try {
			if (file.exists()) {

				System.err.println(file.getName() + " already exists in the root directory");

			} else {

				file.createNewFile();
				System.out.println("File " + filename + " created in the directory " + this.rootpath);

			}

		} catch (IOException e) {

			System.err.println("File could not be created");
			e.printStackTrace();

		}

	}

	/**
	 * Function deletes a file passed by the user. If a file is a Directory it
	 * further checks its content and call function confirmDelete().
	 * 
	 * @param filename
	 */
	public void deleteFile(String filename, String rootpath) {

		File file = new File(rootpath + '\\' + filename);
		if (file.exists()) {

			if (file.isDirectory()) {

				confirmDelete(file);

			} else {

				if (file.canWrite()) {

					file.delete();
					System.out.println("File " + filename + " deleted from root directory " + rootpath);

				} else {

					System.err.println("Permission denied for modifying " + filename
							+ " ... Delete operation could not be performed ");

				}
			}
		} else {

			System.err.println("File " + filename + " do not exit in the root directory " + rootpath);

		}

	}

	/**
	 * Handles deleting a file based on permission from a user, if its is directory
	 * with contents and iterates in itself to delete all sub-folders and file.
	 * 
	 * @param file
	 */
	@SuppressWarnings("resource")
	private void confirmDelete(File file) {

		File[] allContents = file.listFiles();
		if (allContents.length == 0) {

			if (file.canWrite()) {

				file.delete();
				System.out.println("File Deleted : " + file.getAbsolutePath());

			} else {

				System.err.println("Permission denied for modifiying " + file.getName() + "...Delete operation could not be performed");

			}

		} else {

			Scanner in = new Scanner(System.in);
			String per;
			ArrayList<String> ar1 = new ArrayList<>(Arrays.asList(file.list()));
			Iterator<String> itr = ar1.iterator();
			System.out.println("The directory has the below contents :");

			while (itr.hasNext()) {

				System.out.println(itr.next());

			}

			System.out.println("Are you sure you want to delete the folder, press 'Y' to delete and 'N' to not delete");

			while (true) {

				per = in.next();
				if (per.equalsIgnoreCase("N")) {

					System.out.println("File not deleted");
					break;

				} else if (per.equalsIgnoreCase("Y")) {

					for (File f1 : allContents) {
						deleteFile(f1.getName(), f1.getParent());

					}

					deleteFile(file.getName(), file.getParent());
					break;

				} else {

					System.out.println("Enter either Y/N to grant permission: ");

				}

			}

		}

	}

	/**
	 * Search a file in a directory and its sub-directories. Displays the absolute
	 * path for the matching search results. E-num data type, searchType defines the
	 * kind of search
	 * 
	 * @param searchPhrase
	 * @param type
	 * @param rootpath
	 * @param searchMode
	 * @return
	 */
	public boolean searchFile(String searchPhrase, SearchType type, String rootpath, boolean searchMode) {
		boolean searchResult = false;
		File file = new File(rootpath);
		File[] allContent = file.listFiles();
		if (allContent != null && searchMode) {
			
			for (File inside : allContent) {
				
				if (inside.isDirectory())
					searchResult = searchResult | searchFile(searchPhrase, type, inside.getAbsolutePath(), searchMode);
				
			}
		}
		
		File[] content;
		switch (type) {
		
		case EXACT:
			content = (File[]) file.listFiles((dir, name) -> name.equals(searchPhrase));
			break;
			
		case CONTAINS:
			
			content = (File[]) file.listFiles((dir, name) -> name.contains(searchPhrase));
			break;
			
		case STARTS_WITH:
			
			content = (File[]) file.listFiles((dir, name) -> name.startsWith(searchPhrase));
			break;
			
		case BY_EXTENSION:
			
			content = (File[]) file.listFiles((dir, name) -> name.endsWith(searchPhrase));
			break;
			
		default:
			
			content = (File[]) file.listFiles((dir, name) -> name.contentEquals(searchPhrase));
			break;
			
		}

		if (content != null) {
			
			for (File matches : content) {
				
				System.out.println("File found inside given root directory, with file path:" + matches.getAbsolutePath());
				searchResult = true;
				
			}
			
		} else {
			
			searchResult = false;
			
		}

		return searchResult;
	}

}

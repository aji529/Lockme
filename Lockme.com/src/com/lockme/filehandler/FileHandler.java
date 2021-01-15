package com.lockme.filehandler;

import java.io.File;
import java.io.IOException;

public class FileHandler {
	String rootpath=null;
	File file;
	public FileHandler(String rootpath) {
		this.rootpath = rootpath;
		
	}

	public void sortFile() {
		System.out.println("File will displayed in sorted manner");
	}

	public void addFile(String filename) {
		System.out.println("File can added to the directory mentioned:" + filename);
		file= new File(rootpath+filename);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("File could not be created");
			e.printStackTrace();
		}
	}

	public void deletefile(String filename) {
		System.out.println("File can deleted from root directory:" + filename);
	}

	public void searchFile(String filename) {
		System.out.println("You can search for a file exitence for :" + filename);
	}
}

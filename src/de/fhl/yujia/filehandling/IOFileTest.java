package de.fhl.yujia.filehandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOFileTest {
	public static void main(String[] args) throws Throwable {
		String fileName = "org.txt";
		IOFile iof = new IOFile(fileName);
		
		//test countLines()
		System.out.println("**test counting lines**");
		System.out.println("There are "+iof.countLines()+" lines in this file.");
		System.out.println();
		
		//test write(OutputStream os)
		String fileDest = "output.txt";
		System.out.println("**test write**");
		try {
			iof.write(new FileOutputStream(fileDest));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println();
		
		//test print()
		System.out.println("**test print**");
		iof.print();
		System.out.println();
		
		//test copy(String filename)
		System.out.println("**test copy**");
		String fileCopy = "copy.txt";
		try {
			iof.write(new FileOutputStream(fileCopy));
		} catch (IOException e) {
			e.printStackTrace();
		}
		iof.copy(fileCopy);
		new IOFile(fileCopy).print();
		System.out.println();
		
		//test printDirectory()
		System.out.println("**test print directory**");
		System.out.println("The file directory is: ");
		iof.printDirectory();
		System.out.println();
		
		//test List<String> getOtherFiles()
		System.out.println("**test returning list of other files in same directory**");
		System.out.println("The list of other files are: ");
		System.out.println(iof.getOtherFiles());
		System.out.println();

		// test delete()
		//the org.txt exists in /YujiaZHANG_FileHandling/src/Test/org.txt as well
		//so you do not need to create a new org.txt every time before you run the project
		//because a previous running will delete org.txt
		System.out.println("**test delete**");
		iof.delete();
		System.out.println("Does file still exist? " + new File(fileName).exists());
		System.out.println();
	}
}

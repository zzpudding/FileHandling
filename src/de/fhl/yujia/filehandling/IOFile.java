package de.fhl.yujia.filehandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

public class IOFile {
	private String fileName = null;
	private File f = null;

	IOFile(String fn) {
		fileName = fn;
		f = new File(fn);
	}
	
	/**
	 * reset fr and br 
	 * @return 
	 */
	//must be used if br is closed before
	private BufferedReader open() {
		FileReader fr = null;
		try {
			fr = new FileReader(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new BufferedReader(fr);
	}

	/**
	 * count line amount of file
	 * @return the number of Lines
	 * @throws IOException 
	 */
	public int countLines() throws IOException {
		int line = 0;
		BufferedReader br = this.open();
		try {
			while (br.readLine() != null) {
				line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		br.close();
		return line;
	}
	/**
	 * write file on stream
	 * @param os outputstream
	 * @throws IOException 
	 */
	public void write(OutputStream os) throws IOException{
		BufferedReader br = this.open();
		try {
			while (br.ready()) {
				os.write(br.read());
			}
		} catch (IOException e) {
			System.out.println("Cannot be written");
		}
		br.close();
	}
	/**
	 * print file on command line
	 * @throws IOException 
	 */
	public void print() throws IOException {
		BufferedReader br = this.open();
		String s;
		try {
			while (br.ready()) {
				s = br.readLine();
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * copy file content to the file ‘filename’
	 * @param filename the name of file 
	 */
	public void copy(String filename){
		try {
			Files.copy(f.toPath(), new File(fileName).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * delete the file
	 */
	public void delete(){
		if(f.delete()){
			System.out.println(f.getName()+" is deleted.");
		}
		else{
			System.out.println("Cannot delete");
		}
	}
	
	/**
	 * print the file directory
	 */
	public void printDirectory(){
		System.out.println(f.getAbsolutePath());
	}
	
	/**
	 * return list of other files in same directory as file
	 * @return list of other files in same directory as file
	 */
	List<String> getOtherFiles(){
		List<String> dir = new LinkedList<String>();
		for (File tempf: f.getAbsoluteFile().getParentFile().listFiles()) {
			dir.add(tempf.getName());
		}
		return dir;
	}
}

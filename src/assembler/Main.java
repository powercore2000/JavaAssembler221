package assembler;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Main {

	
	static FileReader asmFile;
	static Parser parser;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Running Assembler...");
		
		asmFile = null;
		parser = null;
		
		if(args.length < 1) {
			System.out.println("Please pass in argument for target file name");
			return;
		}
		
		
			System.out.println("Looking for file " + args[0]);		
			
			String filename = args[0];
			asmFile = getFile(filename);
			
			if(asmFile != null) {
				System.out.println("File found! Parsing...");
				parser = new Parser(asmFile);
				parser.ParseFile();
			}
		
		
		
	}
	
	
	
	public static FileReader getFile(String fileName) {
		
		String directory = System.getProperty("user.dir");
		String filePath = directory + "\\" + fileName;
		System.out.println("Searching directory " + filePath);
		try {
			return new FileReader(filePath);
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static FileReader getHackFile() {return asmFile;}

}

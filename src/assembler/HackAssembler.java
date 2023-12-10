package assembler;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

public class HackAssembler {

	
	static FileReader asmFile;
	static ParserModule parser;
	static String fileName;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Running Assembler...");
		
		asmFile = null;
		parser = null;
		fileName = null;
		
		List<String> binaryCodeLines = new ArrayList<String>();
		
		if(args.length < 1) {
			System.out.println("Please pass in argument for target file name");
			return;
		}
		
		
			System.out.println("Looking for file " + args[0]);		
			
			fileName = args[0];
			fileName = fileName.split("\\.", 2)[0];
			asmFile = findAsmFile(fileName);
			
			if(asmFile == null) {
				System.out.println("Error file not found!");
				return;
			}
			
			
				System.out.println("File found! Parsing...");
				parser = new ParserModule(asmFile);
				binaryCodeLines = parser.ParseFileToBinary();
				createAsmFile(fileName);
				writeToAsmFile(binaryCodeLines);
		
		
	}
	
	
	
	public static FileReader findAsmFile(String fileName) {
		
		String directory = System.getProperty("user.dir");
		String filePath = directory + "\\" + fileName + ".asm";
		System.out.println("Searching directory " + filePath);
		try {
			return new FileReader(filePath);
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static void createAsmFile(String fileName) {
		
		
		try {
			File hackFile = new File(fileName + ".hack");
		      if (hackFile.createNewFile()) {
		        System.out.println("File created: " + hackFile.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  }
		
	public static void writeToAsmFile(List<String> binaryCode) {
		
		
		try {
		      FileWriter hackFileWriter = new FileWriter(fileName + ".hack");
		      
		      for(int a = 0; a < binaryCode.size(); a++) {
		    	  
		    	  hackFileWriter.write(binaryCode.get(a)+"\n");
		      }
		     
		      hackFileWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  }
	
		 
	
	public static FileReader getAsmFile() {return asmFile;}

}

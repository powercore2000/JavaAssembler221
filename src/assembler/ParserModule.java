package assembler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserModule {

	BufferedReader asmFileBuffer;
	FileReader originalFile;
	boolean moreCommands;
	
	String currentCommandType;
	String currentCommandSymbol;
	String currentCommandDest;
	String currentCommandComp;
	String currentCommandJump;
	
	String currentCommandString;
	String nextLine;
	int currentLineNumber = 0;
	
	List<List<String>> parsedAssembly = new ArrayList<List<String>>();
	
	List<String> binaryCodeLines = new ArrayList<String>();
	
	//SymbolTableModule symbolTable = new SymbolTableModule();
	
	
	public void resetParser() {
		
		binaryCodeLines.clear();
		CodeModule.setSymbolTable(new SymbolTableModule());
		currentLineNumber = 0;
	}
	
	
	public ParserModule(FileReader inputFile) {
		
		originalFile = inputFile;
		asmFileBuffer = new BufferedReader(inputFile);	

		//ParseFile();
    }
	
	
	public void LogAllFileContents() throws IOException {
		
		System.out.println("Logging all file contents\n");
		
	 // Declaring loop variable
		String line;
	     // Holds true till there is nothing to read
	     while ((line = asmFileBuffer.readLine()) != null) {
	    	
	         // Print all the content of a file
	         System.out.println(line);      
	         advance();
	         
	     }
	     
	     asmFileBuffer.reset();
     
     
	}
	
	public void LogNonEmptyFileLines() throws IOException {
		
		System.out.println("Logging non empty file contents\n----------------");
		
	 // Declaring loop variable
		
	     // Holds true till there is nothing to read
	     while (hasMoreCommands()) {
	    	// String currentLine;
	         // Print all the content of a file
	         System.out.println(nextLine);      
	         //advance();
	         
	     }
	     System.out.println("-------------\nEnd of file contents\n");
	     asmFileBuffer.reset();
     
     
	}
	
	public void ParseFile() {
		
		resetParser();
		try {
			while(hasMoreCommands()) {
				
				advance();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<String> ParseFileToBinary() {
		resetParser();
		//First Pass
		try {
			while(hasMoreCommands()) {
				
				advance();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}		
		
		//Second Pass
		for(int a = 0; a < parsedAssembly.size(); a++) {
			
			String binaryCode = CodeModule.translateToBinaryCode(parsedAssembly.get(a));
			binaryCodeLines.add(binaryCode);
			
			//System.out.println(binaryCode);
		}

		
		return binaryCodeLines;
		
	}
	
	
	
	public boolean hasMoreCommands()  throws IOException {
		
		if ((nextLine = asmFileBuffer.readLine()) != null) {
			
			nextLine = nextLine.trim();
			
			if(nextLine.isEmpty() || nextLine.startsWith("//")) {
				
				return hasMoreCommands();
			}
			
			else {
				currentLineNumber++;
				return true;
			}
			
		}
		
		return false;

	}
	
	public void advance() {

		
		List<String> commandComponents = new ArrayList<String>();
		
		
		currentCommandString = nextLine;
		currentCommandSymbol = "";
		currentCommandType = "";
		currentCommandDest = "";
		currentCommandComp = "";
		currentCommandJump = "";
		
		currentCommandString = currentCommandString.split("\\/", 2)[0];
		currentCommandString = currentCommandString.replaceAll("\\s+", "");
		//System.out.println(currentCommandString);
		parseCommandType();
		
		if(currentCommandType == "C_COMMAND") {
			
			parseCommandC();
			
			//System.out.println(currentCommandType + ":");
			//System.out.println("Dest:" + currentCommandDest);
			//System.out.println("Comp:" + currentCommandComp);
			//System.out.println("Jump:" + currentCommandJump);
			
			commandComponents.add(currentCommandType);
			commandComponents.add(currentCommandDest);
			commandComponents.add(currentCommandComp);
			commandComponents.add(currentCommandJump);
			
		}
		
		//A or L COMMAND
		else {
			
			parseSymbol();
			//System.out.println(currentCommandType + " | Symbol: " + currentCommandSymbol);
			
			if(currentCommandType == "L_COMMAND") {
				currentLineNumber--; // DO not count the line the L instruction was found on
				//System.out.println("Adding symbol " + currentCommandSymbol + " on line " + currentLineNumber);
				CodeModule.getSymbolTable().addEntry(currentCommandSymbol, currentLineNumber);
				return;
			}
			commandComponents.add(currentCommandType);
			commandComponents.add(currentCommandSymbol);
			
			//aInstruction
		}
		
		parsedAssembly.add(commandComponents);

		
	}
	
	
	
	public String commandType() {
		
		
		return currentCommandType;
	}
	
	public void parseCommandType() {
		if(currentCommandString.startsWith("@"))
			currentCommandType = "A_COMMAND";
		
		else if(currentCommandString.startsWith("("))
			currentCommandType = "L_COMMAND";
			
		else
			currentCommandType = "C_COMMAND";

		
	}
	
	
	
	public void parseSymbol() {
		
		currentCommandSymbol = currentCommandString.substring(1);
		
		if(currentCommandSymbol.charAt(currentCommandSymbol.length()-1) == ')') {
			
			currentCommandSymbol = currentCommandSymbol.substring(0, currentCommandSymbol.length()-1);
		}
	}

	public String symbol () {
		
	
		return currentCommandSymbol;
	}

	
	public void parseCommandC () {
		
		C_Command_Phases currentPhase = C_Command_Phases.DEST;
	
		
		if (currentCommandString.indexOf(';') != -1) {
			
			currentPhase = C_Command_Phases.COMP;
		}
		
		for(int a = 0; a < currentCommandString.length(); a++) {
			
			
			if(currentCommandString.charAt(a) != '=' &&  currentCommandString.charAt(a) != ';'  &&  currentCommandString.charAt(a) != '/') {
				
				switch(currentPhase) {
				
					case DEST:
					{
						currentCommandDest += currentCommandString.charAt(a);
					}
					break;
					
					case COMP:
					{
						currentCommandComp += currentCommandString.charAt(a);
					}
					break;
					
					case JUMP:
					{
						currentCommandJump += currentCommandString.charAt(a);
					}
					break;
				
				}
				
			}
			
			else if(currentCommandString.charAt(a) == '=') {
				
				currentPhase = C_Command_Phases.COMP;
			}
				
			else if(currentCommandString.charAt(a) == ';') {
				
				currentPhase = C_Command_Phases.JUMP;
			}
			
			else if(currentCommandString.charAt(a) == '/') {
							
				break;
			}
			 
			 
			
		}
		
		if(currentCommandDest.isEmpty())
			currentCommandDest = null;
		
		if(currentCommandJump.isEmpty())
			currentCommandJump = null;
		
		if(currentCommandComp.isEmpty())
			currentCommandComp = null;
		
	}
	
	
	
		
	public String dest () {
		
		 
		return currentCommandDest;
	}
	
	public String comp() {
		
		return currentCommandComp;
	}
	
	public String jump() {
		
		return currentCommandJump;
	}

	
	enum C_Command_Phases {
		  DEST,
		  COMP,
		  JUMP
		}
	
}

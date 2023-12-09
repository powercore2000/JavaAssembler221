package assembler;

import java.io.*;

public class Parser {

	BufferedReader asmFileBuffer;
	boolean moreCommands;
	
	String currentCommandType;
	String currentCommandSymbol;
	String currentCommandDest;
	String currentCommandComp;
	String currentCommandJump;
	
	String currentCommandString;
	String nextLine;
	
	public Parser(FileReader inputFile) {
		
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
		
		
		try {
			while(hasMoreCommands()) {
				
				advance();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public boolean hasMoreCommands()  throws IOException {
		
		if ((nextLine = asmFileBuffer.readLine()) != null) {
			
			nextLine = nextLine.trim();
			
			if(nextLine.isEmpty() || nextLine.startsWith("//")) {
				
				return hasMoreCommands();
			}
			
			else
				return true;
			
		}
		
		return false;

	}
	
	public void advance() {

		currentCommandString = nextLine;
		currentCommandSymbol = "";
		currentCommandType = "";
		currentCommandDest = "";
		currentCommandComp = "";
		currentCommandJump = "";
		
		parseCommandType();
		
		if(currentCommandType == "C_COMMAND") {
			
			parseCommandC();
			
			System.out.println(currentCommandType + ":");
			System.out.println("Dest:" + currentCommandDest);
			System.out.println("Comp:" + currentCommandComp);
			System.out.println("Jump:" + currentCommandJump);
		}
		
		//A or L COMMAND
		else {
			
			parseSymbol();
			System.out.println(currentCommandType + " | Symbol: " + currentCommandSymbol);
		}
		
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
			
			currentCommandSymbol = currentCommandSymbol.substring(0, currentCommandSymbol.length()-2);
		}
	}

	public String symbol () {
		
	
		return currentCommandSymbol;
	}

	
	public void parseCommandC () {
		
		C_Command_Phases currentPhase = C_Command_Phases.DEST;
		for(int a = 0; a < currentCommandString.length(); a++) {
			
			
			if(currentCommandString.charAt(a) != '=' &&  currentCommandString.charAt(a) != ';') {
				
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
	
	return currentCommandType;
}

public String jump() {
	
	return currentCommandType;
}


enum C_Command_Phases {
	  DEST,
	  COMP,
	  JUMP
	}
	
}

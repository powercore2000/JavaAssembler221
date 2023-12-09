package assembler;

import java.util.HashMap;
import java.util.List;

public class CodeModule {

    private static final HashMap<String, String> compInstructionTable = new HashMap<>();
    private static final HashMap<String, String> aBitInstructionTable = new HashMap<>();
    private static final HashMap<String, String> jumpInstructionTable = new HashMap<>();
    private static final HashMap<String, String> destInstructionTable = new HashMap<>();
    
    static{
    	
    	aBitInstructionTable.put(null, "0");
    	aBitInstructionTable.put("0", "0");
    	aBitInstructionTable.put("1", "0");
    	aBitInstructionTable.put("-1", "0");
    	aBitInstructionTable.put("D", "0");
    	aBitInstructionTable.put("A", "0");
    	aBitInstructionTable.put("!D", "0");
    	aBitInstructionTable.put("!A", "0");
    	aBitInstructionTable.put("-D", "0");
    	aBitInstructionTable.put("-A", "0");
    	aBitInstructionTable.put("D+1", "0");
    	aBitInstructionTable.put("A+1", "0");
    	aBitInstructionTable.put("D-1", "0");
    	aBitInstructionTable.put("A-1", "0");
    	aBitInstructionTable.put("D+A", "0");
    	aBitInstructionTable.put("D-A", "0");
    	aBitInstructionTable.put("A-D", "0");
    	aBitInstructionTable.put("D&A", "0");
    	aBitInstructionTable.put("D|A", "0");
    	
    	
    	aBitInstructionTable.put("M", "1");
        aBitInstructionTable.put("!M", "1");
        aBitInstructionTable.put("-M", "1");
        aBitInstructionTable.put("M+1", "1");
        aBitInstructionTable.put("M-1", "1");
        aBitInstructionTable.put("D+M", "1");
        aBitInstructionTable.put("D-M", "1");
        aBitInstructionTable.put("M-D", "1");
        aBitInstructionTable.put("D&M", "1");
        aBitInstructionTable.put("D|M", "1");
    	

		compInstructionTable.put("0", "101010");
		compInstructionTable.put("1", "111111");
		compInstructionTable.put("-1", "111010");
		compInstructionTable.put("D", "001100");
		compInstructionTable.put("A", "110000");
		compInstructionTable.put("!D", "001101");
		compInstructionTable.put("!A", "110001");
		compInstructionTable.put("-D", "001111");
		compInstructionTable.put("-A", "110011");
		compInstructionTable.put("D+1", "011111");
		compInstructionTable.put("A+1", "110111");
		compInstructionTable.put("D-1", "001110");
		compInstructionTable.put("A-1", "110010");
		compInstructionTable.put("D+A", "000010");
		compInstructionTable.put("D-A", "010011");
		compInstructionTable.put("A-D", "000111");
		compInstructionTable.put("D&A", "000000");
		compInstructionTable.put("D|A", "010101");
		
    	compInstructionTable.put("M", "110000");
        compInstructionTable.put("!M", "110001");
        compInstructionTable.put("-M", "110011");
        compInstructionTable.put("M+1", "110111");
        compInstructionTable.put("M-1", "110010");
        compInstructionTable.put("D+M", "000010");
        compInstructionTable.put("D-M", "010011");
        compInstructionTable.put("M-D", "000111");
        compInstructionTable.put("D&M", "000000");
        compInstructionTable.put("D|M", "010101");
        		
		

		destInstructionTable.put(null, "000");
		destInstructionTable.put("M", "001");
		destInstructionTable.put("D", "010");
		destInstructionTable.put("MD", "011");
		destInstructionTable.put("A", "100");
		destInstructionTable.put("AM", "101");
		destInstructionTable.put("AD", "110");
		destInstructionTable.put("AMD", "111");

        
        

        
        jumpInstructionTable.put(null, "000");
		jumpInstructionTable.put("JGT", "001");
		jumpInstructionTable.put("JEQ", "010");
		jumpInstructionTable.put("JGE", "011");
		jumpInstructionTable.put("JLT", "100");
		jumpInstructionTable.put("JNE", "101");
		jumpInstructionTable.put("JLE", "110");
		jumpInstructionTable.put("JMP", "111");
    }
    
    
	public static String translateToBinaryCode(List<String> components) {
		
		StringBuilder returnCode = new StringBuilder();
		
		if(components.get(0) == "C_COMMAND") {
			
			returnCode.append("111");
			

			returnCode.append(comp(components.get(2)));
			returnCode.append(dest(components.get(1)));			
			returnCode.append(jump(components.get(3)));
		}
		
		else if (components.get(0) == "A_COMMAND") {
			
			returnCode.append(aInstruction(components.get(1)));
		}
		
		else if (components.get(0) == "L_COMMAND") {
			
			
			returnCode.append("0000000000010000");
		}
		
		return returnCode.toString();
	}
	
	public static String aInstruction(String value) {
		
		int val = Integer.parseInt(value);
		String bin = Integer.toBinaryString(0x10000 | val).substring(1);
		return bin;
	}
	public static String jump(String mnemonic) {
		
		String codedJump = jumpInstructionTable.get(mnemonic);
		
		return codedJump;
		
		
	}
	
	public static String dest(String mnemonic) {
		
		String codedDest = destInstructionTable.get(mnemonic);
		
		return codedDest;
		
		
	}
	
	public static String comp(String mnemonic) {
		
		String codedComp = aBitInstructionTable.get(mnemonic) + compInstructionTable.get(mnemonic);
		  System.out.println("A bit : " + aBitInstructionTable.get(mnemonic));
		  System.out.println("comp bits bit : " + compInstructionTable.get(mnemonic));
		  System.out.println("final comp bits : " + codedComp);
		return codedComp;
		
		
	}




	
	
	
}

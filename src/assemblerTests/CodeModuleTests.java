package assemblerTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import assembler.CodeModule;



class CodeModuleTests {

	@Test
	void testJump() {
		String binaryResult = CodeModule.jump(null);
		
		assertTrue(binaryResult == "000");
	}
	
	@Test
	void testComp() {
		String binaryResult = CodeModule.comp("D-1");
		System.out.println(binaryResult);
		assertTrue(binaryResult == "0001110");
	}
	
	@Test
	void testAInstruct() {
		String binaryResult = CodeModule.aInstructionStringConversion("@2");
		System.out.println(binaryResult);
		assertTrue(binaryResult == "0000000000000010");
	}

}

package assemblerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import assembler.Main;
import assembler.ParserModule;

class ParserModuleTests {

	
	@Test
	void testLogNonEmptyFileLines() {
		
		FileReader file = Main.findAsmFile("Add.asm");
		ParserModule parser = new ParserModule(file);
		try {
			parser.LogNonEmptyFileLines();
			//assertTrue(true);
			//return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//assertTrue(true);
			//return;
		}
		
	}
	
	@Test
	void testParseFile() {
		FileReader file = Main.findAsmFile("MaxL.asm");
		ParserModule parser = new ParserModule(file);
		parser.ParseFile();
	}


}

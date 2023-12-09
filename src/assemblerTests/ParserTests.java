package assemblerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import assembler.Main;
import assembler.Parser;

class ParserTests {

	
	@Test
	void testLogNonEmptyFileLines() {
		
		FileReader file = Main.getFile("Add.asm");
		Parser parser = new Parser(file);
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
		FileReader file = Main.getFile("MaxL.asm");
		Parser parser = new Parser(file);
		parser.ParseFile();
	}


}

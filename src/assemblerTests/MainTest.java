package assemblerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import assembler.Main;

class MainTest {

	@Test
	void testMainAddAsm() {
		//fail("Not yet implemented");
		Main.main(new String[]{"Add.asm"});
		assertTrue(Main.getAsmFile() != null);
	}
	
	@Test
	void testMainMaxAsm() {
		//fail("Not yet implemented");
		Main.main(new String[]{"MaxL.asm"});
		assertTrue(Main.getAsmFile() != null);
	}
	
	@Test
	void testMainZeroARgs() {
		//fail("Not yet implemented");
		Main.main(new String[] {});
		assertTrue(Main.getAsmFile() == null);
	}

}

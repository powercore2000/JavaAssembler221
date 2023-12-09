package assemblerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import assembler.Main;

class MainTest {

	@Test
	void testMainWorflow() {
		//fail("Not yet implemented");
		Main.main(new String[]{"Add.asm"});
		assertTrue(Main.getHackFile() != null);
	}
	
	@Test
	void testMainZeroARgs() {
		//fail("Not yet implemented");
		Main.main(new String[] {});
		assertTrue(Main.getHackFile() == null);
	}

}

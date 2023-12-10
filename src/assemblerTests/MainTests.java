package assemblerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import assembler.HackAssembler;

class MainTests {

	@Test
	void testMainAddAsm() {
		//fail("Not yet implemented");
		HackAssembler.main(new String[]{"Add.asm"});
		assertTrue(HackAssembler.getAsmFile() != null);
	}
	
	@Test
	void testMainMaxLAsm() {
		//fail("Not yet implemented");
		HackAssembler.main(new String[]{"MaxL.asm"});
		assertTrue(HackAssembler.getAsmFile() != null);
	}
	
	@Test
	void testMainMaxAsm() {
		//fail("Not yet implemented");
		HackAssembler.main(new String[]{"Max.asm"});
		assertTrue(HackAssembler.getAsmFile() != null);
	}
	
	@Test
	void testMainMaxVAsm() {
		//fail("Not yet implemented");
		HackAssembler.main(new String[]{"MaxV.asm"});
		assertTrue(HackAssembler.getAsmFile() != null);
	}
	
	
	@Test
	void testMainZeroARgs() {
		//fail("Not yet implemented");
		HackAssembler.main(new String[] {});
		assertTrue(HackAssembler.getAsmFile() == null);
	}

}

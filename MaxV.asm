// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/06/max/MaxL.asm

// Contains user-defined variables.

@num1
D=M
@num2
D=D-M
@12
D;JGT
@1
D=M
@2
M=D
@16
0;JMP
@0             
D=M
@max
M=D
@16
0;JMP

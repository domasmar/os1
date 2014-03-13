package os1;

public enum Command {
	
        MOV_AX,// 4 bytes -> register AX
        MOV_BX,// 4 bytes -> register BX
	LOA_AX,// memory -> register
	LOA_BX,
	STO_AX, // register -> memory
	STO_BX,
	PUSH, 
	POP,
	ADD, // 2 viršutinių elementų suma -> AX
	SUB, // 2 viršutinių elementų skirtumas -> AX
	CMP,
	JA, // jump if above
	JB, // jump if below
	JE, // jump if equal
	JNE, // jump if not equa
	OUTR_AX, // prints register value
	OUTR_BX,
	OUTM, // prints memory value 
	// FORK
	STOP,
}

package os1.Interpreter;

public enum Command {
	
	LOA_AX,// memory -> register
	LOA_BX,
	STO_AX, // register -> memory
	STO_BX,
	PUSH, 
	POP,
	ADD, // 2 virðutiniø elementø suma -> AX
	SUB, // 2 virðutiniø elementø skirtumas -> AX
	CMP,
	JA, // jump if above
	JB, // jump if below
	JE, // jump if equal
	JNE, // jump if not equa
	OUTR_AX, // prints register value
	OUTR_BX,
	OUTM, // prints memory value 
	// FORM
	STOP,
	
}

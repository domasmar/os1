package os1;

public enum Command {
	
	LOA, // memory -> register
	STO, // register -> memory
	PUSH, 
	POP,
	ADD, // 2 virðutiniø elementø suma -> AX
	SUB, // 2 virðutiniø elementø skirtumas -> AX
	CMP,
	JA, // jump if above
	JB, // jump if below
	JE, // jump if equal
	JNE, // jump if not equa
	OUTR, // prints register value
	OUTM, // prints memory value 
	// FORM
	STOP,
	
}

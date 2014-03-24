package os1.Interpreter;


import os1.CPU.CPU;
import static os1.Interpreter.Command.*;
import os1.Memory.Stack;
import os1.Memory.VMMemory;
import os1.PeripheralDevices.ChannelsDevice;

/**
 *
 * @author Arturas
 */
public class ProgramExecutor {

    private CPU cpu;
    private VMMemory memory;
    private Stack stack;
    private ChannelsDevice cd;
    public CmdWithVar lastCmd;
    

    public ProgramExecutor(CPU cpu, VMMemory virtualMemory, Stack stack, ChannelsDevice cd) {
        this.cpu = cpu;
        this.memory = virtualMemory;
        this.stack = stack;
        this.lastCmd = new CmdWithVar();
        this.cd = cd;
    }

    public boolean executeNext() throws Exception {
        int word = memory.getValue(cpu.getCS() + cpu.getIP());
        String bits = intToBits(word);
        String cmdBits = bits.substring(0, 8);
        String valueBits = bits.substring(8, 16);
        int cmdInt = (byte) Integer.parseInt(cmdBits, 2);
        int valueInt = Integer.parseInt(valueBits, 2);

        if (cmdInt == CommandBytecode.ADD) {
            cmdAdd();
            return true;
        }

        if (cmdInt == CommandBytecode.SUB) {
            cmdSub();
            return true;
        }

        if (cmdInt == CommandBytecode.CMP) {
            cmdCmp();
            return true;
        }

        if (cmdInt == CommandBytecode.STOP) {
            cmdStop();
            return false;
        }

        if (cmdInt == CommandBytecode.MOV_AX) {
            valueInt = memory.getValue(cpu.getCS() + cpu.getIP() + 1);
            cmdMovAx(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.MOV_BX) {
            valueInt = memory.getValue(cpu.getCS() + cpu.getIP() + 1);
            cmdMovBx(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.LOA_AX) {
            cmdLoaAx(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.LOA_BX) {
            cmdLoaBx(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.STO_AX) {
            cmdStoAx(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.STO_BX) {
            cmdStoBx(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.PUSH) {
            cmdPush(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.POP) {
            cmdPop(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.JA) {
            cmdJa(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.JB) {
            cmdJb(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.JE) {
            cmdJe(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.JMP) {
            cmdJmp(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.JNE) {
            cmdJne(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.OUTR_AX) {
            cmdOutrAx();
            return true;
        }

        if (cmdInt == CommandBytecode.OUTR_BX) {
            cmdOutrBx();
            return true;
        }

        if (cmdInt == CommandBytecode.OUTM) {
            cmdOutM(valueInt);
            return true;
        }
        return false;
    }

//******************************************************************************
    private void cmdMovAx(int variable) throws Exception {
        cpu.setAX(variable);

        short nextCmdAddr = (short) (cpu.getIP() + 2);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = MOV_AX;
        lastCmd.variable = variable;
    }

    private void cmdMovBx(int variable) throws Exception {
        cpu.setBX(variable);

        short nextCmdAddr = (short) (cpu.getIP() + 2);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = MOV_BX;
        lastCmd.variable = variable;
    }

    private void cmdLoaAx(int variable) throws Exception {
        cpu.setAX(memory.getValue(variable));

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = LOA_AX;
        lastCmd.variable = variable;
    }

    private void cmdLoaBx(int variable) throws Exception {
        cpu.setBX(memory.getValue(variable));

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = LOA_BX;
        lastCmd.variable = variable;
    }

    private void cmdStoAx(int variable) throws Exception {
        if (variable >= cpu.getSS()) {
            throw new Exception("Adresas išlipa iš DS segmento!");
        }
        memory.setValue(variable, cpu.getAX());

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = STO_AX;
        lastCmd.variable = variable;
    }

    private void cmdStoBx(int variable) throws Exception {
        if (variable >= cpu.getSS()) {
            throw new Exception("Adresas išlipa iš DS segmento!");
        }
        memory.setValue(variable, cpu.getBX());
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = STO_BX;
        lastCmd.variable = variable;
    }

    private void cmdPush(int variable) throws Exception {
        stack.push(variable);
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = PUSH;
        lastCmd.variable = variable;
    }

    private void cmdPop(int variable) throws Exception {
        stack.pop(variable);
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = POP;
        lastCmd.variable = variable;
    }

    private void cmdJa(int variable) throws Exception {
        if (((cpu.getCS() + variable) >= 255) || (variable < 0)) {
            throw new Exception("Jump komanda išlipa iš CS segmento!");
        }

        if (cpu.getC() == 1) {
            cpu.setIP((short) variable);
        }
        lastCmd.command = JA;
        lastCmd.variable = variable;
    }

    private void cmdJmp(int variable) throws Exception {
        if (((cpu.getCS() + variable) >= 255) || (variable < 0)) {
            throw new Exception("Jump komanda išlipa iš CS segmento!");
        }
        cpu.setIP((short) variable);

        lastCmd.command = JMP;
        lastCmd.variable = variable;
    }

    private void cmdJb(int variable) throws Exception {
        if (((cpu.getCS() + variable) >= 255) || (variable < 0)) {
            throw new Exception("Jump komanda išlipa iš CS segmento!");
        }

        if (cpu.getC() == 2) {
            cpu.setIP((short) variable);
        }
        lastCmd.command = JB;
        lastCmd.variable = variable;
    }

    private void cmdJe(int variable) throws Exception {
        if (((cpu.getCS() + variable) >= 255) || (variable < 0)) {
            throw new Exception("Jump komanda išlipa iš CS segmento!");
        }

        if (cpu.getC() == 0) {
            cpu.setIP((short) variable);
        }
        lastCmd.command = JE;
        lastCmd.variable = variable;
    }

    private void cmdJne(int variable) throws Exception {
        if (((cpu.getCS() + variable) >= 255) || (variable < 0)) {
            throw new Exception("Jump komanda išlipa iš CS segmento!");
        }

        if (cpu.getC() == 1 || cpu.getC() == 2) {
            cpu.setIP((short) variable);
        }
        lastCmd.command = JNE;
        lastCmd.variable = variable;
    }

    private void cmdOutrAx() throws Exception {
    	cd.print(cpu.getAX());
//        output.receiveData(cpu.getAX());
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = OUTR_AX;
    }

    private void cmdOutrBx() throws Exception {
    	cd.print(cpu.getAX());
//        output.receiveData(cpu.getBX());
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = OUTR_BX;
    }

    private void cmdOutM(int variable) throws Exception {
    	cd.print(memory.getValue(variable));
//        output.receiveData(memory.getValue(variable));
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = OUTM;
        lastCmd.variable = variable;
    }

    private void cmdAdd() throws Exception {
        int firstEl = memory.getValue(cpu.getSS() + cpu.getSP());
        int secondEl = memory.getValue(cpu.getSS() + cpu.getSP() - 1);
        int sum = firstEl + secondEl;
        memory.setValue(cpu.getSS() + cpu.getSP(), sum);

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = ADD;
    }

    private void cmdSub() throws Exception {
        int firstEl = memory.getValue(cpu.getSS() + cpu.getSP());
        int secondEl = memory.getValue(cpu.getSS() + cpu.getSP() - 1);
        int diff = firstEl - secondEl;
        memory.setValue(cpu.getSS() + cpu.getSP(), diff);

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = SUB;
    }

    private void cmdCmp() throws Exception {
        if ((memory.getValue(cpu.getSS() + cpu.getSP())) == (memory.getValue(cpu.getSS() + cpu.getSP()) - 1)) {
            cpu.setC((byte) 0);
        }

        if ((memory.getValue(cpu.getSS() + cpu.getSP())) > (memory.getValue(cpu.getSS() + cpu.getSP()) - 1)) {
            cpu.setC((byte) 1);
        }

        if ((memory.getValue(cpu.getSS() + cpu.getSP())) < (memory.getValue(cpu.getSS() + cpu.getSP()) - 1)) {
            cpu.setC((byte) 2);
        }

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = CMP;
    }

    private void cmdStop() throws Exception {
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
        lastCmd.command = STOP;
    }

    private String intToBits(int a) {
        String bits = Integer.toBinaryString(a);
        if (bits.length() == 15) {
            bits = "0" + bits;
        }

        int length = bits.length();
        int diffLength = 32 - length;
        for (int i = 0; i < diffLength; i++) {
            bits = bits + "0";
        }
        return bits;
    }

    public String getLastCommand() {
        return this.lastCmd.command + " " + this.lastCmd.variable;

    }

}

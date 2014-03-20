package os1.Interpreter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import os1.CPU.CPU;
import os1.Memory.Stack;
import os1.Memory.VMMemory;

/**
 *
 * @author Arturas
 */
public class ProgramExecutor {

    private CPU cpu;
    private VMMemory memory;
    private Stack stack;
    public boolean debug;

    public ProgramExecutor(CPU cpu, VMMemory virtualMemory, Stack stack, boolean debug) {
        this.cpu = cpu;
        this.memory = virtualMemory;
        this.stack = stack;
        this.debug = debug;
    }

    public void execute() {
        boolean proceed = true;
        while (proceed) {
            try {
                proceed = recognizeCommand();
                if (debug) {
                    waitForInput();
                }
            } catch (Exception ex) {
                Logger.getLogger(ProgramExecutor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean recognizeCommand() throws Exception {
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

        if (cmdInt == CommandBytecode.JNE) {
            cmdJne(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.OUTR_AX) {
            cmdOutrAx(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.OUTR_BX) {
            cmdOutrBx(valueInt);
            return true;
        }

        if (cmdInt == CommandBytecode.OUTM) {
            cmdOutrBx(valueInt);
            return true;
        }
        return false;
    }

//******************************************************************************
    private void cmdMovAx(int variable) throws Exception {
        cpu.setAX(variable);

        short nextCmdAddr = (short) (cpu.getIP() + 2);
        cpu.setIP(nextCmdAddr);
    }

    private void cmdMovBx(int variable) throws Exception {
        cpu.setBX(variable);

        short nextCmdAddr = (short) (cpu.getIP() + 2);
        cpu.setIP(nextCmdAddr);
    }

    private void cmdLoaAx(int variable) throws Exception {
        cpu.setAX(memory.getValue(variable));

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
    }

    private void cmdLoaBx(int variable) throws Exception {
        cpu.setBX(memory.getValue(variable));

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
    }

    private void cmdStoAx(int variable) throws Exception {
        if (variable >= cpu.getSS()) {
            throw new Exception("Adress is out of bounds of DS");
        }
        memory.setValue(variable, cpu.getAX());

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
    }

    private void cmdStoBx(int variable) throws Exception {
        if (variable >= cpu.getSS()) {
            throw new Exception("Adress is out of bounds of DS");
        }
        memory.setValue(variable, cpu.getBX());
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
    }

    private void cmdPush(int variable) throws Exception {
        stack.Push(variable);
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
    }

    private void cmdPop(int variable) throws Exception {
        stack.Pop();
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
    }

    private void cmdJa(int variable) throws Exception {
        if ((variable >= 255) || (variable < cpu.getCS())) {
            throw new Exception("Jump is out of bounds!");
        }

        if (cpu.getC() == 1) {
            cpu.setIP((short) variable);
        }
    }

    private void cmdJb(int variable) throws Exception {
        if ((variable >= 255) || (variable < cpu.getCS())) {
            throw new Exception("Jump is out of bounds!");
        }

        if (cpu.getC() == 2) {
            cpu.setIP((short) variable);
        }
    }

    private void cmdJe(int variable) throws Exception {
        if ((variable >= 255) || (variable < cpu.getCS())) {
            throw new Exception("Jump is out of bounds!");
        }

        if (cpu.getC() == 0) {
            cpu.setIP((short) variable);
        }
    }

    private void cmdJne(int variable) throws Exception {
        if ((variable >= 255) || (variable < cpu.getCS())) {
            throw new Exception("Jump is out of bounds!");
        }

        if (cpu.getC() == 1 || cpu.getC() == 2) {
            cpu.setIP((short) variable);
        }
    }

    private void cmdOutrAx(int variable) throws Exception {
        //TO-DO
    }

    private void cmdOutrBx(int variable) throws Exception {
        //TO-DO
    }

    private void cmdOutM(int variable) throws Exception {
        //TO-DO
    }

    private void cmdAdd() throws Exception {
        int firstEl = memory.getValue(cpu.getSS() + cpu.getSP());
        int secondEl = memory.getValue(cpu.getSS() + cpu.getSP() - 1);
        int sum = firstEl + secondEl;
        memory.setValue(cpu.getSS() + cpu.getSP(), sum);

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
    }

    private void cmdSub() throws Exception {
        int firstEl = memory.getValue(cpu.getSS() + cpu.getSP());
        int secondEl = memory.getValue(cpu.getSS() + cpu.getSP() - 1);
        int diff = firstEl - secondEl;
        memory.setValue(cpu.getSS() + cpu.getSP(), diff);

        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
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
    }

    private void cmdStop() throws Exception {
        //TO-DO
        short nextCmdAddr = (short) (cpu.getIP() + 1);
        cpu.setIP(nextCmdAddr);
    }

    private String intToBits(int a) {
        String bits = Integer.toBinaryString(a);

        int length = bits.length();
        int diffLength = 32 - length;
        for (int i = 0; i < diffLength; i++) {
            bits = bits + "0";
        }
        return bits;
    }

    private void waitForInput() throws IOException {
        System.in.read();
    }
}

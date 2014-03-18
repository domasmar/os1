package os1.Interpreter;

import os1.Interpreter.*;
import os1.Memory.*;
import os1.CPU.*;

/**
 *
 * @author Arturas
 */
public class CommandExecution {

    private CPU cpu;
    private VMMemory memory;

    CommandExecution(CPU cpu, VMMemory virtualMemory) {
        this.cpu = cpu;
        this.memory = virtualMemory;
    }

    public void execute(CmdWithVar[] cmdWithVar) {
        for (int i = 0; i < cmdWithVar.length; i++) {
            if (cmdWithVar[i].command == Command.MOV_AX) {
                cmdMovAx(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.MOV_BX) {
                cmdMovBx(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.LOA_AX) {
                cmdLoaAx(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.LOA_BX) {
                cmdLoaBx(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.OUTR_AX) {
                cmdOutrAx(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.OUTR_BX) {
                cmdOutrBx(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.JA) {
                cmdJa(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.JB) {
                cmdJb(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.JE) {
                cmdJe(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.JNE) {
                cmdJne(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.ADD) {
                cmdAdd();
            }
            if (cmdWithVar[i].command == Command.CMP) {
                cmdCmp();
            }
            if (cmdWithVar[i].command == Command.OUTM) {
                cmdOutM(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.POP) {
                cmdPop(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.PUSH) {
                cmdPush(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.STO_AX) {
                cmdStoAx(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.STO_BX) {
                cmdStoBx(cmdWithVar[i].variable);
            }
            if (cmdWithVar[i].command == Command.SUB) {
                cmdSub();
            }
            if (cmdWithVar[i].command == Command.STOP) {
                cmdStop();
            }
        }
    }

    private void cmdMovAx(int variable) {
        cpu.setAX(variable);

    }

    private void cmdMovBx(int variable) {
        cpu.setBX(variable);

    }

    private void cmdLoaAx(int variable) {
        cpu.setAX(memory.getValue(variable));

    }

    private void cmdLoaBx(int variable) {
        cpu.setBX(memory.getValue(variable));

    }

    private void cmdStoAx(int variable) {
        memory.setValue(variable, cpu.getAX());

    }

    private void cmdStoBx(int variable) {
        memory.setValue(variable, cpu.getBX());

    }

    private void cmdPush(int variable) {


    }

    private void cmdPop(int variable) {
       

    }

    private void cmdJa(int variable) {

        
    }

    private void cmdJb(int variable) {

    }

    private void cmdJe(int variable) {

    }

    private void cmdJne(int variable) {

    }

    private void cmdOutrAx(int variable) {

    }

    private void cmdOutrBx(int variable) {

    }

    private void cmdOutM(int variable) {

    }

    private void cmdAdd() {

    }

    private void cmdSub() {

    }

    private void cmdCmp() {

    }

    private void cmdStop() {

    }
}

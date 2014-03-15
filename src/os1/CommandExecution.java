package os1;

import os1.Interpreter.*;

/**
 *
 * @author Arturas
 */
public class CommandExecution {

    CommandExecution(/*paduoti VM atmintį, procesorių ir padaryti juos šitos klasės globaliais kintamaisiais*/) {

    }

    public void execute(CmdWithVar[] cmdWithVar) {
        for (int i = 0; i < cmdWithVar.length; i++) {
            if (cmdWithVar[i].command == Command.MOV_AX) {
                cmdMovAx();
            }
            if (cmdWithVar[i].command == Command.MOV_BX) {
                cmdMovBx();
            }
            if (cmdWithVar[i].command == Command.LOA_AX) {
                cmdLoaAx();
            }
            if (cmdWithVar[i].command == Command.LOA_BX) {
                cmdLoaBx();
            }
            if (cmdWithVar[i].command == Command.OUTR_AX) {
                cmdOutrAx();
            }
            if (cmdWithVar[i].command == Command.OUTR_BX) {
                cmdOutrBx();
            }
            if (cmdWithVar[i].command == Command.JA) {
                cmdJa();
            }
            if (cmdWithVar[i].command == Command.JB) {
                cmdJb();
            }
            if (cmdWithVar[i].command == Command.JE) {
                cmdJe();
            }
            if (cmdWithVar[i].command == Command.JNE) {
                cmdJne();
            }
            if (cmdWithVar[i].command == Command.ADD) {
                cmdAdd();
            }
            if (cmdWithVar[i].command == Command.CMP) {
                cmdCmp();
            }
            if (cmdWithVar[i].command == Command.OUTM) {
                cmdOutM();
            }
            if (cmdWithVar[i].command == Command.POP) {
                cmdPop();
            }
            if (cmdWithVar[i].command == Command.PUSH) {
                cmdPush();
            }
            if (cmdWithVar[i].command == Command.STO_AX) {
                cmdStoAx();
            }
            if (cmdWithVar[i].command == Command.STO_BX) {
                cmdStoBx();
            }
            if (cmdWithVar[i].command == Command.SUB) {
                cmdSub();
            }
            if (cmdWithVar[i].command == Command.STOP) {
                cmdStop();
                i = cmdWithVar.length;
            }
        }
    }

    private void cmdMovAx() {

    }

    private void cmdMovBx() {

    }

    private void cmdLoaAx() {

    }

    private void cmdLoaBx() {

    }

    private void cmdStoAx() {

    }

    private void cmdStoBx() {

    }

    private void cmdPush() {

    }

    private void cmdPop() {

    }

    private void cmdAdd() {

    }

    private void cmdSub() {

    }

    private void cmdCmp() {

    }

    private void cmdJa() {

    }

    private void cmdJb() {

    }

    private void cmdJe() {

    }

    private void cmdJne() {

    }

    private void cmdOutrAx() {

    }

    private void cmdOutrBx() {

    }

    private void cmdOutM() {

    }

    private void cmdStop() {

    }
}

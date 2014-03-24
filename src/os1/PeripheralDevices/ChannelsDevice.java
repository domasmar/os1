package os1.PeripheralDevices;

import java.util.ArrayList;

import os1.CPU.CPU;

public class ChannelsDevice {

    private CPU cpu;

    public ChannelsDevice(CPU cpu) {
        this.cpu = cpu;
    }

    public boolean isChannelAvailable(byte number) {
        if (number == 0) {
            if (this.cpu.getCHST0()) {
                return true;
            } else {
                return false;
            }
        } else if (number == 1) {
            if (this.cpu.getCHST1()) {
                return true;
            } else {
                return false;
            }
        } else if (number == 2) {
            if (this.cpu.getCHST2()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void print(int data) {
        if (!isChannelAvailable((byte) 1)) {
            OutputDevice.print(data);
        }
    }

    public void stickInputDevice(String path, HDD hdd) throws Exception {
        boolean exceptionCaught = false;
        ArrayList<SourceFile> sfs = new ArrayList<SourceFile>();
        InputDevice id = new InputDevice(path);
        if (!isChannelAvailable((byte) 0)) {
            this.cpu.setCHST0(false);
            sfs = id.getData();
            this.cpu.setCHST0(true);
        }
        if (!isChannelAvailable((byte) 2)) {
            this.cpu.setCHST2(false);
            for (int i = 0; i < sfs.size(); i++) {
                try {
                    hdd.store(sfs.get(i));
                } catch (Exception e) {
                    if (e.getMessage().equals("Failas su tokiu pat pavadinimu!")) {
                        exceptionCaught = true;
                    }
                }
            }
            this.cpu.setCHST2(true);
            if (exceptionCaught) {
                throw new Exception("Failas(-ai) su tokiu pačiu pavadinimu jau egzistuoja. Jis(-ie) buvo perrašytas.");
            }
        }
    }
}

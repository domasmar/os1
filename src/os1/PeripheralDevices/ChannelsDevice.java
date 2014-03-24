package os1.PeripheralDevices;

import java.util.ArrayList;

import os1.CPU.CPU;

public class ChannelsDevice {
	
	private CPU cpu;
	
	public ChannelsDevice(CPU cpu) {
		this.cpu = cpu;
	}	
		
	public boolean isChannelAvailable(byte number) {
		if (number == 0) 
			if (this.cpu.getCHST0())
				return true;
			else
				return false;
		else if (number == 1)
			if (this.cpu.getCHST1())
				return true;
			else
				return false;
		else if (number == 2)
			if (this.cpu.getCHST2())
				return true;
			else
				return false;
		return false;
	}
	
	public void print(int data) {
		if (!isChannelAvailable((byte) 1)) {
			OutputDevice.print(data);
		 }
	}
	
	public void stickInputDevice(String path, HDD hdd) throws Exception {
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
				hdd.store(sfs.get(i));
			}
			this.cpu.setCHST2(true);
		}
	}

}

package os1.PeripheralDevices;

import os1.CPU.CPU;

public class ChannelsDevice {
	
	private CPU cpu;
	private HDD hdd;
	
	public ChannelsDevice(CPU cpu, HDD hdd) {
		this.cpu = cpu;
		this.hdd = hdd;
	}
	
	public byte channelIsAvailable(byte channelNumber) {
		if (channelNumber == 0) {
			if (!this.cpu.getCHST0())
				return 0;
			else
				return 1;
		}
		if (channelNumber == 1) {
			if (!this.cpu.getCHST1())
				return 0;
			else
				return 1;
		}		
		if (channelNumber == 2) {
			if (!this.cpu.getCHST2())
				return 0;
			else
				return 1;
		}
		return 2;
	}

	public void storeData(SourceFile sf) throws Exception {
		this.cpu.setCHST0(false);
		this.hdd.store(sf);
		this.cpu.setCHST0(true);
		for (int i = 0; i <= this.hdd.getFilesList().length; i++) {
			System.out.println(this.hdd.getFilesList()[0]);
		}
	}

}

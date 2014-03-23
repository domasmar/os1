package os1.PeripheralDevices;

import os1.GUI.VMLogger;

public class OutputDevice {
	
	public void receiveData(int data) {
		VMLogger.newVMMessage(data + "");
	}

}

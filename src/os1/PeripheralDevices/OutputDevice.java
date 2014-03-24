package os1.PeripheralDevices;

import os1.GUI.VMLogger;

public class OutputDevice {

	public static void print(int data) {
		VMLogger.newVMMessage(data + "");
	}

}

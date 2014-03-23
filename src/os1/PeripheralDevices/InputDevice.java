package os1.PeripheralDevices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import os1.CPU.CPU;

public class InputDevice {
	
	private String path = ".";
	
	private File folder;
	private File[] listOfFiles;
	
	private String fileName;
	private String fileContents;
	
	private CPU cpu;
	private HDD hdd;
	
	private ChannelsDevice channelsDevice;
	
	public static String getFileContents(String fileName) {
		String contents = null;
		try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			contents = stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contents;
	}
	
	public InputDevice(String path, CPU cpu, HDD hdd) throws Exception {
		String fileName;
		this.cpu = cpu;
		this.hdd = hdd;
		this.path = path;
		this.folder = new File(path);
		this.channelsDevice = new ChannelsDevice(cpu, hdd);
		this.listOfFiles = folder.listFiles();
		
		for (int i = 0; i <= this.listOfFiles.length - 1; i++) {
			if (this.listOfFiles[i].isFile()) {
				fileName = this.listOfFiles[i].getName();
				if (fileName.endsWith(".ltu") || (fileName.endsWith(".LTU"))) {
					this.fileName = fileName;
					this.fileContents = getFileContents(fileName);
					SourceFile sf = new SourceFile(this.fileContents, this.fileName);
					if (channelsDevice.channelIsAvailable((byte) 0) == 0) {
						channelsDevice.storeData(sf);
					}
				}
			}
		}
	}

}

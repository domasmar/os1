package os1.PeripheralDevices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputDevice {
	
	private String path;
	
	public InputDevice(String path) {
		this.path = path;
	}
		
	public String getFileContents(String fileName) {
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
		} catch (IOException e) {}
		return contents;
	}
	
	public ArrayList<SourceFile> getData() throws Exception {
		File folder = new File(this.path);
		File[] listOfFiles = folder.listFiles();
		ArrayList<SourceFile> sfs = new ArrayList<SourceFile>();
		for (File file: listOfFiles) {
			if (file.isFile() && (file.getName().endsWith(".ltu") || file.getName().endsWith(".LTU"))) {
				sfs.add(new SourceFile(getFileContents(file.getAbsolutePath()), file.getName()));
			}
		}
		return sfs;
	}

}

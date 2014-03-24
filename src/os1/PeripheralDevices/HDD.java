package os1.PeripheralDevices;

import java.io.*;

/**
 *
 * @author Arturas
 */
public class HDD implements Serializable {

    private final int maxSize = 100;

    private SourceFile[] memory = new SourceFile[maxSize];

    public void store(SourceFile file) throws Exception {
        for (int i = 0; i < maxSize; i++) {
            if (memory[i] != null) {
                if (file.getFileName().equals(memory[i].getFileName())) {
                    memory[i] = file;
                    throw new Exception("File with this name already exists in HDD! It was overwritten.");
                }
            }
        }

        for (int i = 0; i < maxSize; i++) {
            if (memory[i] == null) {
                memory[i] = file;
                return;
            }
        }
        throw new Exception("Disk is full!");
    }

    public SourceFile get(String name) throws UnsupportedEncodingException, Exception {
        for (int i = 0; i < maxSize; i++) {
            if ((memory[i] != null) && (name.equals(memory[i].getFileName()))) {
                return memory[i];
            }
        }
        throw new Exception("File not found!");
    }

    public void delete(String name) throws UnsupportedEncodingException, Exception {
        for (int i = 0; i < maxSize; i++) {
            if ((memory[i] != null) && (memory[i].getFileName().equals(name))) {
                memory[i] = null;
                return;
            }
        }
        throw new Exception("File not found!");
    }

    public String[] getFilesList() throws UnsupportedEncodingException {
        String[] list = new String[maxSize];
        int j = 0;
        for (int i = 0; i < maxSize; i++) {
            if (memory[i] != null) {
                list[j] = memory[i].getFileName();
                j++;
            }
        }
        String[] shortenedList = new String[j];
        System.arraycopy(list, 0, shortenedList, 0, j);
        return shortenedList;
    }
}

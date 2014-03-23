package os1.PeripheralDevices;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 *
 * @author Arturas
 */
public class SourceFile {

    private int[] nameInts;
    private int[] contentInts;

    public SourceFile(String contents, String name) throws Exception {
        if (contents.length() <= 10048) {
            fileContentsToInt(contents);
        } else {
            throw new Exception("Failas per didelis");
        }
        if (name.length() <= 12) {
            fileNameToInt(name);
        } else {
            throw new Exception("Failo pavadinimas per ilgas");
        }
    }

    public String getFileContent() throws UnsupportedEncodingException {
        String content = "";
        byte[] bytesAscii = new byte[contentInts.length * 4];

        for (int i = 0; i < contentInts.length; i++) {
            System.arraycopy(ByteBuffer.allocate(4).putInt(contentInts[i]).array(), 0, bytesAscii, i * 4, 4);
        }
        content = new String(bytesAscii, "US-ASCII");
        return content;
    }

    public String getFileName() throws UnsupportedEncodingException {
        String nameString = "";
        byte[] bytesAscii = new byte[12];

        System.arraycopy(ByteBuffer.allocate(4).putInt(nameInts[0]).array(), 0, bytesAscii, 0, 4);
        System.arraycopy(ByteBuffer.allocate(4).putInt(nameInts[1]).array(), 0, bytesAscii, 4, 4);
        System.arraycopy(ByteBuffer.allocate(4).putInt(nameInts[2]).array(), 0, bytesAscii, 8, 4);

        nameString = new String(bytesAscii, "US-ASCII");
        nameString = nameString.trim();
        return nameString;
    }

    public void renameFile(String name) throws Exception {
        fileNameToInt(name);
    }

    public int getFileSize() {
        int size = 0;
        size = contentInts.length;
        return size;
    }

    public int[] getFileContentBytes() {
        return this.contentInts;
    }

    public int[] getFileNameBytes() {
        return this.nameInts;
    }

    private void fileContentsToInt(String contents) throws UnsupportedEncodingException {
        int sizeOfIntArray = contents.length() / 4;
        if (contents.length() % 4 != 0) {
            sizeOfIntArray++;
        }
        contentInts = new int[sizeOfIntArray];

        byte[] bytesOfContentAscii = contents.getBytes("US-ASCII");
        byte[] bytesOfContent = Arrays.copyOf(bytesOfContentAscii, sizeOfIntArray * 4);

        for (int i = 0; i < sizeOfIntArray; i++) {
            contentInts[i] = ByteBuffer.wrap(bytesOfContent, i * 4, 4).getInt();
        }
    }

    private void fileNameToInt(String stringOfName) throws UnsupportedEncodingException, Exception {
        nameInts = new int[3];

        if (stringOfName.length() <= 12) {
            byte[] bytesOfNameAscii = stringOfName.getBytes("US-ASCII");
            byte[] bytesOfName = Arrays.copyOf(bytesOfNameAscii, 12);

            for (int i = 0; i < 3; i++) {
                nameInts[i] = ByteBuffer.wrap(bytesOfName, i * 4, 4).getInt();
            }
        } else {
            throw new Exception("Failo vardas per ilgas!");
        }
    }
}

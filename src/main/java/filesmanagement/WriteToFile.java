package filesmanagement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {

    private static WriteToFile instance = new WriteToFile();

    public void WriteToFile(String fileName, String text)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(text);

        writer.close();
    }

    public void AppendToFile(String fileName, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.append(' ');
        writer.append(text);
        writer.close();
    }

    public static WriteToFile getInstance() {
        return instance;
    }
}

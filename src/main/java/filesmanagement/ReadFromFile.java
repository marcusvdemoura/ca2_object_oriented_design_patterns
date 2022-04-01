package filesmanagement;

import java.io.*;

public class ReadFromFile {

    private static ReadFromFile instance = new ReadFromFile();

    public String readFromFile(String file)
            throws IOException {
        String expected_value = "Hello, world!";

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String textRead = "";
        while( (line = reader.readLine() ) != null) {
            textRead+="\n"+line;
        }
        reader.close();

        return textRead;

    }

    public static ReadFromFile getInstance() {
        return instance;
    }
}

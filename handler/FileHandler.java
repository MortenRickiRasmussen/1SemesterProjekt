package handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Morten Ricki Rasmussen 
 */
public class FileHandler {
    private String path;
    private int numberOfLines;

    public FileHandler(String filePath) {
        path = filePath;
    }
    
    public ArrayList<String> openFile() throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);
        int numberOfLines = readLines();
        ArrayList<String> textData = new ArrayList();
        for (int i = 0; i < numberOfLines; i++) {
            textData.add(textReader.readLine());
        }
        textReader.close();
        return textData;
    }

    public int readLines() throws IOException {
        FileReader file_to_read = new FileReader(path);
        BufferedReader bf = new BufferedReader(file_to_read);

        String aLine;
        while ((aLine = bf.readLine()) != null) {
            numberOfLines++;
        }
        bf.close();
        return numberOfLines;
    }

}

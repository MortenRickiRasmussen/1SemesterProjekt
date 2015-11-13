package handler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author Jakob Ferdinandsen
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
    
    public void updateDBFile(String usr, String pwd, String url, String schema) throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter("src/rescources/DatabaseIndstillinger.txt");
        writer.println("//USERNAME");
        writer.println(usr);
        writer.println("//PASSWORD");
        writer.println(pwd);
        writer.println("//URL");
        writer.println(url);
        writer.println("//SCHEMA");
        writer.println(schema);
        writer.close();
    }

}

package ru.atom.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ResourceReader {
   private static ArrayList<String> lines = new ArrayList<>();

    public static List<String> readFromResource(String resourceName) throws IOException {

        InputStream inputStream = ResourceReader.class.getClassLoader().getResourceAsStream(resourceName);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        catch(IOException e){
            e.getMessage();
        }

        return lines;
    }
    public static int getSize(){
        return lines.size();
    }
    public static String getSizeToString(){
        String str;
        str = "" + lines.size();
        return str;
    }
}

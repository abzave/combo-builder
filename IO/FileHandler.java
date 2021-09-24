package IO;

import java.util.Scanner;
import java.io.File;

public class FileHandler {

    public static String read(String filename) {
        Scanner reader = null;
        StringBuilder fileContent = new StringBuilder("");

        try{
            File file = new File(filename);
            reader = new Scanner(file);

            while(reader.hasNextLine()) {
                fileContent.append(reader.nextLine());
            }
        } catch (Exception e) {
        } finally {
            if (reader != null){
                reader.close();
            }
        }

        return fileContent.toString();
    }

}
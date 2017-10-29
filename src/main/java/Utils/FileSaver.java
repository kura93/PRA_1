package Utils;

import java.io.*;
import java.util.*;

public class FileSaver {

    private PrintWriter fileWriter;
    public static FileSaver fileSaver;
    private HashMap<Integer,String> dataToSave;
    private String fileName;

    public static FileSaver getFileSaver() {
        if (fileSaver != null) {
            return fileSaver;
        } else {
            try {
                fileSaver = new FileSaver();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return fileSaver;
        }
    }

    private FileSaver() throws FileNotFoundException, UnsupportedEncodingException {
        this.fileName = "./sql-files/" + Math.random() + ".txt";
        this.fileWriter = new PrintWriter(this.fileName, "UTF-8");
        this.dataToSave = new HashMap<Integer, String>();
    }

    public void printToFile() {
        this.fileWriter.close();

        try {
            this.fileWriter = new PrintWriter(this.fileName, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        List<Integer> sortedKeys=new ArrayList(this.dataToSave.keySet());
        Collections.sort(sortedKeys);
        for ( Integer key : sortedKeys ) {
            fileWriter.println(key);
            fileWriter.println(dataToSave.get(key));

        }
        fileWriter.flush();
    }

    public void addToHashMap(int key, String sentence) {
        this.dataToSave.put(key, sentence);
    }

    public void showHashMapTexts() {
        List<Integer> sortedKeys=new ArrayList(this.dataToSave.keySet());
        Collections.sort(sortedKeys);
        for ( Integer key : sortedKeys ) {
            System.out.println(key);
            System.out.println(dataToSave.get(key));
        }
    }

    public void close() {
        this.printToFile();
        fileWriter.close();
    }

}


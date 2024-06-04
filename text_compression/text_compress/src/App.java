import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class App {
  public static void main(String[] args) {
    HashMap<String, Integer> wordMap = new HashMap<>();
    int wordCount = 1;
    
    try {
      File myFile = new File("C:\\Users\\berme\\OneDrive\\Desktop\\ACADEMY\\GH-Academy\\text_compression\\text_compress\\input.txt");
      Scanner myReader = new Scanner(myFile);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println(("An error occurred"));
      e.printStackTrace();
    }
  }
}
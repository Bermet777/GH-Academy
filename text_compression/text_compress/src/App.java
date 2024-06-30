import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

// read the file and saved the words in a separate file
public class App {
  public static void main(String[] args) {
    HashMap<String, Integer> wordMap = new HashMap<>();
    int wordCount = 1;

    try {
      File myFile = new File("C:\\Users\\berme\\OneDrive\\Desktop\\ACADEMY\\GH-Academy\\text_compression\\text_compress\\input.txt");
      Scanner myReader = new Scanner(myFile);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] words = data.split("\\W+");
        for (String word:words) {
          if (!word.isEmpty() && !wordMap.containsKey(word)) {
            wordMap.put(word, wordCount++);
          }
        }  
      }
      myReader.close();

      FileWriter myWriter = new FileWriter("C:\\Users\\berme\\OneDrive\\Desktop\\ACADEMY\\GH-Academy\\text_compression\\text_compress\\output.sc");
      for (HashMap.Entry<String, Integer> entry : wordMap.entrySet()) {
        myWriter.write(entry.getKey() + "\t" + entry.getValue() + "\n");
      }
      myWriter.close();

      compressFile(outputFilePath, compressedFilePath);
      System.out.println("File compressed");

      // Step 4: Decompress the file
      decompressFile(compressedFilePath, decompressedFilePath);
      System.out.println("File decompressed");

//Compressed file      

     FileInputStream fis = new FileInputStream("C:\\Users\\berme\\OneDrive\\Desktop\\ACADEMY\\GH-Academy\\text_compression\\text_compress\\output.sc");
      FileOutputStream fos = new FileOutputStream("C:\\Users\\berme\\OneDrive\\Desktop\\ACADEMY\\GH-Academy\\text_compression\\text_compress\\output.sc.gz");
      GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
      byte[] buffer = new byte[1024];
      int len;
      while((len=fis.read(buffer)) != -1){
        gzipOS.write(buffer, 0, len);
      }
      fis.close();
      gzipOS.close();
      fos.close();

      System.out.println("File compressed");

    } catch (FileNotFoundException e) {
      System.out.println(("An error occurred"));
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("An error occured while writing to the file");
      e.printStackTrace();
    }
  }
}
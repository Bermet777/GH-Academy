import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;


// read the file and saved the words in a separate file
public class App {
  public static void main(String[] args) {

    String inputFilePath = "C:\\Users\\berme\\OneDrive\\Desktop\\ACADEMY\\GH-Academy\\text_compression\\text_compress\\input.txt";
    String outputFilePath = "C:\\Users\\berme\\OneDrive\\Desktop\\ACADEMY\\GH-Academy\\text_compression\\text_compress\\output.sc";
    String compressedFilePath = "C:\\Users\\berme\\OneDrive\\Desktop\\ACADEMY\\GH-Academy\\text_compression\\text_compress\\output.sc.gz";
    String decompressedFilePath = "C:\\Users\\berme\\OneDrive\\Desktop\\ACADEMY\\GH-Academy\\text_compression\\text_compress\\readable.txt";

    HashMap<String, Integer> wordMap = new HashMap<>();
    int wordCount = 1;

    try {
      File myFile = new File(inputFilePath);
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

      FileWriter myWriter = new FileWriter(outputFilePath);
      for (HashMap.Entry<String, Integer> entry : wordMap.entrySet()) {
        myWriter.write(entry.getKey() + "\t" + entry.getValue() + "\n");
      }
      myWriter.close();

      //compressed file

      compressFile(outputFilePath, compressedFilePath);
      System.out.println("File compressed");

      // decompressed file
      decompressFile(compressedFilePath, decompressedFilePath);
      System.out.println("File decompressed");

    } catch (FileNotFoundException e) {
      System.out.println(("An error occurred"));
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("An error occured while writing to the file");
      e.printStackTrace();
    }
  }
// compress and decompress methods
  private static void compressFile(String inputFilePath, String compressedFilePath) throws IOException {
    FileInputStream fis = new FileInputStream(inputFilePath);
    FileOutputStream fos = new FileOutputStream(compressedFilePath);
    GZIPOutputStream gzipOS = new GZIPOutputStream(fos);
    byte[] buffer = new byte[1024];
    int len;
    while ((len = fis.read(buffer)) != -1) {
        gzipOS.write(buffer, 0, len);
    }
    fis.close();
    gzipOS.close();
    fos.close();
}


private static void decompressFile(String compressedFilePath, String decompressedFilePath) throws IOException {
  FileInputStream fis = new FileInputStream(compressedFilePath);
  GZIPInputStream gzipIS = new GZIPInputStream(fis);
  FileOutputStream fos = new FileOutputStream(decompressedFilePath);
  byte[] buffer = new byte[1024];
  int len;
  while ((len = gzipIS.read(buffer)) != -1) {
      fos.write(buffer, 0, len);
  }
  gzipIS.close();
  fis.close();
  fos.close();
}
}
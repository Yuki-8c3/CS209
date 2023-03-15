package week5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTypeParser {
  private static final int png[] = new int[] { 0x89, 0x50, 0x4e, 0x47};
  private static final int zip[] = new int[] { 0x50, 0x4b, 0x03, 0x04};
  private static final int cla[] = new int[] { 0xca, 0xfe ,0xba ,0xbe};

  public static void main(String[] args) throws IOException {
    String str = args[0];
    File file = new File("F:\\2023Spring\\CS209_main\\practice\\src\\week5\\"+str);
//    File file = new File("F:\\2023Spring\\CS209_main\\practice\\src\\week5\\3");
//    byte[] bytes = new byte[(int)file.length()];
    System.out.println("Filename:"+ str);
//    System.out.println("Filename:1");
    checkType(file);

  }

  public static void checkType(File filename) throws IOException {
    FileInputStream fis = new FileInputStream(filename);
    try{
      String[] types = new String[]{"png","zip or jar","class"};
      int pos = -1;
      System.out.printf("File Header(Hex): [");
      for (int i = 0; i < 4; i++) {
        int reader = fis.read();
        if(reader==png[i]) {
          pos = 0;
          System.out.printf("%2X",png[i]);
        } else if (reader == zip[i]) {
          pos = 1;
          System.out.printf("%2X",zip[i]);
        } else if (reader == cla[i]) {
          pos = 2;
          System.out.printf("%2X",cla[i]);
        } else {
          System.out.printf("%2X",reader);
        }
        if (i != 3) {
          System.out.printf(",");
        }
       }

      System.out.println("]");
      System.out.println("File Type: "+types[pos]);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      fis.close();
    }
  }
}

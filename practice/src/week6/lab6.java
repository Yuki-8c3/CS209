package week6;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class lab6 {

  public static void main(String[] args) {
    try{
      readZipFile("F:\\2023Spring\\CS209_main\\practice\\src\\week6\\src.zip");
//      readJarFile("F:\\2023Spring\\CS209_main\\practice\\src\\week6\\rt.jar");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void readZipFile(String zipFile) throws Exception{
    ZipFile zf = new ZipFile(zipFile); //read zipFile
    Enumeration<? extends ZipEntry> zes = zf.entries();
    int size = 0;
    ArrayList<String> files = new ArrayList<>();
    String pattern_io = ".*java/io.*";
    String pattern_nio = ".*java/nio.*";
    while(zes.hasMoreElements()) {
      ZipEntry ze = zes.nextElement();
      String name = ze.getName();
      if (Pattern.matches(pattern_io, name) || Pattern.matches(pattern_nio, name)) {
        size++;
        files.add(name);
      }
    }
    System.out.println("# of .java files in java.io/java.nio: " + size);
    for(Iterator it = files.iterator();it.hasNext();){
      System.out.println(it.next());
    }
  }

  public static void readJarFile(String jarFile) throws Exception {
    JarFile jf = new JarFile(jarFile);
    Enumeration<? extends JarEntry> jes = jf.entries();
    int size = 0;
    ArrayList<String> files = new ArrayList<>();
    String pattern_io = ".*java/io.*";
    String pattern_nio = ".*java/nio.*";
    while(jes.hasMoreElements()) {
      JarEntry ze = jes.nextElement();
      String name = ze.getName();
      if (Pattern.matches(pattern_io, name) || Pattern.matches(pattern_nio, name)) {
        size++;
        files.add(name);
      }
    }
    System.out.println("# of .class files in java.io/java.nio: " + size);
    for(Iterator it = files.iterator();it.hasNext();){
      System.out.println(it.next());
    }
  }
}

package week3;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class Practice3Answer {

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    int func = 10;
    do {
      System.out.println("Please input the function No:\n" +
          "1 - Get even numbers\n" +
          "2 - Get odd numbers\n" +
          "3 - Get prime numbers\n" +
          "4 - Get prime numbers that are bigger than 5\n" +
          "0 - Quit\n");
      func = sc.nextInt();
      Predicate<Integer> p;
      if(func == 1) {
        p = x -> x % 2 == 0;
      } else if(func == 2) {
        p = x -> x % 2 == 1;
      } else if(func == 3) {
        p = x -> {
          for(int i=2; i<x; i++) {
            if(x%i == 0) {
              return false;
            }
          }
          return true;
        };
      } else if(func == 4) {
        p = x -> {
          for(int i=2; i<x; i++) {
            if(x%i == 0) {
              return false;
            }
          }
          if (x > 5) return true;
          else return false;
        };
      } else {
        break;
      }
      System.out.println("Input size of the list:\n");
      int size = sc.nextInt();
      System.out.println("Input elements of the list:\n");

      ArrayList<Integer> array = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        int check = sc.nextInt();
        if (p.test(check) && !array.contains(check)){
          array.add(check);
        }
      }

      System.out.println("Filter results:\n");
      System.out.println(Arrays.toString(array.toArray()));
    }while (func != 0);

  }
}

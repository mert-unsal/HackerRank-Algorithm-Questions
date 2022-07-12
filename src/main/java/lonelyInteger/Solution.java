package lonelyInteger;

import java.io.*;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
  
  // Complete the lonelyinteger function below.
  static int lonelyinteger(int[] a) {
    int result = 0;
    for(int i=0;i<a.length;i++){
      result^=a[i];
    }
    return result;
  }
  
  private static final Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) throws IOException {
    File directory = new File("./");
    
    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    
    int[] a = new int[n];
    
    String[] aItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    
    for (int i = 0; i < n; i++) {
      int aItem = Integer.parseInt(aItems[i]);
      a[i] = aItem;
    }
    
    int result = lonelyinteger(a);
    
    
    scanner.close();
  }
}

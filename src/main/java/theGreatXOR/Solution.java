package theGreatXOR;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
  /**
   * Note: to achieve higher result than x, you must XOR x with something in ~x
   * while java taking complement of x, care all trailingZeros, result is not gonna be what we want
   * I write my own complement function
   */
  static long theGreatXor(long x) {
    StringBuilder stringBuilder = new StringBuilder();
    StringBuilder binaryStringOfX = new StringBuilder(Long.toBinaryString(x));
    binaryStringOfX.reverse().chars().forEach(chr->{
      if(chr=='0')stringBuilder.append("1");
      else if(chr=='1')stringBuilder.append("0");
    });
    return Long.parseLong(String.valueOf(stringBuilder.reverse()),2);
  }
  
  private static final Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) throws IOException {
//    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    
    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    
    for (int qItr = 0; qItr < q; qItr++) {
      long x = scanner.nextLong();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
      
      long result = theGreatXor(x);
      System.out.println(result);
//      bufferedWriter.write(String.valueOf(result));
//      bufferedWriter.newLine();
    }
    
//    bufferedWriter.close();
    
    scanner.close();
  }
}

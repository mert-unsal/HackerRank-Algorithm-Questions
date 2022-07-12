package flippingBits;

import jdk.jfr.Unsigned;

import java.io.IOException;
import java.util.Scanner;

public class Solution {
  /**
   Integer - Max range
   Signed: From −2,147,483,648 to 2,147,483,647, from −(2^31) to 2^31 – 1
   Unsigned: From 0 to 4,294,967,295 which equals 2^32 − 1
   
   Long - Max range
   Signed: From −9,223,372,036,854,775,808 to 9,223,372,036,854,775,807, from −(2^63) to 2^63 − 1
   Unsigned: From 0 to 18,446,744,073,709,551,615 which equals 2^64 – 1
   */
  
  // Complete the flippingBits function below.
  static long flippingBits(long n) {
    StringBuilder stringBuilder = new StringBuilder();
    int length = Long.toBinaryString(n).length();
    StringBuilder binaryStringOfN = new StringBuilder(Long.toBinaryString(n));
    for(int i=0;i<32-length;i++){
      binaryStringOfN.insert(0, "0");
    }
    binaryStringOfN.reverse().chars().forEach(chr->{
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
      long n = scanner.nextLong();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
      
      long result = flippingBits(n);
      System.out.println(result);
//      bufferedWriter.write(String.valueOf(result));
//      bufferedWriter.newLine();
    }
    
//    bufferedWriter.close();
    
    scanner.close();
  }
}

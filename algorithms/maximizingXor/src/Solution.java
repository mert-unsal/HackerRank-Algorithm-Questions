import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
  
  // Complete the maximizingXor function below.
  static int maximizingXor(int l, int r) {
    int xored  = l ^ r;
    int significantBit = 31 - Integer.numberOfLeadingZeros(xored);
    int result = (1 << (significantBit + 1)) - 1;
    return result;
  }
  
  private static final Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) {
//    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    
    int l = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    
    int r = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    
    int result = maximizingXor(l, r);
    System.out.println(result);
    
//    bufferedWriter.write(String.valueOf(result));
//    bufferedWriter.newLine();
//
//    bufferedWriter.close();
    
    scanner.close();
  }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
  /**
   * There are 2 important note about this question
   *
   * To calculate to A[i] where A[i] = A[i-1] XOR i , i>0 and A[0]=0;
   * There is a pattern
   *  for i value,
   *    i % 4  == 0, then A[i]=i;
   *    i % 4  == 1, then A[i]=1;
   *    i % 4  == 2, then A[i]=i+1;
   *    i % 4  == 3, then A[i]=0;
   *
   *
   *
   * Another important note is directly about answer of this question,
   * To calculate to all numbers in array till A[i] inclusive A[i],  where A[i] = A[i-1] XOR i , i>0 and A[0]=0;
   *    A[0]+A[1]+...+A[i] = SUM
   *     There is a pattern for this SUM
   *      for i value,
   *        i % 8  == 0, then A[i]=i;
   *        i % 8  == 1, then A[i]=i;
   *        i % 8  == 2, then A[i]=2;
   *        i % 8  == 3, then A[i]=2;
   *        i % 8  == 4, then A[i]=i+2;
   *        i % 8  == 5, then A[i]=i+2;
   *        i % 8  == 6, then A[i]=0;
   *        i % 8  == 7, then A[i]=0;
   */
  
  static long calculateSum(long n){
    switch ((int) (n%8)){
      case 0:
      case 1:
        return n;
      case 2:
      case 3:
        return 2;
      case 4:
      case 5:
        return n+2;
      case 6:
      case 7:
        return 0;
      default:
        return 999;
    }
  }
  
  // Complete the xorSequence function below.
  static long xorSequence(long l, long r) {
    return calculateSum(l-1) ^ calculateSum(r);
  }
  
  private static final Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) throws IOException {
//    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    
    int q = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    
    for (int qItr = 0; qItr < q; qItr++) {
      String[] lr = scanner.nextLine().split(" ");
      
      long l = Long.parseLong(lr[0]);
      
      long r = Long.parseLong(lr[1]);
      
      long result = xorSequence(l, r);
      System.out.println(result);
//      bufferedWriter.write(String.valueOf(result));
//      bufferedWriter.newLine();
    }
    
//    bufferedWriter.close();
    
    scanner.close();
  }
}

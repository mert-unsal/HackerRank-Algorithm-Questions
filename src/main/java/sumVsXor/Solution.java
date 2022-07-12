package sumVsXor;

import java.io.*;

class Solution {
  
  // Complete the sumXor function below.
  static long sumXor(long n) {
    return n==0 ? 1 : (long) Math.pow(2, Long.toBinaryString(n).replaceAll("1","").length());
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    long n = Long.parseLong(bufferedReader.readLine().trim());
    
    long result = sumXor(n);
    
//    bufferedWriter.write(String.valueOf(result));
//    bufferedWriter.newLine();
    
    bufferedReader.close();
//    bufferedWriter.close();
  }
}

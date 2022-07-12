package xorMatrix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class XorMatrixSolution {
  
  static void xorMatrixDebugging(long m, int[] first_row) {
    int n = first_row.length;
    m = m - 1;
    for (int numberOfGeneratedRows = 1; numberOfGeneratedRows < m + 1; numberOfGeneratedRows++) {
      int[] last_row = new int[n];
      System.out.println("\t Iteration : " + numberOfGeneratedRows + "\t");
      for (int i = 0; i < n; i++) {
        if (i == n - 1) {
          last_row[i] = first_row[i] ^ first_row[0];
        } else {
          last_row[i] = first_row[i] ^ first_row[i + 1];
        }
        System.out.print("\t" + last_row[i] + "\t");
      }
      first_row = last_row.clone();
      System.out.print("\n");
    }
  }
  
  static int[] xorMatrix(BigInteger m, int[] first_row) {
    m=m.subtract(BigInteger.valueOf(1));
    int n = first_row.length;
    int[] last_row = new int[n];
    int[] base_row = first_row.clone();
    int[] binaryStreamArray = new StringBuilder(m.toString(2)).reverse().chars().toArray();
    for (long k = 0; k < binaryStreamArray.length; k++) {
      if (binaryStreamArray[(int) k] == 49) {
        for (int i = 0; i < n; i++) last_row[i] = base_row[i] ^ base_row[(int) ((i + (Math.pow(2, k))) % n)];
        base_row = last_row.clone();
      }
    }
    return last_row;
  }

  private static final Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) throws IOException {
    File directory = new File("./");
    File file = new File(directory.getAbsolutePath().replace(".", "") + "/testCases/xorMatrixTestCase.txt");
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(directory.getAbsolutePath().replace(".", "") + "/testCases/xorMatrixTestCaseOutput.txt")));
    Scanner scannerForFile = new Scanner(file, StandardCharsets.UTF_8.name());
    
    
    String[] nm = scannerForFile.nextLine().split(" ");
    BigInteger n = new BigInteger(nm[0].trim());
    BigInteger m = new BigInteger(nm[1].trim());

    int[] first_row = new int[n.intValue()];
    
    String[] first_rowItems = scannerForFile.nextLine().split(" ");
    
    for (int first_rowItr = 0; first_rowItr < n.intValue(); first_rowItr++) {
      int first_rowItem = Integer.parseInt(first_rowItems[first_rowItr].trim());
      first_row[first_rowItr] = first_rowItem;
    }
    xorMatrixDebugging(m.intValue(),first_row);
    int[] last_row = xorMatrix(m, first_row);
    
    for (int resultItr = 0; resultItr < last_row.length; resultItr++) {
      bufferedWriter.write(String.valueOf(last_row[resultItr]));
      
      if (resultItr != last_row.length - 1) {
        bufferedWriter.write(" ");
      }
    }
    
    bufferedWriter.newLine();
    
    bufferedWriter.close();
  }
}

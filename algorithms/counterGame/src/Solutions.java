import java.io.IOException;
import java.util.Scanner;

class Solution {
  static boolean isItPowerOfTwo(long n) {
    return n > 0 && ((n & (n - 1)) == 0);
  }
  
  // Complete the counterGame function below.
  static String counterGame(long n) {
    long numberOfMovementToAchievePowerOfTwo=0;
    long bitLengthOfPowerOfTwoNumber=0;
    while(true){
      if(isItPowerOfTwo(n)){
        break;
      } else {
        numberOfMovementToAchievePowerOfTwo++;
        n= n - ((long) 1 << ( 63 - Long.numberOfLeadingZeros(n)));
      }
    }
    bitLengthOfPowerOfTwoNumber= Long.toBinaryString(n).length()-1;
    return ((bitLengthOfPowerOfTwoNumber+numberOfMovementToAchievePowerOfTwo) % 2 == 1) ? "Louise" : "Richard";
//    long richardPoints = 0;
//    long louisePoints = 0;
//    boolean isItLouiseTurn = true;
//    boolean isItAPowerOfTwo=false;
//    while(richardPoints!=1 && louisePoints!=1){
//      long nValueAfterMove = 0;
//      if(isItLouiseTurn){
//        if(louisePoints==0){
//          // This means initial movement
//          isItAPowerOfTwo = isItPowerOfTwo(n);
//          if(isItAPowerOfTwo){
//            nValueAfterMove=n/2;
//          } else
//          {
//            nValueAfterMove = 1 << ( 63 - Long.numberOfLeadingZeros(n));
//          }
//          if(nValueAfterMove==0){
//            return "Richard";
//          }
//          richardPoints=n-nValueAfterMove;
//        } else {
//          isItAPowerOfTwo = isItPowerOfTwo(louisePoints);
//          if(isItAPowerOfTwo){
//            nValueAfterMove=louisePoints/2;
//          } else
//          {
//            nValueAfterMove = 1 << ( 63 - Long.numberOfLeadingZeros(louisePoints));
//          }
//          richardPoints=louisePoints - nValueAfterMove;
//        }
//      } else {
//        isItAPowerOfTwo = isItPowerOfTwo(richardPoints);
//        if(isItAPowerOfTwo){
//          nValueAfterMove=richardPoints/2;
//        } else
//        {
//          nValueAfterMove = 1 << ( 63 - Long.numberOfLeadingZeros(richardPoints));
//        }
//        louisePoints=richardPoints-nValueAfterMove;
//      }
//      if(richardPoints==0)return "Richard";
//      isItLouiseTurn=!isItLouiseTurn;
//    }
//    return richardPoints == 1 ? "Louise":"Richard";
  }
  
  private static final Scanner scanner = new Scanner(System.in);
  
  public static void main(String[] args) throws IOException {
//    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    
    int t = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
    
    for (int tItr = 0; tItr < t; tItr++) {
      long n = scanner.nextLong();
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
      
      String result = counterGame(n);
      System.out.println(result);
      
//      bufferedWriter.write(result);
//      bufferedWriter.newLine();
    }
    
//    bufferedWriter.close();
    
    scanner.close();
  }
}

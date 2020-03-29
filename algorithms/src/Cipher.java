import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

public class Cipher {

    // Complete the cipher function below.
    static String cipher(int k, String s) {
        ArrayDeque<Integer> charactersXOROperationHasBeenProcessed = new ArrayDeque<>();
        StringBuilder stringBuilder = new StringBuilder();
        int intValueOfDeCipheredCharacter = 0;
        int exOrSumOfLastKCharacters = 0;
        int intValueOfChar=0;
        for(int i=0;i<s.length()-k+1;i++){
            intValueOfChar = Integer.parseInt(String.valueOf(s.charAt(i)),2);
            intValueOfDeCipheredCharacter = exOrSumOfLastKCharacters ^ intValueOfChar;
            stringBuilder.append(String.valueOf(intValueOfDeCipheredCharacter));
            if (charactersXOROperationHasBeenProcessed.size() > k-2){
                exOrSumOfLastKCharacters ^= charactersXOROperationHasBeenProcessed.poll();
                charactersXOROperationHasBeenProcessed.addLast(intValueOfDeCipheredCharacter);
                exOrSumOfLastKCharacters ^= intValueOfDeCipheredCharacter;
            } else {
                charactersXOROperationHasBeenProcessed.addLast(intValueOfDeCipheredCharacter);
                exOrSumOfLastKCharacters ^= intValueOfDeCipheredCharacter;
            }
        }
        return stringBuilder.toString();
    }

    private static final Scanner scannerForCommandLine = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        File directory = new File("./");
        File file = new File(directory.getAbsolutePath().replace(".","")+ "/algorithms/src/testInput.txt");
        Scanner scannerForFile = new Scanner(file, StandardCharsets.UTF_8.name());

        String[] nk = scannerForCommandLine.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String s = scannerForCommandLine.nextLine();

        long startTime = Instant.now().toEpochMilli();
        String result = cipher(k, s);
        long endTime = Instant.now().toEpochMilli();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed);
        System.out.println(result);

        scannerForCommandLine.close();
    }
}

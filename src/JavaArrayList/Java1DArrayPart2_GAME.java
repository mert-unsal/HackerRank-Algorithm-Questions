package JavaArrayList;
//3
//        11 5
//        0 1 1 1 0 0 0 0 0 0 1
//        11 5
//        0 1 1 1 0 0 1 1 1 0 1
//        11 5
//        0 1 1 1 1 0 1 1 1 0 1
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Java1DArrayPart2_GAME {
    private static boolean isSolvable(int m, int[] arr, int i) {
        if (i < 0 || arr[i] == 1) return false;
        if ((i == arr.length - 1) || i + m > arr.length - 1) return true;

        arr[i] = 1;
        return isSolvable(m, arr, i + 1) || isSolvable(m, arr, i - 1) || isSolvable(m, arr, i + m);
    }

    static boolean canWin(int leap,int[] game){
//        return isSolvable(leap,game,0);
        int positionOfPlayer=0;
        while(positionOfPlayer+leap<game.length && positionOfPlayer!=game.length-1){
            if(game[positionOfPlayer+1]==0){
                positionOfPlayer=positionOfPlayer+1;
            } else if(leap > 1 && game[positionOfPlayer+leap]==0){
                positionOfPlayer+=leap;
            } else {
                // If leap == moveForward so going back does not make any sense.
                if(leap<=1)return false;
                int maxBackwardStep = leap-1;
                while(maxBackwardStep>0) {
                    // There is no way to go back!
                    if(positionOfPlayer==0)return false;
                    // First try to move backward
                    if(game[positionOfPlayer-1] == 0) {
                        positionOfPlayer-=1;
                    } else {
                        return false;
                    }
                    if(game[positionOfPlayer+leap]==0){
                        positionOfPlayer+=leap;
                        break;
                    }
                    maxBackwardStep--;
                    if(maxBackwardStep==0)return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws FileNotFoundException {
        File directory = new File("./");
        File file = new File(directory.getAbsolutePath().replace(".","")+ "src/JavaArrayList/testCase.txt");
        Scanner scan = new Scanner(file, StandardCharsets.UTF_8.name());
        long startTime = Instant.now().toEpochMilli();
        int q = scan.nextInt();
        int counter = 0;
        while (counter++ < q) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }
            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
        long endTime = Instant.now().toEpochMilli();

        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }
}

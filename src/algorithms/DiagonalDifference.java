package algorithms;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        AtomicInteger primaryDiagonal = new AtomicInteger();
        AtomicInteger secondaryDiagonal = new AtomicInteger();
        IntStream.range(0, arr.size()).forEach(index -> {
            primaryDiagonal.addAndGet(arr.get(index).get(index));
            secondaryDiagonal.addAndGet(arr.get(index).get(arr.size() - 1 - index));
        });
        return Math.abs(primaryDiagonal.get() - secondaryDiagonal.get());
    }

    static void plusMinus(int[] arr) {
        AtomicInteger numberOfNegativeNumbers = new AtomicInteger(0);
        AtomicInteger numberOfPositiveNumbers = new AtomicInteger(0);
        AtomicInteger numberOfZeros = new AtomicInteger(0);
        Arrays.stream(arr).forEach(element->{
            if(element<0)numberOfNegativeNumbers.incrementAndGet();
            if(element==0)numberOfZeros.incrementAndGet();
            if(element>0)numberOfPositiveNumbers.incrementAndGet();
        });
        System.out.printf("%.6f\n%.6f\n%.6f", (float)((float)numberOfPositiveNumbers.get()/(float)arr.length),
                (float)((float)numberOfNegativeNumbers.get()/(float)arr.length),
                (float)((float)numberOfZeros.get())/(float)arr.length);

    }
    static void staircase(int n) {
        String format = "%"+n+"s";
        for(int i=1;i<n+1;i++){
            String output = "";
            for(int y=0;y<i;y++){
                output+="#";
            }
            System.out.printf(format,output);
            if(i!=n) System.out.println();
        }
    }

}

public class DiagonalDifference {
    public static void main(String[] args) throws IOException {
//        int [] abc = { 1,2, 3, -1, -2, -3, 0, 0};
//        Result.plusMinus(abc);
        Result.staircase(6);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        int result = Result.diagonalDifference(arr);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
        System.out.println(result);
//        bufferedWriter.close();
    }
}

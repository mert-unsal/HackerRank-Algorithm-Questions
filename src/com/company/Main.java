package com.company;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;



public class Main {
    public static int moves(List<Integer> a) {
        int numberOfMovesForOdd=0;
        int numberOfMovesForEven=0;
        if(a.size() %2 == 0){
            for(int i=0;i<a.size();i++){
                if(i < a.size()/2){
                    if(a.get(i)%2 == 0)numberOfMovesForEven++;
                } else {
                    if(a.get(i)%2 == 1)numberOfMovesForOdd++;
                }
            }
        } else {
            for(int i=0;i<a.size();i++){
                if(i < (a.size()+1)/2){
                    if(a.get(i)%2 == 0)numberOfMovesForEven++;
                } else if(i > (a.size()+1)/2 ) {
                    if(a.get(i)%2 == 1)numberOfMovesForOdd++;
                }
            }
        }
        if(numberOfMovesForEven>=numberOfMovesForOdd){return numberOfMovesForEven;}else{return numberOfMovesForOdd;}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = IntStream.range(0, aCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = moves(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

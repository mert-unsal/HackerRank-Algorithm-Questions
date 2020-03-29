package subtitleManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class subtitleNonTurkishCharacterFixer {
    final static FilenameFilter subTitleFilter = new FilenameFilter(){
        public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".srt")) {
                return true;
            } else {
                return false;
            }
        }
    };

    final static Map<String,String> characterSet = Map.of("ý", "i", "ð", "g", "þ", "ş");

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input fullPath for directory which contains Subtitles (Empty means default)");
        String pathDirectory = scanner.nextLine();
        if(pathDirectory.equals(""))pathDirectory="/Users/mert/IdeaProjects/MertDeneme/src/subtitleManager/s11/";
        File directoryPath = new File(pathDirectory);
        String[] subtitleFiles = directoryPath.list(subTitleFilter);
        fixNonTurkishCharacters(directoryPath,subtitleFiles);
    }
    private static void fixNonTurkishCharacters(File directoryPath,String[] pathList) {

        try {
            Arrays.stream(pathList).forEach(path->{
                File file = new File(directoryPath.getAbsolutePath()+"/"+path);
                BufferedReader bufferedReader = null;
                try {
                    bufferedReader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(file), StandardCharsets.US_ASCII));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String line = null;
                BufferedWriter bufferedWriter=null;
                File fileWrite = new File("output_" + path);
                FileWriter fileWriter = null; // A stream that connects to the text file
                try {
                    fileWriter = new FileWriter(fileWrite);
                    bufferedWriter = new BufferedWriter(fileWriter); // Connect the FileWriter to the BufferedWriter
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true) {
                    try {
                        if ((line = bufferedReader.readLine()) == null) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String newLine = checkAndFixNonTurkishCharacters(line);
                    System.out.println(newLine); // Display the file's contents on the screen, one line at a time
                    assert bufferedWriter != null;
                    try {
                        bufferedWriter.write(newLine + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    bufferedWriter.close (); // Close the stream
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String checkAndFixNonTurkishCharacters(String line) {
        String newLine = line;
        for(int index=0;index<line.length();index++){
            if(characterSet.get(String.valueOf(line.charAt(index))) != null){
                newLine=newLine.substring(0,index)+ characterSet.get(String.valueOf(line.charAt(index))) +newLine.substring(index+1);
            }
        }
        return newLine;
    }
}



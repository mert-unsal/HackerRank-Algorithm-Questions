package JavaArrayList;
import java.util.*;
import java.util.stream.Collectors;

public class ArrayListSolution {

    private static void executeQueries(List<List<Integer>> listQueryContainer, List<List<Integer>> listContainer){
        for (List<Integer> query:
                listQueryContainer) {
            try{
                int elementPositionInsideArray = query.get(1)-1;
                int arrayPositionInsideContainer = query.get(0)-1;
                System.out.println(listContainer.get(arrayPositionInsideContainer).get(elementPositionInsideArray));
            }catch (Exception ex){
                System.out.println("ERROR!");
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> listContainer = new ArrayList<>();
        List<List<Integer>> listQueryContainer = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int numberOfGivenArrays = Integer.parseInt(in.nextLine());
        while (numberOfGivenArrays > 0) {
            List<Integer> list = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            listContainer.add(list.subList(1, list.get(0) + 1));
            numberOfGivenArrays--;
        }
        int numberOfQueries = Integer.parseInt(in.nextLine());
        while (numberOfQueries > 0) {
            List<Integer> list = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            listQueryContainer.add(list);
            numberOfQueries--;
        }
        executeQueries(listQueryContainer,listContainer);
    }
}
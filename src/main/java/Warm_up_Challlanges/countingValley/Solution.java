package Warm_up_Challlanges.countingValley;

import java.io.*;
import java.util.Objects;

class Result {

    public enum HikeStepType {
        HIKE_UP(1),
        HIKE_DOWN(-1);
        private Integer hikeStepValue;
        private HikeStepType(Integer value) {
            this.hikeStepValue = value;
        }
        public Integer getHikeStepValue() {
            return this.hikeStepValue;
        }
    }

    public enum HikeMovementType {
        SEA_LEVEL("sea_level"),
        MOUNTAIN("mountain"),
        VALLEY("valley");

        private String hikeMovement;
        private HikeMovementType(String value) {
            this.hikeMovement = value;
        }
        public boolean isMovementValley() {
            return Objects.equals(this.hikeMovement,"valley");
        }
    }


    public static int countingValleys(int steps, String path) {
        HikeStepType hikeStepType;
        HikeMovementType hikeMovement = HikeMovementType.SEA_LEVEL;
        Integer numberOfValley = 0;
        Integer sumOfHikes = 0;
        for (int i = 0; i < steps; i++) {
            hikeStepType = Objects.equals(String.valueOf(path.charAt(i)),"U")
                    ? HikeStepType.HIKE_UP
                    : HikeStepType.HIKE_DOWN;

            if(hikeMovement.equals(HikeMovementType.SEA_LEVEL)) {
                hikeMovement = hikeStepType.hikeStepValue > 0
                        ? HikeMovementType.MOUNTAIN :
                        HikeMovementType.VALLEY;
            }

            sumOfHikes += hikeStepType.getHikeStepValue();

            if(sumOfHikes==0) {
                switch(hikeMovement) {
                    case VALLEY:
                        numberOfValley++;
                        hikeMovement = HikeMovementType.SEA_LEVEL;
                        break;
                    case MOUNTAIN:
                    case SEA_LEVEL:
                    default:
                        hikeMovement = HikeMovementType.SEA_LEVEL;
                }
            }

        }
        return numberOfValley;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = Result.countingValleys(steps, path);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

/**
 * This class runs the applications
 * You need to specify what the exercise file name is
 * Created by ehsan on 4/29/15.
 */
public class ExerciseMain {
    public static void main(String args[]){

        ExerciseFactory exerciseFactory = new ExerciseFactory("JohnDoe");
        ExerciseController.printBestExerciseDays();
        ExerciseController.printWorstExerciseDays();
        ExerciseController.printBestStreak();
        ExerciseController.printWorseStreak();
    }

}

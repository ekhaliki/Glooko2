import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class parses the file and calls the controller's
 * methods to populate the exercise list
 * Created by ehsan on 4/28/15.
 */
public class ExerciseFactory {

    /**
     * constructor
     * @param fileName the filename we are parsing
     */
    public ExerciseFactory(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String exercise = scan.nextLine();
                ExerciseModel exerciseModel = new ExerciseModel(parseDuration(exercise), parseTime(exercise));
                ExerciseController.addExerciseModel(exerciseModel);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * parses the duration from the exercise string
     * @param exercise the string we are parsing
     * @return duration
     */
    int parseDuration(String exercise){
        int startIndex = exercise.indexOf("duration")+11;
        int endIndex = exercise.indexOf(",");
        return Integer.parseInt(exercise.substring(startIndex, endIndex));
    }

    /**
     * parses the timestamp
     * @param exercise the string we are parsing
     * @return the timestamp
     */
    String parseTime(String exercise){
        int startIndex = exercise.lastIndexOf('‘')+1;
        int endIndex = exercise.lastIndexOf('’');
        return exercise.substring(startIndex, endIndex);
    }
}

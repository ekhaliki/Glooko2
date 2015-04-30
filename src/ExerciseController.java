import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * This is a controller class which operates on the
 * exercise data.
 * Created by ehsan on 4/28/15.
 */
public class ExerciseController {
    //the constants you can set to view different statistics
    private static final int STREAK_LENGTH = 3;
    private static final int NUMBER_OF_BEST_DAYS = 4;
    private static final int NUMBER_OF_WORST_DAYS = 3;

    //some private instance variables needed for printing statistics
    private static TreeSet<ExerciseModel> topExerciseDays = new TreeSet<>(new DurationComp());
    private static ArrayList<ExerciseModel> bestStreak = new ArrayList<>();
    private static ArrayList<ExerciseModel> worstStreak = new ArrayList<>();
    private static Date previousDate;
    private static ArrayList<ExerciseModel> currBestStreak = new ArrayList<>();
    private static int currentBest, currentWorst=0;
    private static ExerciseModel previousExerciseModel;

    /**
     * adds an exercise model to the list of exercises
     * @param exerciseModel the exercise model we are adding
     */
    public static void addExerciseModel(ExerciseModel exerciseModel) {
        topExerciseDays.add(exerciseModel);
        checkStreak(exerciseModel);

    }

    /**
     * checks and sets the current best and worst exercise strings.
     * @param exerciseModel the exercise model we are checking
     */
    private static void checkStreak(ExerciseModel exerciseModel) {
        Date currentDate = exerciseModel.getDate();
        if(previousDate!=null) {
            if(nextDayDate(previousDate).equals(currentDate)) {
                currentBest++;
                currBestStreak.add(exerciseModel);
                if (currentBest > bestStreak.size()) {
                    bestStreak = new ArrayList<>(currBestStreak);
                }
            } else {
            currentBest = 1;
            currBestStreak = new ArrayList<>();
            currBestStreak.add(exerciseModel);
            }
            //checking breaktime here
            int days = Days.daysBetween(new DateTime(previousDate), new DateTime(currentDate)).getDays();
            if(currentWorst < days){
                currentWorst = days;
                worstStreak = new ArrayList<>();
                worstStreak.add(previousExerciseModel);
                worstStreak.add(exerciseModel);
            }
        } else {
            currentBest = 1;
            currBestStreak = new ArrayList<>();
            currBestStreak.add(exerciseModel);
        }

        //setting previous entry's data
        previousDate = currentDate;
        previousExerciseModel = exerciseModel;
    }

    /**
     * prints a number of best exercise days depending on the constant NUMBER_OF_BEST_DAYS
     */
    public static void printBestExerciseDays(){
        Iterator<ExerciseModel> it = ExerciseController.topExerciseDays.descendingIterator();
        int i=0;
        System.out.println("==================================================");
        System.out.println("Best exercise days: ");
        while(it.hasNext() && i!=NUMBER_OF_BEST_DAYS){
            ExerciseModel obj = it.next();
            System.out.println(obj);
            i++;
        }
        System.out.println("==================================================\n");
    }

    /**
     * prints a number of worse exercise days depending on the constant NUMBER_OF_WORST_DAYS
     */
    public static void printWorstExerciseDays(){
        Iterator<ExerciseModel> it = ExerciseController.topExerciseDays.iterator();
        int i=0;
        System.out.println("==================================================");
        System.out.println("Worst exercise days: ");
        while(it.hasNext() && i!=NUMBER_OF_WORST_DAYS){
            ExerciseModel obj = it.next();
            System.out.println(obj);
            i++;
        }
        System.out.println("==================================================\n");
    }

    /**
     * prints the longest exercise streak depending on the constant STREAK_LENGTH;
     */
    public static void printBestStreak(){
        System.out.println("==================================================");
        System.out.println("Best Exercise Streak:");
        if(bestStreak.size() < STREAK_LENGTH){
            System.out.println("You have not worked out in " + STREAK_LENGTH + " consecutive days!");
        } else {
            System.out.println("Your best streak is " + bestStreak.size() + " days for the following days.");
            bestStreak.forEach(System.out::println);
        }
        System.out.println("==================================================\n");
    }

    /**
     * prints the longest break time exceeding the limit set by STREAK_LENGTH
     */
    public static void printWorseStreak(){
        System.out.println("==================================================");
        System.out.println("Longest Exercise Break:");
        if(currentWorst<STREAK_LENGTH){
            System.out.println("You have been working out consistently, Congratulations!");
        } else {
            System.out.println("Your longest exercise break is " + currentWorst + " days between the following days:");
            worstStreak.forEach(System.out::println);        }
        System.out.println("==================================================\n");
    }

    /**
     * return the next days date
     * @param date the date we want to increment
     * @return next day's date
     */
    private static Date nextDayDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }

    /**
     * the class which implements Comparator to compare to
     * Exercise days by duration
     * @author Ehsan Khaliki
     *
     */
    private static class DurationComp implements Comparator<ExerciseModel> {
        @Override
        public int compare(ExerciseModel o1, ExerciseModel o2) {
            double growth1 = o1.getDuration();
            double growth2 = o2.getDuration();
            if (growth1 > growth2)
                return 1;
            if (growth1 < growth2)
                return -1;
            if(o1.getYear()==o2.getYear()) {
                if(o1.getMonth()==o2.getMonth()) {
                    if (o1.getDay() < o2.getDay()) {
                        return 1;
                    } else if (o1.getDay() > o2.getDay()) {
                        return -1;
                    } else {
                        return 0;
                    }
                } else if(o1.getMonth()<o2.getMonth()) {
                    return 1;
                } else {
                    return 1;
                }
            } else if(o1.getYear() < o2.getYear()){
                return 1;
            } else {
                return -1;
            }
        }
    }
}

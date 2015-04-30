import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a model for a single exercise reading
 * Created by ehsan on 4/28/15.
 */
public class ExerciseModel {

    private int duration;
    private String timeStamp;

    /**
     * constructor
     * @param duration the duration of the exercise
     * @param timeStamp the timestamp of the exercise
     */
    public ExerciseModel(int duration, String timeStamp){
        this.duration = duration;
        this.timeStamp = timeStamp;
    }

    /**
     * @return the day of timestamp
     */
    public int getDay(){
        return Integer.parseInt(timeStamp.substring(8, 10));
    }

    /**
     * @return the month of timestamp
     */
    public int getMonth(){
        return Integer.parseInt(timeStamp.substring(5, 7));
    }

    /**
     * @return the year of timestamp
     */
    public int getYear(){
        return Integer.parseInt(timeStamp.substring(0, 4));
    }

    /**
     * @return Date object from the timestamp
     */
    public Date getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        try {
            return dateFormat.parse(getYear()+"-"+getMonth()+"-"+getDay());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return get the duration
     */
    public int getDuration(){
        return duration;
    }

    @Override
    public String toString(){
        return "Duration: " + duration + ", Date: " + timeStamp;
    }

}

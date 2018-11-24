package hackjunction2018.c2c.hackthesauna.Model;

/**
 * Created by Corentin on 11/24/2018.
 */

public class Session {

    /**
     * INTERN STATE
     */
    public String date;
    public String averageTemperature;
    public String duration;
    public String burnedCalories;



    /** CONTRUCTORS **/
    public Session() {
    }

    public Session(String date, String averageTemperature, String duration, String burnedCalories) {
        this.date = date;
        this.averageTemperature = averageTemperature;
        this.duration = duration;
        this.burnedCalories = burnedCalories;
    }

    /**
     * GETTERS AND SETTERS
     */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(String averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(String burnedCalories) {
        this.burnedCalories = burnedCalories;
    }
}

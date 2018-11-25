package hackjunction2018.c2c.hackthesauna.Model;

import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by Cyril Niobé on 24/11/2018.
 */
public class SimpleSensor {

    private String name;
    private int mTimeStamp;
    private double mTemperature;
    private int mCarbon;

    public static final String TIMESTAMP = "Timestamp";
    public static final String MEASUREMENTS = "Measurements";
    public static final String TEMPERATURE = "Temperature";
    public static final String VALUE = "value";
    public static final String CARBON = "Carbon Dioxide concentration";

    public SimpleSensor() {
    }

    public SimpleSensor(JSONObject jsonObject, String name) {
        this.name = name;
        this.mTimeStamp = jsonObject.optInt(TIMESTAMP);
        this.mTemperature = jsonObject.optJSONObject(MEASUREMENTS).optJSONObject(TEMPERATURE).optDouble(VALUE);
        if (jsonObject.optJSONObject(MEASUREMENTS).optJSONObject(CARBON) != null) {
            this.mCarbon = jsonObject.optJSONObject(MEASUREMENTS).optJSONObject(CARBON).optInt(VALUE);
        } else {
            this.mCarbon = -1;
        }
    }

    public int getmTimeStamp() {
        return mTimeStamp;
    }

    public void setmTimeStamp(int mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public double getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(double mTemperature) {
        this.mTemperature = mTemperature;
    }

    public String getName() {
        return name;
    }

    public int getmCarbon() {
        return mCarbon;
    }


    @Override
    public String toString() {
        return "SimpleSensor{" +
                "name='" + name + '\'' +
                ", mTimeStamp=" + mTimeStamp +
                ", mTemperature=" + mTemperature +
                ", mCarbon=" + mCarbon +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleSensor that = (SimpleSensor) o;
        return mTimeStamp == that.mTimeStamp &&
                Double.compare(that.mTemperature, mTemperature) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, mTimeStamp, mTemperature);
    }
}


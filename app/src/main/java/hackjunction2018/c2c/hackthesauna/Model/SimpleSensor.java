package hackjunction2018.c2c.hackthesauna.Model;

import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by Cyril Niobé on 24/11/2018.
 */
public class SimpleSensor {
    private int mTimeStamp;
    private double mTemperature;

    public static final String TIMESTAMP = "Timestamp";
    public static final String MEASEUREMENTS = "Measurements";
    public static final String TEMPERATURE = "Temperature";
    public static final String VALUE = "value";

    public SimpleSensor(int mTimeStamp, double mTemperature, int mCarbonDioxideConcentration) {
        this.mTimeStamp = mTimeStamp;
        this.mTemperature = mTemperature;
    }

    public SimpleSensor() {
    }

    public SimpleSensor(JSONObject jsonObject) {
        this.mTimeStamp = jsonObject.optInt(TIMESTAMP);
        this.mTemperature = jsonObject.optJSONObject(MEASEUREMENTS).optJSONObject(TEMPERATURE).optDouble(VALUE);
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

    @Override
    public String toString() {
        return "BenchSimple{" +
                "mTimeStamp=" + mTimeStamp +
                ", mTemperature=" + mTemperature +
                '}';
    }

    public void updateBenchSimple(JSONObject jsonObject) {
        this.mTimeStamp = jsonObject.optInt(TIMESTAMP);
        this.mTemperature = jsonObject.optJSONObject(TEMPERATURE).optDouble(VALUE);
    }


}


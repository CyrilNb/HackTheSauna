package hackjunction2018.c2c.hackthesauna.Model;

import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by Cyril Niobé on 24/11/2018.
 */
public class HumiditySensor {

    private int mTimeStamp;
    private double mTemperature;
    private double mRelativeHumidity;


    public static final String TIMESTAMP = "Timestamp";
    public static final String MEASEUREMENTS = "Measurements";
    public static final String TEMPERATURE = "Temperature";
    public static final String RELATIVEHUMIDITY = "Relative humidity";
    public static final String VALUE = "value";

    public HumiditySensor(int mTimeStamp, double mTemperature, double mRelativeHumidity) {
        this.mTimeStamp = mTimeStamp;
        this.mTemperature = mTemperature;
        this.mRelativeHumidity = mRelativeHumidity;
    }

    public HumiditySensor() {
    }

    public HumiditySensor(JSONObject jsonObject) {
        this.mTimeStamp = jsonObject.optInt(TIMESTAMP);
        this.mTemperature = jsonObject.optJSONObject(MEASEUREMENTS).optJSONObject(TEMPERATURE).optDouble(VALUE);
        this.mRelativeHumidity = jsonObject.optJSONObject(MEASEUREMENTS).optJSONObject(RELATIVEHUMIDITY).optInt(VALUE);
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

    public double getmRelativeHumidity() {
        return mRelativeHumidity;
    }

    public void setmRelativeHumidity(double mRelativeHumidity) {
        this.mRelativeHumidity = mRelativeHumidity;
    }

    public void updateBench2(JSONObject jsonObject) {
        this.mTimeStamp = jsonObject.optInt(TIMESTAMP);
        this.mTemperature = jsonObject.optJSONObject(TEMPERATURE).optDouble(VALUE);
        this.mRelativeHumidity = jsonObject.optJSONObject(MEASEUREMENTS).optJSONObject(RELATIVEHUMIDITY).optInt(VALUE);
    }

    @Override
    public String toString() {
        return "Bench2{" +
                "mTimeStamp=" + mTimeStamp +
                ", mTemperature=" + mTemperature +
                ", mRelativeHumidity=" + mRelativeHumidity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumiditySensor bench2 = (HumiditySensor) o;
        return mTimeStamp == bench2.mTimeStamp &&
                Double.compare(bench2.mTemperature, mTemperature) == 0 &&
                Double.compare(bench2.mRelativeHumidity, mRelativeHumidity) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(mTimeStamp, mTemperature, mRelativeHumidity);
    }
}

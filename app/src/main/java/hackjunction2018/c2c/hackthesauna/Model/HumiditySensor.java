package hackjunction2018.c2c.hackthesauna.Model;

import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by Cyril Niobé on 24/11/2018.
 */
public class HumiditySensor extends SimpleSensor {

    private double mRelativeHumidity;

    public static final String TIMESTAMP = "Timestamp";
    public static final String MEASUREMENTS = "Measurements";
    public static final String TEMPERATURE = "Temperature";
    public static final String RELATIVEHUMIDITY = "Relative humidity";
    public static final String VALUE = "value";

    public HumiditySensor(JSONObject jsonObject, String name) {
        super(jsonObject, name);
        this.mRelativeHumidity = jsonObject.optJSONObject(MEASUREMENTS).optJSONObject(RELATIVEHUMIDITY).optInt(VALUE);
    }

    public double getmRelativeHumidity() {
        return mRelativeHumidity;
    }

    public void setmRelativeHumidity(double mRelativeHumidity) {
        this.mRelativeHumidity = mRelativeHumidity;
    }

    public void updateBench2(JSONObject jsonObject) {
        this.setmTimeStamp(jsonObject.optInt(TIMESTAMP));
        this.setmTemperature(jsonObject.optJSONObject(TEMPERATURE).optDouble(VALUE));
        this.mRelativeHumidity = jsonObject.optJSONObject(MEASUREMENTS).optJSONObject(RELATIVEHUMIDITY).optInt(VALUE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HumiditySensor that = (HumiditySensor) o;
        return Double.compare(that.mRelativeHumidity, mRelativeHumidity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mRelativeHumidity);
    }

    @Override
    public String toString() {
        return "HumiditySensor{" +
                "Name=" + getName() +
                "mTemperature="+ getmTemperature() +
                "mRelativeHumidity=" + mRelativeHumidity +
                '}';
    }
}

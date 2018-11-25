package hackjunction2018.c2c.hackthesauna.Model;

import org.json.JSONObject;

import java.util.Objects;

/**
 * Created by Cyril Niobé on 24/11/2018.
 */
public class ComplexSensor extends SimpleSensor {

    private double mRelativeHumidity;
    private double mEnthalpy;

    public static final String TIMESTAMP = "Timestamp";
    public static final String MEASUREMENTS = "Measurements";
    public static final String TEMPERATURE = "Temperature";
    public static final String RELATIVEHUMIDITY = "Relative humidity";
    public static final String VALUE = "value";
    public static final String ENTHALPY = "Enthalpy";

    public ComplexSensor(JSONObject jsonObject, String name) {
        super(jsonObject, name);
        this.mRelativeHumidity = jsonObject.optJSONObject(MEASUREMENTS).optJSONObject(RELATIVEHUMIDITY).optInt(VALUE);
        this.mEnthalpy = jsonObject.optJSONObject(MEASUREMENTS).optJSONObject(ENTHALPY).optInt(VALUE);
    }

    public double getmRelativeHumidity() {
        return mRelativeHumidity;
    }

    public double getmEnthalpy() {
        return mEnthalpy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ComplexSensor that = (ComplexSensor) o;
        return Double.compare(that.mRelativeHumidity, mRelativeHumidity) == 0 &&
                Double.compare(that.mEnthalpy, mEnthalpy) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), mRelativeHumidity, mEnthalpy);
    }

    @Override
    public String toString() {
        return "ComplexeSensor{" +
                "Name=" + getName() +
                "mEnthalpy=" + getmEnthalpy() +
                "mTemperature=" + getmTemperature() +
                "mRelativeHumidity=" + mRelativeHumidity +
                '}';
    }
}

package hackjunction2018.c2c.hackthesauna;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import hackjunction2018.c2c.hackthesauna.Model.SimpleSensor;
import hackjunction2018.c2c.hackthesauna.Model.ComplexSensor;

/**
 * Created by Cyril Niobé on 24/11/2018.
 */
public class ContentManager {
    private static final String TAG = ContentManager.class.getSimpleName();
    private static ContentManager mInstance;
    private SimpleSensor mBench1, mBench3, mStove1, mStove2;
    private ComplexSensor mBench2, mCeiling1, mCeiling2, mFloor1, mDoorway1;
    private Context mContext;
    private ContentManager.DataListener mDataListener;
    private int averageTemperature;
    private SimpleSensor lowestTemperatureSensor;
    private SimpleSensor highestTemperatureSensor;
    private int averageEnthalpy;
    private double averageCarbonDioxideEmission;
    private double averageHumidity;
    private RequestQueue mRequestQueue;


    private List<SimpleSensor> mSimpleSensorList;

    private int numberOfRequestsToMake;
    private boolean hasRequestFailed = false;

    public interface DataListener {
        void notifyRetrieved();

        void notifyNotRetrieved();
    }

    public static ContentManager getInstance(Context context, DataListener dataListener) {
        if (mInstance == null)
            mInstance = new ContentManager(context, dataListener);

        return mInstance;
    }

    private ContentManager(Context context, DataListener dataListener) {
        this.mContext = context;
        this.mDataListener = dataListener;
        this.mRequestQueue = Volley.newRequestQueue(this.mContext);
        this.mSimpleSensorList = new ArrayList<>();
    }

    public void fetchAllData() {
        this.getmSimpleSensorList().clear();
        this.numberOfRequestsToMake = 9;
        fetchBench1();
        fetchBench2();
        fetchBench3();
        fetchCeiling1();
        fetchCeiling2();
        fetchStove1();
        fetchStove2();
        fetchDoorway1();
        fetchFloor1();
    }

    private void fetchBench1() {
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Bench1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mBench1 = new SimpleSensor(jsonObject, "Bench 1");
                        mSimpleSensorList.add(mBench1);
                    }
                    numberOfRequestsToMake--;

                    if (numberOfRequestsToMake == 0) {
                        if (!hasRequestFailed) {
                            //All requests finished correctly
                            mDataListener.notifyRetrieved();
                        } else {
                            //At least one request failed
                            mDataListener.notifyNotRetrieved();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("VOLLEY ERROR: " + error);
                mDataListener.notifyNotRetrieved();
            }
        });

        this.mRequestQueue.add(jsonArrayRequest);
    }

    private void fetchBench2() {
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Bench2&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mBench2 = new ComplexSensor(jsonObject, "Bench 2");
                        mSimpleSensorList.add(mBench2);
                    }

                    numberOfRequestsToMake--;

                    if (numberOfRequestsToMake == 0) {
                        if (!hasRequestFailed) {
                            //All requests finished correctly
                            mDataListener.notifyRetrieved();
                        } else {
                            //At least one request failed
                            mDataListener.notifyNotRetrieved();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("VOLLEY ERROR: " + error);
                mDataListener.notifyNotRetrieved();
            }
        });


        mRequestQueue.add(jsonArrayRequest);
    }

    private void fetchBench3() {
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Bench3&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mBench3 = new SimpleSensor(jsonObject, "Bench 3");
                        mSimpleSensorList.add(mBench3);
                    }
                    numberOfRequestsToMake--;

                    if (numberOfRequestsToMake == 0) {
                        if (!hasRequestFailed) {
                            //All requests finished correctly
                            mDataListener.notifyRetrieved();
                        } else {
                            //At least one request failed
                            mDataListener.notifyNotRetrieved();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("VOLLEY ERROR: " + error);
                mDataListener.notifyNotRetrieved();
            }
        });

        mRequestQueue.add(jsonArrayRequest);
    }

    private void fetchStove1() {
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Stove1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mStove1 = new SimpleSensor(jsonObject, "Stove 1");
                        mSimpleSensorList.add(mStove1);

                    }
                    numberOfRequestsToMake--;

                    if (numberOfRequestsToMake == 0) {
                        if (!hasRequestFailed) {
                            //All requests finished correctly
                            mDataListener.notifyRetrieved();
                        } else {
                            //At least one request failed
                            mDataListener.notifyNotRetrieved();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("VOLLEY ERROR: " + error);
                mDataListener.notifyNotRetrieved();
            }
        });

        mRequestQueue.add(jsonArrayRequest);
    }

    private void fetchStove2() {
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Stove2&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mStove2 = new SimpleSensor(jsonObject, "Stove 2");
                        mSimpleSensorList.add(mStove2);
                    }
                    numberOfRequestsToMake--;

                    if (numberOfRequestsToMake == 0) {
                        if (!hasRequestFailed) {
                            //All requests finished correctly
                            mDataListener.notifyRetrieved();
                        } else {
                            //At least one request failed
                            mDataListener.notifyNotRetrieved();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("VOLLEY ERROR: " + error);
                mDataListener.notifyNotRetrieved();
            }
        });

        mRequestQueue.add(jsonArrayRequest);
    }

    private void fetchCeiling1() {
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Ceiling1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mCeiling1 = new ComplexSensor(jsonObject, "Ceiling 1");
                        mSimpleSensorList.add(mCeiling1);
                    }
                    numberOfRequestsToMake--;

                    if (numberOfRequestsToMake == 0) {
                        if (!hasRequestFailed) {
                            //All requests finished correctly
                            mDataListener.notifyRetrieved();
                        } else {
                            //At least one request failed
                            mDataListener.notifyNotRetrieved();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("VOLLEY ERROR: " + error);
                mDataListener.notifyNotRetrieved();
            }
        });

        mRequestQueue.add(jsonArrayRequest);
    }

    private void fetchCeiling2() {
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Ceiling2&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mCeiling2 = new ComplexSensor(jsonObject, "Ceiling 2");
                        mSimpleSensorList.add(mCeiling2);

                    }
                    numberOfRequestsToMake--;

                    if (numberOfRequestsToMake == 0) {
                        if (!hasRequestFailed) {
                            //All requests finished correctly
                            mDataListener.notifyRetrieved();
                        } else {
                            //At least one request failed
                            mDataListener.notifyNotRetrieved();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("VOLLEY ERROR: " + error);
                mDataListener.notifyNotRetrieved();
            }
        });

        mRequestQueue.add(jsonArrayRequest);
    }

    private void fetchFloor1() {
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Floor1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mFloor1 = new ComplexSensor(jsonObject, "Floor 1");
                        mSimpleSensorList.add(mFloor1);
                        System.out.println("LAST ADDED");

                    }
                    numberOfRequestsToMake--;

                    if (numberOfRequestsToMake == 0) {
                        if (!hasRequestFailed) {
                            //All requests finished correctly
                            mDataListener.notifyRetrieved();

                        } else {
                            //At least one request failed
                            mDataListener.notifyNotRetrieved();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("VOLLEY ERROR: " + error);
                mDataListener.notifyNotRetrieved();
            }
        });

        mRequestQueue.add(jsonArrayRequest);
    }

    private void fetchDoorway1() {
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Doorway1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mDoorway1 = new ComplexSensor(jsonObject, "Doorway 1");
                        mSimpleSensorList.add(mDoorway1);

                    }
                    numberOfRequestsToMake--;


                    if (numberOfRequestsToMake == 0) {
                        if (!hasRequestFailed) {
                            //All requests finished correctly
                            mDataListener.notifyRetrieved();
                        } else {
                            //At least one request failed
                            mDataListener.notifyNotRetrieved();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("VOLLEY ERROR: " + error);
                mDataListener.notifyNotRetrieved();
            }
        });

        mRequestQueue.add(jsonArrayRequest);
    }


    public int getAverageEnthalpy() {
        return averageEnthalpy;
    }

    public double getAverageCarbonDioxideEmission() {
        return averageCarbonDioxideEmission;
    }

    public int getAverageTemperature() {
        return averageTemperature;
    }

    public double getAverageHumidity() {
        return averageHumidity;
    }

    public void setAverageTemperature(int averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public void setAverageHumidity(double averageHumidity) {
        this.averageHumidity = averageHumidity;
    }

    public void setAverageEnthalpy(int averageEnthalpy) {
        this.averageEnthalpy = averageEnthalpy;
    }

    public void setAverageCarbonDioxideEmission(double averageCarbonDioxideEmission) {
        this.averageCarbonDioxideEmission = averageCarbonDioxideEmission;
    }

    public List<SimpleSensor> getmSimpleSensorList() {
        return mSimpleSensorList;
    }

    public SimpleSensor getLowestTemperatureSensor() {
        return lowestTemperatureSensor;
    }

    public SimpleSensor getHighestTemperatureSensor() {
        return highestTemperatureSensor;
    }

    public void setLowestTemperatureSensor(SimpleSensor lowestTemperatureSensor) {
        this.lowestTemperatureSensor = lowestTemperatureSensor;
    }

    public void setHighestTemperatureSensor(SimpleSensor highestTemperatureSensor) {
        this.highestTemperatureSensor = highestTemperatureSensor;
    }
}

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
import hackjunction2018.c2c.hackthesauna.Model.HumiditySensor;

/**
 * Created by Cyril Niobé on 24/11/2018.
 */
public class ContentManager {
    private static final String TAG = ContentManager.class.getSimpleName();
    private static ContentManager mInstance;
    private SimpleSensor mBench1, mBench3, mStove1, mStove2;
    private HumiditySensor mBench2, mCeiling1, mCeiling2, mFloor1, mDoorway1;
    private Context mContext;
    private ContentManager.DataListener mDataListener;
    private int averageTemperature;
    SimpleSensor lowestTemperatureSensor;
    SimpleSensor highestTemperatureSensor;
    private int averageHumidity;

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
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Bench1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mBench1 = new SimpleSensor(jsonObject, "Bench1");
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
                Toast.makeText(mContext, "Sorry, internet error", Toast.LENGTH_SHORT).show();
                mDataListener.notifyNotRetrieved();
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void fetchBench2() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Bench2&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mBench2 = new HumiditySensor(jsonObject, "Bench2");
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
                Toast.makeText(mContext, "Sorry, internet error", Toast.LENGTH_SHORT).show();
                mDataListener.notifyNotRetrieved();
            }
        });


        queue.add(jsonArrayRequest);
    }

    private void fetchBench3() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Bench3&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mBench3 = new SimpleSensor(jsonObject, "Bench3");
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
                Toast.makeText(mContext, "Sorry, internet error", Toast.LENGTH_SHORT).show();
                mDataListener.notifyNotRetrieved();
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void fetchStove1() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Stove1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mStove1 = new SimpleSensor(jsonObject, "Stove1");
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
                Toast.makeText(mContext, "Sorry, internet error", Toast.LENGTH_SHORT).show();
                mDataListener.notifyNotRetrieved();
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void fetchStove2() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Stove2&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mStove2 = new SimpleSensor(jsonObject, "Stove2");
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
                Toast.makeText(mContext, "Sorry, internet error", Toast.LENGTH_SHORT).show();
                mDataListener.notifyNotRetrieved();
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void fetchCeiling1() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Ceiling1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mCeiling1 = new HumiditySensor(jsonObject, "Ceiling1");
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
                Toast.makeText(mContext, "Sorry, internet error", Toast.LENGTH_SHORT).show();
                mDataListener.notifyNotRetrieved();
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void fetchCeiling2() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Ceiling2&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mCeiling2 = new HumiditySensor(jsonObject, "Ceiling2");
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
                Toast.makeText(mContext, "Sorry, internet error", Toast.LENGTH_SHORT).show();
                mDataListener.notifyNotRetrieved();
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void fetchFloor1() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Floor1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mFloor1 = new HumiditySensor(jsonObject, "Floor1");
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
                Toast.makeText(mContext, "Sorry, internet error", Toast.LENGTH_SHORT).show();
                mDataListener.notifyNotRetrieved();
            }
        });

        queue.add(jsonArrayRequest);
    }

    private void fetchDoorway1() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Doorway1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mDoorway1 = new HumiditySensor(jsonObject, "Doorway1");
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
                Toast.makeText(mContext, "Sorry, internet error", Toast.LENGTH_SHORT).show();
                mDataListener.notifyNotRetrieved();
            }
        });

        queue.add(jsonArrayRequest);
    }


    public SimpleSensor getmBench1() {
        return mBench1;
    }

    public SimpleSensor getmBench3() {
        return mBench3;
    }

    public HumiditySensor getmBench2() {
        return this.mBench2;
    }

    public SimpleSensor getmStove1() {
        return mStove1;
    }

    public SimpleSensor getmStove2() {
        return mStove2;
    }

    public HumiditySensor getmCeiling1() {
        return mCeiling1;
    }

    public HumiditySensor getmCeiling2() {
        return mCeiling2;
    }

    public HumiditySensor getmFloor1() {
        return mFloor1;
    }

    public HumiditySensor getmDoorway1() {
        return mDoorway1;
    }

    public int getAverageTemperature() {
        return averageTemperature;
    }

    public int getAverageHumidity() {
        return averageHumidity;
    }

    public void setAverageTemperature(int averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public void setAverageHumidity(int averageHumidity) {
        this.averageHumidity = averageHumidity;
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

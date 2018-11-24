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

import hackjunction2018.c2c.hackthesauna.Model.SimpleSensor;
import hackjunction2018.c2c.hackthesauna.Model.HumiditySensor;

/**
 * Created by Cyril Niobé on 24/11/2018.
 */
public class ContentManager {
    private static final String TAG = ContentManager.class.getSimpleName();
    private static ContentManager mInstance;
    private SimpleSensor mBench1, mBench3, mStove1, mStove2;
    private HumiditySensor mBench2, mCeiling1, mCeiling2, mFloor1, mDoorway1, mOutdoor1;
    private Context mContext;
    ContentManager.DataListener mDataListener;

    private int numberOfRequestsToMake = 10;
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
        this.fetchAllData();
    }

    private void fetchAllData() {
        fetchBench1();
        fetchBench2();
        fetchBench3();
        fetchCeiling1();
        fetchCeiling2();
        fetchStove1();
        fetchStove2();
        fetchDoorway1();
        fetchOutdoor1();
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
                        mBench1 = new SimpleSensor(jsonObject);

                    }
                    numberOfRequestsToMake--;

                    if(numberOfRequestsToMake == 0) {
                        if(!hasRequestFailed) {
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
                        mBench2 = new HumiditySensor(jsonObject);
                    }

                    numberOfRequestsToMake--;

                    if(numberOfRequestsToMake == 0) {
                        if(!hasRequestFailed) {
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
                        mBench3 = new SimpleSensor(jsonObject);

                    }
                    numberOfRequestsToMake--;

                    if(numberOfRequestsToMake == 0) {
                        if(!hasRequestFailed) {
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
                        mStove1 = new SimpleSensor(jsonObject);

                    }
                    numberOfRequestsToMake--;

                    if(numberOfRequestsToMake == 0) {
                        if(!hasRequestFailed) {
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
                        mStove2 = new SimpleSensor(jsonObject);

                    }
                    numberOfRequestsToMake--;

                    if(numberOfRequestsToMake == 0) {
                        if(!hasRequestFailed) {
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
                        mCeiling1 = new HumiditySensor(jsonObject);

                    }
                    numberOfRequestsToMake--;

                    if(numberOfRequestsToMake == 0) {
                        if(!hasRequestFailed) {
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
                        mCeiling2 = new HumiditySensor(jsonObject);

                    }
                    numberOfRequestsToMake--;

                    if(numberOfRequestsToMake == 0) {
                        if(!hasRequestFailed) {
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
                        mFloor1 = new HumiditySensor(jsonObject);

                    }
                    numberOfRequestsToMake--;

                    if(numberOfRequestsToMake == 0) {
                        if(!hasRequestFailed) {
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
                        mDoorway1 = new HumiditySensor(jsonObject);

                    }
                    numberOfRequestsToMake--;

                    if(numberOfRequestsToMake == 0) {
                        if(!hasRequestFailed) {
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

    private void fetchOutdoor1() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url = "https://apigtw.vaisala.com/hackjunction2018/saunameasurements/latest?SensorID=Outdoor1&limit=1";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Loop through the array elements
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        mOutdoor1 = new HumiditySensor(jsonObject);

                    }
                    numberOfRequestsToMake--;

                    if(numberOfRequestsToMake == 0) {
                        if(!hasRequestFailed) {
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

    public HumiditySensor getmOutdoor1() {
        return mOutdoor1;
    }
}

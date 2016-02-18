package com.example.adu49.androidmaterialwithvolley.Singletons;

import android.support.v4.app.Fragment;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.adu49.androidmaterialwithvolley.CallBackInterfaces.ICallBack;


public class ApiMAnager
{

    ICallBack iCallBack;
    Fragment context;

    private static ApiMAnager instance;

    public static ApiMAnager getInstance() {
        if (instance == null) {
            instance = new ApiMAnager();
        }
        return instance;
    }

    public static ApiMAnager getInstance(Fragment context) {
        //if (instance == null) {
            instance = new ApiMAnager(context);
        //}
        return instance;
    }

    ApiMAnager(){

    }

    ApiMAnager(Fragment c){
        this.context = c;
    }

    /**
     * Call from Activity only
     *
     * @param requestUrl
     */
    public void sendReq(String requestUrl, final String id) {
         iCallBack = (ICallBack)context;
        StringRequest stringRequest = new StringRequest(requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iCallBack.onResult(response,id);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallBack.onResult(error.getMessage(),id);
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest, "stringReq");
    }

    /**
     * Call from Fragment only
     *
     * @param context
     * @param requestUrl
     */
    public void sendReq(Fragment context, String requestUrl, final String id) {
        iCallBack = (ICallBack)context;

        StringRequest stringRequest = new StringRequest(requestUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iCallBack.onResult(response,id);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallBack.onResult(error.getMessage(),id);
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);

    }
}
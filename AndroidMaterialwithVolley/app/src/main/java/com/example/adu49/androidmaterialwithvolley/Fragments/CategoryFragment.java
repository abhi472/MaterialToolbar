package com.example.adu49.androidmaterialwithvolley.Fragments;

import com.android.volley.toolbox.ImageLoader;
import com.example.adu49.androidmaterialwithvolley.Singletons.AppController;
import com.example.adu49.androidmaterialwithvolley.CallBackInterfaces.ICallBack;
import com.example.adu49.androidmaterialwithvolley.Models.SiteCat;
import com.example.adu49.androidmaterialwithvolley.R;
import com.example.adu49.androidmaterialwithvolley.Singletons.ApiMAnager;
import com.example.adu49.androidmaterialwithvolley.Models.Customer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;


import java.util.ArrayList;



public class CategoryFragment extends Fragment implements ICallBack {

    private OnFragmentInteractionListener mListener;
    public static final int CATEGID = 2001;
    String url ="https://www.lafalafa.com/lafalafafront/api/getAllSiteCat";
    TextView txtShowJson;
    RecyclerView rv;
    ObjectMapper obj=new ObjectMapper();
    ProgressDialog pDialog;
   public static final String id="one";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        pDialog = new ProgressDialog(getActivity());
        View rootView = inflater.inflate(R.layout.json, container, false);
      //  txtShowJson=(TextView)rootView.findViewById(R.id.checkJParse);
        pDialog.show();
        ApiMAnager.getInstance(this).sendReq(url,id);
        rv=(RecyclerView)rootView.findViewById(R.id.recyclerView);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void setupRecyclerView(RecyclerView rv,ArrayList<Customer>cust)  {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new recyclerviewAdapter(getActivity(),cust));
    }

    public static class recyclerviewAdapter extends RecyclerView.Adapter<recyclerviewAdapter.ViewHolder>
    {
         Context mContext;
         ArrayList<Customer> customer;
         ImageLoader imageLoader = AppController.getInstance().getImageLoader();


        public static class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public ViewHolder(View view) {
                super(view);
                mView = view;
            }
        }



        public recyclerviewAdapter(Context context, ArrayList<Customer> customer) {
            mContext = context;
            this.customer=customer   ;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.customlist, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
         TextView t=(TextView)holder.mView.findViewById(R.id.textView);
            TextView t2=(TextView)holder.mView.findViewById(R.id.textView2);
            TextView t3=(TextView)holder.mView.findViewById(R.id.textView3);
            NetworkImageView ni=(NetworkImageView)holder.mView.findViewById(R.id.imageView);
            t.setText(customer.get(position).catName);
            t2.setText(customer.get(position).catId);
            t3.setText(customer.get(position).catSlug);
            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
            ni.setImageUrl(customer.get(position).catImg, imageLoader);

        }


        @Override
        public int getItemCount() {
            return customer.size();
        }
    }


    @Override
    public void onResult(String result,String id) {
        Log.e("onResult",this.getClass().getName());
        Log.e("onResult",id);
        pDialog.dismiss();

        if (CategoryFragment.id.equalsIgnoreCase(id)) {

        String reqresult = result.trim();

            SiteCat custObjects = null;
            try {
                JsonFactory jsonFactory = new JsonFactory();
                JsonParser jsonParser = jsonFactory.createJsonParser(result.trim());
                custObjects = obj.readValue(jsonParser, SiteCat.class);            // txtShowJson.setText(cust.getCatn());
            } catch (Exception e) {
                e.printStackTrace();
            }

            //txtShowJson.setText(custObjects.SiteCats.size()+"");
            setupRecyclerView(rv, custObjects.SiteCats);


        }
    }


    @Override
    public void onError(String error) {
        pDialog.dismiss();
        String ErrorData = error;
        txtShowJson.setText(ErrorData);
    }
}


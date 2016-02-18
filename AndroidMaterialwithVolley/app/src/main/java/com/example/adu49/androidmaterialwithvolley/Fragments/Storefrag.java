package com.example.adu49.androidmaterialwithvolley.Fragments;

import com.android.volley.toolbox.ImageLoader;
import com.example.adu49.androidmaterialwithvolley.Singletons.AppController;
import com.example.adu49.androidmaterialwithvolley.CallBackInterfaces.ICallBack;
import com.example.adu49.androidmaterialwithvolley.Models.Offer;
import com.example.adu49.androidmaterialwithvolley.Models.StoreOffers;
import com.example.adu49.androidmaterialwithvolley.R;
import com.example.adu49.androidmaterialwithvolley.Singletons.ApiMAnager;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Storefrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Storefrag extends Fragment implements ICallBack {
RecyclerView rv;
    ProgressDialog pDialog;
    ObjectMapper om=new ObjectMapper();
    String url="https://www.lafalafa.com/lafalafafront/api/storeOffer";
    private OnFragmentInteractionListener mListener;
    public static final String id="two";

    public Storefrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView= inflater.inflate(R.layout.homefrag2,container,false);
        rv=(RecyclerView)rootView.findViewById(R.id.storeOffersRecycler);
        pDialog = new ProgressDialog(getActivity());
        pDialog.show();

        //t=(TextView)rootView.findViewById(R.id.checkJParse);
        ApiMAnager.getInstance(this).sendReq(url,id);
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
    public void recyclerviewsetup(RecyclerView rv,ArrayList<Offer> store)
    {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(new recyclerviewAdapter(rv.getContext(),store));
    }
    public static class recyclerviewAdapter extends RecyclerView.Adapter<recyclerviewAdapter.ViewHolder>
    {
        Context mContext;
        ArrayList<Offer>store;
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();


        public static class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public ViewHolder(View view) {
                super(view);
                mView = view;

            }

        }



        public recyclerviewAdapter(Context context, ArrayList<Offer> store) {
            mContext = context;
            this.store=store   ;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.storelist, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            TextView t=(TextView)holder.mView.findViewById(R.id.textView4);
            TextView t2=(TextView)holder.mView.findViewById(R.id.textView5);
            TextView t3=(TextView)holder.mView.findViewById(R.id.textView6);
            TextView t4=(TextView)holder.mView.findViewById(R.id.textView7);
            TextView t5=(TextView)holder.mView.findViewById(R.id.textView8);
            NetworkImageView ni=(NetworkImageView)holder.mView.findViewById(R.id.imageView2);
            t.setText(store.get(position).offerName);
            t2.setText(store.get(position).id);
            t3.setText(store.get(position).storeName);
            t4.setText(store.get(position).cashbackTitle);
            t5.setText(store.get(position).cashback);
            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
            ni.setImageUrl(store.get(position).img, imageLoader);

        }


        @Override
        public int getItemCount() {
            return store.size();
        }
    }

    @Override
    public void onResult(String result,String id) {
        Log.e("id1",id);
        Log.e("id2",this.getClass().getName());
        if(Storefrag.id.equalsIgnoreCase(id)) {
        StoreOffers storeOffers=null;
        String reqresult = result.trim();

            try {
                JsonFactory jsonFactory = new JsonFactory();
                JsonParser jsonParser = jsonFactory.createJsonParser(reqresult);
                storeOffers = om.readValue(jsonParser, StoreOffers.class);            // txtShowJson.setText(cust.getCatn());
            } catch (Exception e) {
                e.printStackTrace();
            }
//        t.setText(result);
//        t.setMovementMethod(new ScrollingMovementMethod());
            recyclerviewsetup(rv, storeOffers.Offers);


        }
        pDialog.dismiss();

    }

    @Override
    public void onError(String error) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package com.abhijit.mobeng.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.abhijit.mobeng.R;
import com.abhijit.mobeng.adapter.DealsAdapter;
import com.abhijit.mobeng.model.Deal;
import com.abhijit.mobeng.util.JsonArrayRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.deals_list_view) ListView vDealsListView;
    @InjectView(R.id.progress) ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        setupListView();

        dispatchVolleyRequest(getString(R.string.feed_url)); // fetch data
    }

    /**
     * Creates and initiates a volley GET request to retrieve json from the given url, using
     * {@link com.abhijit.mobeng.util.JsonArrayRequest}
     *
     * @param url url to retrieve the json from
     */
    private void dispatchVolleyRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url,
                    new Response.Listener<JsonArray>() {
                        @Override
                        public void onResponse(JsonArray jsonArray) {
                            hideProgressBar();

                            ArrayAdapter dealsArrayAdapter = (ArrayAdapter) vDealsListView.getAdapter();
                            dealsArrayAdapter.clear();

                            List<Deal> deals = new Gson().fromJson(jsonArray, new TypeToken<List<Deal>>() {}.getType());
                            bindDataToAdapter(dealsArrayAdapter, deals);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            hideProgressBar();

                            Toast.makeText(MainActivity.this, getString(R.string.error_retrieving_feed), Toast.LENGTH_LONG).show();
                        }
                    }
                );
        queue.add(jsonObjectRequest);

        showProgressBar();
    }

    /**
     * Attaches a {@link com.abhijit.mobeng.adapter.DealsAdapter} and an {@link android.widget.AdapterView.OnItemClickListener}
     * to {@link #vDealsListView}
     */
    private void setupListView() {
        vDealsListView.setAdapter(new DealsAdapter(MainActivity.this, R.layout.list_row, new ArrayList<Deal>()));
        vDealsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Deal deal = (Deal) vDealsListView.getAdapter().getItem(position);
                if(deal != null) {
                    startBrowserIntent(deal.getHref());
                }
            }
        });
    }

    /**
     * Displays the content of the url in a web browser
     * @param url a String
     */
    private void startBrowserIntent(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    /**
     * Binds the given deals list to the adapter
     * @param adapter the adapter that will contain the data
     * @param deals a List
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void bindDataToAdapter(ArrayAdapter adapter, List<Deal> deals){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            adapter.addAll(deals);
        } else {
            for(Deal deal : deals) adapter.add(deal);
        }
    }

    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
}

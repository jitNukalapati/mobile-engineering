package com.abhijit.mobeng.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        vDealsListView.setAdapter(new DealsAdapter(MainActivity.this, R.layout.list_row, new ArrayList<Deal>()));
        createVolleyRequest(getString(R.string.feed_url));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Creates and initiates a volley GET request to retrieve json from the given url
     *
     * @param url url to retrieve the json from
     */
    private void createVolleyRequest(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url,
                    new Response.Listener<JsonArray>() {
                        @Override
                        public void onResponse(JsonArray jsonArray) {
                            ArrayAdapter dealsArrayAdapter = (ArrayAdapter) vDealsListView.getAdapter();
                            dealsArrayAdapter.clear();

                            List<Deal> deals = new Gson().fromJson(jsonArray, new TypeToken<List<Deal>>(){}.getType());
                            dealsArrayAdapter.addAll(deals); // XXX: requires api level 11
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(MainActivity.this, getString(R.string.error_retrieving_feed), Toast.LENGTH_LONG).show();
                        }
                    }
                );
        queue.add(jsonObjectRequest);
    }

}

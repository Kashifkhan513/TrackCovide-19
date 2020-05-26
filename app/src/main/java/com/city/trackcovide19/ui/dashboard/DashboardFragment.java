package com.city.trackcovide19.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.city.trackcovide19.Adapter.DataAdapter;
import com.city.trackcovide19.Interface.CallBack;
import com.city.trackcovide19.Main2Activity;
import com.city.trackcovide19.Model.Model;
import com.city.trackcovide19.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DashboardFragment extends Fragment  {

    private RecyclerView recyclerView;
    private Model model;
    private ArrayList<Model> arrayList;
    private DataAdapter adapter;
    private ProgressBar progressBar;
    SearchView searchView;
    String url = "https://corona.lmao.ninja/v2/countries";

    Context context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        setHasOptionsMenu(true);

        progressBar = root.findViewById(R.id.pb);
        arrayList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        context=getContext();
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));


        getJsonData();


        return root;
    }

    public void getJsonData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.GET, url, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);

                        try {
                            JSONArray array = new JSONArray(response);


                            for (int i = 0; i < array.length(); i++) {
                                JSONObject arrayObject = array.getJSONObject(i);
                                JSONObject object = arrayObject.getJSONObject("countryInfo");
                                //Log.d("arrayObject","Body="+arrayObject);
                                String countryName = arrayObject.getString("country");
                                String flag = object.getString("flag");
                                String totalCases = arrayObject.getString("cases");
                                String todayCases = arrayObject.getString("todayCases");
                                String totalDeath = arrayObject.getString("deaths");
                                String recovered = arrayObject.getString("recovered");
                                String critical = arrayObject.getString("critical");

                                Log.d("critical", "critical=" + countryName);

                                arrayList.add(new Model(countryName, flag, totalCases, todayCases, totalDeath, recovered, critical));
                            }
                            adapter = new DataAdapter(getContext(), arrayList);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull final MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.searchView);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toUpperCase();
                ArrayList<Model> modelArrayList = new ArrayList<>();
                for (Model m : arrayList) {
                    if (m.getCountryName().toUpperCase().contains(userInput)) {
                        modelArrayList.add(m);


                    }

                }
                adapter.searchItemList(modelArrayList);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }


}

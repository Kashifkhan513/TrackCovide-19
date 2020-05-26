package com.city.trackcovide19.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.city.trackcovide19.R;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

 private TextView tvTotalVictum,tvTotalDeath,tvTotalRecover;
  ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
       tvTotalVictum=root.findViewById(R.id.tvTotal);
        tvTotalDeath=root.findViewById(R.id.tvDeath);
        tvTotalRecover=root.findViewById(R.id.tvRecover);
        progressBar=root.findViewById(R.id.pg);
       getJsonData();
        return root;
    }


    private void getJsonData() {
        RequestQueue queue= Volley.newRequestQueue(getContext());
        String url="https://corona.lmao.ninja/v2/all";
        String url2="https://corona-virus-stats.herokuapp.com/api/v1/cases/general-stats";
        final StringRequest request=new StringRequest(Request.Method.GET, url, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject object=new JSONObject(response);

                            String tvVictim=object.getString("cases");

                            String tvDeath=object.getString("deaths");

                            String tvRecover=object.getString("recovered");

                            tvTotalVictum.setText(tvVictim);

                            tvTotalDeath.setText(tvDeath);

                            tvTotalRecover.setText(tvRecover);
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
}

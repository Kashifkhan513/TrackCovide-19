package com.city.trackcovide19.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.city.trackcovide19.Interface.CallBack;
import com.city.trackcovide19.Main2Activity;
import com.city.trackcovide19.Model.Model;
import com.city.trackcovide19.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private Context context;
    private ArrayList<Model> arrayList;
     private CallBack callBack;

    public DataAdapter(Context context, ArrayList<Model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_row, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, final int position) {
        Model model = arrayList.get(position);
        holder.tvCountryName.setText(model.getCountryName());
        Glide.with(context).load(model.getFlag()).into(holder.ivFalg);
        holder.totalCases.setText(model.getTotalCases());
        holder.todayCases.setText(model.getTodayCases());
        holder.totalDeath.setText(model.getTotalDeath());
        holder.recovered.setText(model.getRecovered());
        holder.critical.setText(model.getCritical());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Main2Activity.class);
                intent.putExtra("Name",arrayList.get(position).getCountryName());
                intent.putExtra("TotalCases",arrayList.get(position).getTotalCases());
                intent.putExtra("TodayCases",arrayList.get(position).getTodayCases());
                intent.putExtra("TotalDeath",arrayList.get(position).getTotalDeath());
                intent.putExtra("Recovered",arrayList.get(position).getRecovered());
                intent.putExtra("Critical",arrayList.get(position).getCritical());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class DataViewHolder extends RecyclerView.ViewHolder {

        TextView tvCountryName;
        ImageView ivFalg;
        TextView totalCases;
        TextView todayCases;
        TextView totalDeath;
        TextView recovered;
        TextView critical;
        RelativeLayout relativeLayout;
        CardView cardView;


        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountryName = itemView.findViewById(R.id.tvCountryName);
            ivFalg = itemView.findViewById(R.id.tvFlag);
            totalCases = itemView.findViewById(R.id.tvToalcases);
            todayCases = itemView.findViewById(R.id.tvTodayCases);
            totalDeath = itemView.findViewById(R.id.tvTotalDeath);
            recovered = itemView.findViewById(R.id.tvRecover);
            critical = itemView.findViewById(R.id.tvCritical);
            relativeLayout=itemView.findViewById(R.id.rl);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }



    public void searchItemList(ArrayList<Model> modelArrayList) {
        arrayList = new ArrayList<>();
        arrayList.addAll(modelArrayList);
        notifyDataSetChanged();
    }

}

package com.sidume.freedmores.sqlitecruddemo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    List<Country> countries;

    public CustomAdapter(Context context, List<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_list_item, viewGroup,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {

        final Country country = (Country) countries.get(i);
        holder.vh_country_name.setText(country.getCountry_name().toString());
        holder.vh_country_cont.setText(country.getCountry_continent().toString());
        holder.vh_population.setText(String.valueOf(country.getPopulation()).toString());
        holder.vh_currency.setText(String.valueOf(country.getCurrency()).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = holder.getAdapterPosition();
                Country selected_country = (Country) countries.get(i);

                Intent modify_intent = new Intent(holder.itemView.getContext(), ModifyCountryActivity.class);
                modify_intent.putExtra("rec_id",String.valueOf(selected_country.getId()));
                modify_intent.putExtra("country_name", selected_country.getCountry_name().toString());
                modify_intent.putExtra("continent", selected_country.getCountry_continent().toString());
                modify_intent.putExtra("population", String.valueOf(country.getPopulation()).toString());
                modify_intent.putExtra("currency", String.valueOf(country.getCurrency()).toString());

                context.startActivity(modify_intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView vh_country_name,   vh_country_cont,vh_population,vh_currency;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vh_country_name = itemView.findViewById(R.id.txt_country_name);
            vh_country_cont = itemView.findViewById(R.id.txt_continent);
            vh_population = itemView.findViewById(R.id.txt_population);
            vh_currency = itemView.findViewById(R.id.txt_currency);
        }
    }
}

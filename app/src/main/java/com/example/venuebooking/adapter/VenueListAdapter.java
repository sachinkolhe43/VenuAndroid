package com.example.venuebooking.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.venuebooking.R;
import com.example.venuebooking.activity.DetailsActivity;
import com.example.venuebooking.entity.Venue;

import java.util.List;

public class VenueListAdapter extends RecyclerView.Adapter<VenueListAdapter.MyViewHolder> {

    Context context;
    List<Venue> venueList;

    public VenueListAdapter(Context context, List<Venue> venueList) {
        this.context = context;
        this.venueList = venueList;
    }

    @NonNull
    @Override
    public VenueListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.venue_list,null);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull VenueListAdapter.MyViewHolder holder, int position) {
        Venue venue= venueList.get(position);
        holder.textName.setText(venue.getVenue_name());
        holder.textdescription.setText(venue.getVenue_description());
        holder.textcontact.setText(""+venue.getVenue_contact());
        holder.textaddress.setText(""+venue.getVenue_address());
       Glide.with(context).load("http://192.168.1.16:4000/"+venue.getVenue_image()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView textName,textdescription,textcontact,textaddress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textName = itemView.findViewById(R.id.textName);
            textdescription = itemView.findViewById(R.id.textdescription);
            textcontact = itemView.findViewById(R.id.textcontact);
            textaddress = itemView.findViewById(R.id.textaddress);
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("venue",venueList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}

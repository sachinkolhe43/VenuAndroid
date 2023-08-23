package com.example.venuebooking.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.venuebooking.R;
import com.example.venuebooking.adapter.VenueListAdapter;
import com.example.venuebooking.entity.Venue;
import com.example.venuebooking.utils.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VenueListFragment extends Fragment {
    RecyclerView recyclerView;

    VenueListAdapter venueListAdapter;
    List<Venue> venueList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_venue_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        venueList = new ArrayList<>();
        venueListAdapter = new VenueListAdapter(getContext(),venueList);
        recyclerView.setAdapter(venueListAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        getAllVenues();
    }

    private void getAllVenues() {
        RetrofitClient.getInstance().getApi().getAllVenues().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().getAsJsonObject().get("status").getAsString().equals("success")){
                    JsonArray jsonArray = response.body().getAsJsonObject().get("data").getAsJsonArray();
                    Log.d("Venue list",jsonArray.toString());
                    for (JsonElement element :jsonArray) {
                        Venue venue = new Venue();
                        venue.setVenue_id(element.getAsJsonObject().get("Venue_id").getAsInt());
                        venue.setVenue_name(element.getAsJsonObject().get("Venue_name").getAsString());
                        venue.setVenue_description(element.getAsJsonObject().get("Venue_description").getAsString());
                        venue.setVenue_contact(element.getAsJsonObject().get("Venue_contact").getAsInt());
                        venue.setVenue_address(element.getAsJsonObject().get("Venue_address").getAsString());
                        venue.setVenue_amountPerDay(element.getAsJsonObject().get("Venue_amountPerDay").getAsInt());
                        venue.setVenue_image(element.getAsJsonObject().get("Venue_image").getAsString());
                        venueList.add(venue);
                    }
                    venueListAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


package com.example.venuebooking.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.venuebooking.R;
import com.example.venuebooking.utils.RetrofitClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ProfileFragment extends Fragment {

    TextView textUser_email,textUser_name,textUser_mobile,textUser_address;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textUser_name = view.findViewById(R.id.textUser_name);
        textUser_email = view.findViewById(R.id.textUser_email);
        textUser_mobile = view.findViewById(R.id.textUser_mobile);
        textUser_address = view.findViewById(R.id.textUser_address);


        getUserById();
    }
    private void getUserById() {
        int id = getContext().getSharedPreferences("VENUEBOOKING", Context.MODE_PRIVATE).getInt("User_id",0);
        RetrofitClient.getInstance().getApi().getUserById(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().getAsJsonObject().get("status").getAsString().equals("success")){
                    JsonObject object = response.body().getAsJsonObject().get("data").getAsJsonArray().get(0).getAsJsonObject();
                    textUser_name.setText("Name : "+object.get("User_name").getAsString());
                    textUser_email.setText("Email : "+object.get("User_email").getAsString());
                    textUser_mobile.setText("Mobile : "+object.get("User_contact").getAsString());
                    textUser_address.setText("Address : "+object.get("User_address").getAsString());

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
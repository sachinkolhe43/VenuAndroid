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


    TextView textUserName,textUserEmail,textUserContact,textUserAddress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textUserName = view.findViewById(R.id.textUserName);
        textUserEmail = view.findViewById(R.id.textUserEmail);
        textUserContact = view.findViewById(R.id.textUserContact);
        textUserAddress = view.findViewById(R.id.textUserAddress);

        getUserById();
    }

    private void getUserById() {
        int id = getContext().getSharedPreferences("mobileStore", Context.MODE_PRIVATE).getInt("uid",1);
        RetrofitClient.getInstance().getApi().getUserById(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().getAsJsonObject().get("status").getAsString().equals("success")){
                    JsonObject object = response.body().getAsJsonObject().get("data").getAsJsonArray().get(0).getAsJsonObject();
                    textUserName.setText("Name : "+object.get("User_name").getAsString());
                    textUserEmail.setText("Email : "+object.get("User_email").getAsString());
                    textUserContact.setText("Contact : "+object.get("User_contact").getAsString());
                    textUserAddress.setText("Address : "+object.get("User_address").getAsString());

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
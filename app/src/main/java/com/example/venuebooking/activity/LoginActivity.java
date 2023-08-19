package com.example.venuebooking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.venuebooking.R;
import com.example.venuebooking.entity.User;
import com.example.venuebooking.utils.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    EditText editemail, editpass;
    CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editemail = findViewById(R.id.editemail);
        editpass = findViewById(R.id.editpass);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);
    }

    public void login(View view) {
        User user = new User();
        user.setUser_email(editemail.getText().toString());
        user.setUser_pass(editpass.getText().toString());

        if (checkBoxRememberMe.isChecked())
            getSharedPreferences("mobileStore", MODE_PRIVATE).edit().putBoolean("login_status", true).apply();

        RetrofitClient.getInstance().getApi().loginUser(user).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonArray array = response.body().getAsJsonObject().get("data").getAsJsonArray();
                if (array.size() != 0) {
                    JsonObject object = array.get(0).getAsJsonObject();
                    getSharedPreferences("Venuebooking", MODE_PRIVATE).edit()
                            .putInt("User_id", object.get("User_id").getAsInt()).apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}


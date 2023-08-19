package com.example.venuebooking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.venuebooking.R;
import com.example.venuebooking.entity.User;
import com.example.venuebooking.utils.RetrofitClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText editUser_name,editUser_email,editUser_pass,editConfirmPassword,editUser_contact,editUser_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editUser_name = findViewById(R.id.editUser_name);
        editUser_email = findViewById(R.id.editUser_email);
        editUser_pass = findViewById(R.id.editUser_pass);
        editUser_contact = findViewById(R.id.editUser_contact);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
        editUser_address = findViewById(R.id.editUser_address);
    }
    public void register(View view){
        User users = validateUser();
        if(users!=null){
            RetrofitClient.getInstance().getApi().registerUser(users).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(response.body().getAsJsonObject().get("status").getAsString().equals("success"))
                    {
                        Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

    private User validateUser() {
        String password = editUser_pass.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();
        if(password.equals(confirmPassword))
        {
            User users = new User();
            users.setUser_name(editUser_name.getText().toString());
            users.setUser_pass(password);
            users.setUser_email(editUser_email.getText().toString());
            users.setUser_contact(editUser_contact.getText().toString());
            users.setUser_address(editUser_address.getText().toString());
            return users;
        }
        else {
            Toast.makeText(this, "passwords do not match", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void cancel(View view){

    }

}







package com.example.venuebooking.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.venuebooking.R;
import com.example.venuebooking.adapter.VenueFragmentAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
      Toolbar toolbar;
   ViewPager2 viewPager2;
   TabLayout tabLayout;
    VenueFragmentAdapter venueFragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Main:","Oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        viewPager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tabLayout);
        setSupportActionBar(toolbar);
       venueFragmentAdapter = new VenueFragmentAdapter(this);
       viewPager2.setAdapter(venueFragmentAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setIcon(R.drawable.list);
                        break;
                   case 1:
                        tab.setIcon(R.drawable.order);
                        break;
                   case 2:
                       tab.setIcon(R.drawable.profile);
                       break;

               }
            }
       }).attach();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("Main:","onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
   }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("Main:","onOptionsItemSelected");
        getSharedPreferences("VENUEBOOKING",MODE_PRIVATE).edit().putBoolean("login_status",false).apply();finish();
        return super.onOptionsItemSelected(item);
   }
    }






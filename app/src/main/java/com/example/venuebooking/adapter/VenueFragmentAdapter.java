package com.example.venuebooking.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.venuebooking.fragment.OrderFragment;
import com.example.venuebooking.fragment.ProfileFragment;
import com.example.venuebooking.fragment.VenueListFragment;

public class VenueFragmentAdapter extends FragmentStateAdapter {


    public VenueFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new VenueListFragment();
            case 1:
                return new OrderFragment();
            case 2:
                return new ProfileFragment();
        }
        return null;
    }




    @Override
    public int getItemCount() {
        return 3;
    }
}

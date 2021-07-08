package com.example.a1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int i) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment returnValue = null;
        switch(position){
            case 0 :
                returnValue = new seoulFragment();
                break;
            case 1 :
                returnValue =  new DaegeonFragment();
                break;
            case 2 :
                returnValue =  new BusanFragment();
                break;
        }
        return returnValue;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0:
                return "Seoul";
            case 1:
                return "Daegu";
            case 2:
                return "Busan";
        }
        return super.getPageTitle(position);
    }
}

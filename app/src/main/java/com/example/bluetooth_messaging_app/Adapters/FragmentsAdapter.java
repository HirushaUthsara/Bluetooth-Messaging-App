package com.example.bluetooth_messaging_app.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bluetooth_messaging_app.Fragments.DirectChatsFragment;
import com.example.bluetooth_messaging_app.Fragments.GroupsFragment;

public class FragmentsAdapter extends FragmentPagerAdapter {
    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new DirectChatsFragment();
            case 1: return new GroupsFragment();
            default:return new DirectChatsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position==0){
            title = "DIRECT CHATS";
        }
        if(position==1){
            title = "GROUPS";
        }
        return title;
    }
}

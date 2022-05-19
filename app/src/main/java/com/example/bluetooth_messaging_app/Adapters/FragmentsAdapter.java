package com.example.bluetooth_messaging_app.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bluetooth_messaging_app.Fragments.DirectChatsFragment;
import com.example.bluetooth_messaging_app.Fragments.GroupsFragment;

public class FragmentsAdapter extends FragmentPagerAdapter {
    //this class holds the two fragments of the main layout
    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){       //Here the particular layout is selected between Direct chat and the Groups
            case 0: return new DirectChatsFragment();       //Direct chats layout
            case 1: return new GroupsFragment();            //Group chats layout
            default:return new DirectChatsFragment();       //Direct chats layout
        }
    }

    @Override
    public int getCount() {
        return 2;
    }   //Number of layouts

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;    //Title of the layout is selected
        if(position==0){
            title = "DIRECT CHATS";     //Direct chats
        }
        if(position==1){
            title = "GROUPS";           //Groups
        }
        return title;
    }
}

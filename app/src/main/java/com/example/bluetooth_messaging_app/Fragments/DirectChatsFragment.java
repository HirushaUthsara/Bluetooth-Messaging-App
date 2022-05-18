package com.example.bluetooth_messaging_app.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bluetooth_messaging_app.Adapters.UserAdapter;
import com.example.bluetooth_messaging_app.Models.Users;
import com.example.bluetooth_messaging_app.R;
import com.example.bluetooth_messaging_app.databinding.FragmentDirectChatsBinding;

import java.util.ArrayList;


public class DirectChatsFragment extends Fragment {


    public DirectChatsFragment() {
        // Required empty public constructor
    }

    FragmentDirectChatsBinding binding;
    ArrayList<Users> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentDirectChatsBinding.inflate(inflater, container, false);

        //I should take the arraylist from the database using a methaod and assignit to the list

        Users u1 = new Users("A1",1,"Chiran","Hi");
        Users u2 = new Users("A2",2,"Hirusha","Hello");
        Users u3 = new Users("A3",3,"Kalum","wrvw");
        Users u4 = new Users("A4",4,"Wimal","brb");
        Users u6 = new Users("A5",5,"Sahan",",ujm yh");
        Users u7 = new Users("A6",2,"Charith","ehth");
        Users u8 = new Users("A7",3,"Malith","nerts");

        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u6);
        list.add(u7);
        list.add(u8);


        UserAdapter adapter = new UserAdapter(0,list,getContext());
        binding.chatRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        //2:18:09
        return binding.getRoot();
    }
}
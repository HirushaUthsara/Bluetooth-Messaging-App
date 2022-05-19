package com.example.bluetooth_messaging_app.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bluetooth_messaging_app.Adapters.UserAdapter;
import com.example.bluetooth_messaging_app.DBHelper;
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

        DBHelper db = new DBHelper(this.getContext());
        list = db.loadAllUsers();
        //I should take the arraylist from the database using a methaod and assign it to the list
        //Instead of that i created some sample data here

        //Sending data to the corresponding chat rows
        UserAdapter adapter = new UserAdapter(0,list,getContext());
        binding.chatRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        //2:18:09
        return binding.getRoot();
    }
}
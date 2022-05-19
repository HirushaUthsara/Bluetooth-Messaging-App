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
import com.example.bluetooth_messaging_app.databinding.FragmentGroupsBinding;

import java.util.ArrayList;


public class GroupsFragment extends Fragment {

    public GroupsFragment() {
        // Required empty public constructor
    }

    FragmentGroupsBinding binding;
    ArrayList<Users> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentGroupsBinding.inflate(inflater, container, false);

        Users u1 = new Users("A1",1,"Colombo","Hi");
        Users u2 = new Users("A1",2,"Kalutara","Hello");
        Users u3 = new Users("A1",3,"Jaffna","wrvw");
        Users u4 = new Users("A1",4,"Galle","brb");
        Users u6 = new Users("A1",5,"Matara",",ujm yh");
        Users u7 = new Users("A1",1,"Gampaha","ehth");
        Users u8 = new Users("A1",2,"Cricket","nerts");
        Users u9 = new Users("A1",1,"Colombo","Hi");
        Users u10 = new Users("A1",2,"Temple","Hello");
        Users u11 = new Users("A1",3,"Gang","wrvw");
        Users u12 = new Users("A1",4,"Basketball","brb");
        Users u13 = new Users("A1",5,"Matara",",ujm yh");
        Users u14 = new Users("A1",1,"Gampaha","ehth");
        Users u15 = new Users("A1",2,"Cricket","nerts");

        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u6);
        list.add(u7);
        list.add(u8);
        list.add(u9);
        list.add(u10);
        list.add(u11);
        list.add(u12);
        list.add(u13);
        list.add(u14);
        list.add(u15);


        UserAdapter adapter = new UserAdapter(1,list,getContext());
        binding.GroupchatRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.GroupchatRecyclerView.setLayoutManager(layoutManager);

        //2:18:09

        return binding.getRoot();
    }
}
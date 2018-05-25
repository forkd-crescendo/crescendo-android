package com.forkd.crescendo.viewcontrollers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.User;
import com.forkd.crescendo.network.CrescendoApi;
import com.forkd.crescendo.viewcontrollers.adapters.FavoritesAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    private List<User> users;
    private RecyclerView usersRecyclerView;
    private RecyclerView.LayoutManager usersLayoutManager;
    private FavoritesAdapter usersAdapter;

    private String JWT;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        JWT = getArguments().getString("jwt");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        users = new ArrayList<>();
        usersRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_favorites);
        usersAdapter = new FavoritesAdapter(users);
        usersLayoutManager = new LinearLayoutManager(view.getContext());
        usersRecyclerView.setAdapter(usersAdapter);
        usersRecyclerView.setLayoutManager(usersLayoutManager);
        updateData();
        return view;
    }

    private void updateData() {
        AndroidNetworking
                .get(CrescendoApi.getUsers())
                .addHeaders("Accept", "application/json")
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Authorization", JWT)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            users = User.Builder
                                    .from(response.getJSONArray("data"))
                                    .buildAll();

                            usersAdapter.setUsers(users);
                            usersAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {

                    }
                });
        }


    }

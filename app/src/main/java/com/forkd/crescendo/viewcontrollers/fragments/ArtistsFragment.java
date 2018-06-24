package com.forkd.crescendo.viewcontrollers.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.User;
import com.forkd.crescendo.network.CrescendoApi;
import com.forkd.crescendo.viewcontrollers.activities.LoginActivity;
import com.forkd.crescendo.viewcontrollers.adapters.FavoritesAdapter;
import com.forkd.crescendo.viewcontrollers.adapters.UsersAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistsFragment extends Fragment {

    private List<User> users;
    private RecyclerView usersRecyclerView;
    private RecyclerView.LayoutManager usersLayoutManager;
    private UsersAdapter usersAdapter;

    private String JWT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences mPrefs = getActivity().getSharedPreferences("jwt", 0);
        JWT = mPrefs.getString("jwt", "");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artists, container, false);
        users = new ArrayList<>();
        usersRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_artist);
        usersAdapter = new UsersAdapter(users);
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
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        users = User.Builder.from(response).buildAll();
                        usersAdapter.setUsers(users);
                        usersAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("CrescendoAppFail", anError.getErrorDetail());
                        Toast.makeText(getContext(), "Error de conexión.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

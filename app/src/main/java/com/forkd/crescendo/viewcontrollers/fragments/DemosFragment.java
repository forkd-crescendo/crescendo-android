package com.forkd.crescendo.viewcontrollers.fragments;

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
import com.forkd.crescendo.models.Artwork;
import com.forkd.crescendo.models.User;
import com.forkd.crescendo.network.CrescendoApi;
import com.forkd.crescendo.viewcontrollers.adapters.DemosAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DemosFragment extends Fragment {

   private List<Artwork> demos;
    private RecyclerView demosRecyclerView;
    private RecyclerView.LayoutManager demosLayoutManager;
    private DemosAdapter demosAdapter;

    private String JWT;

    public DemosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences mPrefs = getActivity().getSharedPreferences("jwt", 0);
        JWT = mPrefs.getString("jwt", "");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demos, container, false);
        demos = new ArrayList<>();
        demosRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_demos);
        demosAdapter = new DemosAdapter(demos);
        demosLayoutManager = new LinearLayoutManager(view.getContext());
        demosRecyclerView.setAdapter(demosAdapter);
        demosRecyclerView.setLayoutManager(demosLayoutManager);
        updateData();
        return view;
    }

    private void updateData() {
        AndroidNetworking
                .get(CrescendoApi.getUserArtworks())
                .addHeaders("Accept", "application/json")
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Authorization", JWT)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        demos = Artwork.Builder.from(response).buildAll();
                        demosAdapter.setDemos(demos);
                        demosAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("CrescendoAppFail", anError.getErrorDetail());
                        Toast.makeText(getContext(), "Error de conexión.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

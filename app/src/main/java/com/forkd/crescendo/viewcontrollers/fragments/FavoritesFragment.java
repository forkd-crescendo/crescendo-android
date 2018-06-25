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
import android.widget.TextView;
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
import com.forkd.crescendo.viewcontrollers.adapters.FavoritesAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    private List<Artwork> artworks;
    private TextView noFavoritesText;
    private RecyclerView artworksRecyclerView;
    private RecyclerView.LayoutManager artworksLayoutManager;
    private FavoritesAdapter artworksAdapter;

    private String JWT;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences mPrefs = getActivity().getSharedPreferences("jwt", 0);
        JWT = mPrefs.getString("jwt", "");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        artworks = new ArrayList<>();
        noFavoritesText = (TextView) view.findViewById(R.id.no_favorites_text);
        artworksRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_favorites);
        artworksAdapter = new FavoritesAdapter(artworks);
        artworksLayoutManager = new LinearLayoutManager(view.getContext());
        artworksRecyclerView.setAdapter(artworksAdapter);
        artworksRecyclerView.setLayoutManager(artworksLayoutManager);
        updateData();

        return view;
    }

    private void updateData() {
        AndroidNetworking
                .get(CrescendoApi.getUserFavoriteArtworks())
                .addHeaders("Accept", "application/json")
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Authorization", JWT)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        artworks = Artwork.Builder.from(response).buildAll();
                        artworksAdapter.setArtworks(artworks);
                        artworksAdapter.notifyDataSetChanged();
                        if (artworks.size() == 0) {
                            noFavoritesText.setVisibility(View.VISIBLE);
                        } else {
                            noFavoritesText.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("CrescendoAppFail", anError.getErrorDetail());
                        Toast.makeText(getContext(), "Error de conexión.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

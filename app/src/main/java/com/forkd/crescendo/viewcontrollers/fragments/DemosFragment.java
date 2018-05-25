package com.forkd.crescendo.viewcontrollers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.Artwork;
import com.forkd.crescendo.network.CrescendoApi;
import com.forkd.crescendo.viewcontrollers.adapters.ArtworksAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DemosFragment extends Fragment {

    private RecyclerView demosRecyclerView;
    private ArtworksAdapter artworksAdapter;
    private RecyclerView.LayoutManager artworkLayoutManager;
    private List<Artwork> artworks;

    private String JWT;

    public DemosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_demos, container, false);
        demosRecyclerView=(RecyclerView) view.findViewById(R.id.recycler_demos);
        artworks=new ArrayList<>();
        artworksAdapter=new ArtworksAdapter(artworks);
        //La orientación 1:vertical true:los diseños van de principio a fin
        artworkLayoutManager=new LinearLayoutManager(getContext(),1,true);
        demosRecyclerView.setAdapter(artworksAdapter);
        demosRecyclerView.setLayoutManager(artworkLayoutManager);
        updateData();
        return view;
    }
    private void updateData(){

    }

}

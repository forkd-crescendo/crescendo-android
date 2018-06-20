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
import com.forkd.crescendo.models.Artwork;
import com.forkd.crescendo.models.User;
import com.forkd.crescendo.network.CrescendoApi;
import com.forkd.crescendo.viewcontrollers.adapters.DemosAdapter;

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

    private boolean MOCK_DATA = true;
    private ArrayList<Artwork> DEMOS_MOCK = new ArrayList<Artwork>();

    public DemosFragment() {
        DEMOS_MOCK.add(new Artwork("R29Pq23T6xE", "Top 5 Covers of MARCH 2018 | Best Cover Songs 2018", "Subscribe! http://smarturl.it/SubCoverNation Click That Bell to Turn On Notifications Covers performed by: Emma Heesters ( Friends - Marshmello ...", "https://i.ytimg.com/vi/R29Pq23T6xE/default.jpg"));
        DEMOS_MOCK.add(new Artwork("3G8CM-6BZC4", "Perfect - Ed Sheeran & Beyoncé (Boyce Avenue acoustic cover) on Spotify & Apple", "Our friends at MVMT are offering $15 off any purchase at http://mvmt.com/boyce - just use the code \"BOYCE\" :) Tickets + VIP Meet & Greets: ...", "https://i.ytimg.com/vi/3G8CM-6BZC4/default.jpg"));
        DEMOS_MOCK.add(new Artwork("MhQKe-aERsU", "Ed Sheeran - Shape Of You ( cover by J.Fla )", "My New album is out Now! Listen to Rose: http://bit.ly/JflacompleteworksonSpotify http://bit.ly/JFlaRoseAppleMusic http://bit.ly/jflaRoseonSpotify ...", "https://i.ytimg.com/vi/MhQKe-aERsU/default.jpg"));
        DEMOS_MOCK.add(new Artwork("39_OmBO9jVg", "All of Me - John Legend Cover (Luciana Zogbi)", "All of Me - John Legend (Cover by Luciana Zogbi) Available on Itunes and Spotify Itunes: ...", "https://i.ytimg.com/vi/39_OmBO9jVg/default.jpg"));
        DEMOS_MOCK.add(new Artwork("WsptdUFthWI", "Closer - The Chainsmokers ft. Halsey (Boyce Avenue ft. Sarah Hyland cover) on Spotify & Apple", "Tickets + VIP Meet & Greets: http://smarturl.it/BATour Spotify: http://smarturl.it/CSV4Spotify1 Apple: http://smarturl.it/CSV4Apple1 iTunes: ...", "https://i.ytimg.com/vi/WsptdUFthWI/default.jpg"));
        DEMOS_MOCK.add(new Artwork("i1R4R84-EPA", "Camila Cabello - Havana ( cover by J.Fla )", "My New album is out today! Listen to Rose: http://bit.ly/JflacompleteworksonSpotify http://bit.ly/JFlaRoseAppleMusic http://bit.ly/jflaRoseonSpotify ...", "https://i.ytimg.com/vi/i1R4R84-EPA/default.jpg"));
        DEMOS_MOCK.add(new Artwork("jPulLuBRfWQ", "No te creas tan importante- El bebeto (Cover by Xandra Garsem)", "SOCIAL: http://facebook.com/xandragarsem http://instagram.com/xandragarsem http://twitter.com/xandragarsem VISIT MY BLOG: http://bonjourxandra.com.", "https://i.ytimg.com/vi/jPulLuBRfWQ/default.jpg"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        if (MOCK_DATA) {
            demos = DEMOS_MOCK;
            demosAdapter.setDemos(demos);
            demosAdapter.notifyDataSetChanged();
            return;
        }
    }
}

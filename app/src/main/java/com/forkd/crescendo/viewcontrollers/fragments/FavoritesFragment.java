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
import com.forkd.crescendo.viewcontrollers.adapters.FavoritesAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    private List<Artwork> artworks;
    private RecyclerView artworksRecyclerView;
    private RecyclerView.LayoutManager artworksLayoutManager;
    private FavoritesAdapter artworksAdapter;

    User user;
    private String JWT;

    private boolean MOCK_DATA = true;
    private ArrayList<Artwork> ARTWORKS_MOCK = new ArrayList<Artwork>();

    public FavoritesFragment() {
        ARTWORKS_MOCK.add(new Artwork("MQoW9xifQi0", "Metallica 2018-04-28 Cracow, Tauron Arena, Poland - Wehikuł Czasu (Dżem cover)(4K 2160p)", "Metallica 2018-04-28 Kraków, Tauron Arena, Polska (4K 2160p) Wehikuł Czasu (Dżem cover)", "https://i.ytimg.com/vi/MQoW9xifQi0/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("Jl8fV1jUQPs", "Crazy In Love (Beyoncé Cover)", "50 Shades Of Grey Trailer Song - Crazy In Love (Beyoncé Cover) Spotify: http://open.spotify.com/track/7jOvEsDIjHRH0LwCkwZSHS iTunes: ...", "https://i.ytimg.com/vi/Jl8fV1jUQPs/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("ASAzwmORUJk", "Despacito - Luis Fonsi, Daddy Yankee ft. Justin Bieber (Madilyn Bailey & Leroy Sanchez Cover)", "Listen to my new single TETRIS everywhere! - http://red.lnk.to/Tetris Stream on Spotify - https://open.spotify.com/album/0B75OuY7kuQXfz7qF5r2l3 Download on ...", "https://i.ytimg.com/vi/ASAzwmORUJk/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("dSYtf_sJRHo", "CRISP - ЮТУБ БЭЙБИ ( GUF cover )", "Как и обещал - лицо Криспа в новом ( первом ) клипе \"ЮТУБ БЭЙБИ\" на канале. Это не DISS CHALENGE НА YOUTUBE Запись и сведе...", "https://i.ytimg.com/vi/dSYtf_sJRHo/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("RsSftfKzRPM", "Attention by Charlie Puth | Alex Aiono and Sabrina Carpenter Cover", "Can we get this cover Attention from Charlie Puth to 80000 likes? THANKS SABRINA for jumping in the video!!! And you can also listen to Question here: ...", "https://i.ytimg.com/vi/RsSftfKzRPM/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("iPf_ifCKftc", "Shawn Mendes - Psycho (Post Malone cover) in the Live Lounge", "Shawn Mendes covers Post Malone's Psycho in the BBC Radio 1 Live Lounge.", "https://i.ytimg.com/vi/iPf_ifCKftc/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("bRRn6FXf6vg", "Cô bé Cover 1 Phút quá hay tại quán Trà Đá Huyền Thoại - Mỹ Hồng", "Cô bé Cover 1 Phút quá hay tại quán Trà Đá Huyền Thoại - Mỹ Hồng [KHOI MY TUBE] MỘT PHÚT - KHỞI MY: https://youtu.be/wN_goJznt54 Dịch vụ quay phim, ...", "https://i.ytimg.com/vi/bRRn6FXf6vg/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("lGbzyKWkiio", "TÌNH ĐƠN PHƯƠNG ACOUSTIC COVER - Edward Duong Nguyen Ft Tùng Acoustic", "TÌNH ĐƠN PHƯƠNG ACOUSTIC COVER   Một sản phẩm cover kết hợp giữa Edward Dương Nguyễn và Tùng Acoustic. Tán gái chưa bao giờ là đơn giản các ...", "https://i.ytimg.com/vi/lGbzyKWkiio/default.jpg"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        JWT = getArguments().getString("jwt");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        artworks = new ArrayList<>();
        artworksRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_favorites);
        artworksAdapter = new FavoritesAdapter(artworks);
        artworksLayoutManager = new LinearLayoutManager(view.getContext());
        artworksRecyclerView.setAdapter(artworksAdapter);
        artworksRecyclerView.setLayoutManager(artworksLayoutManager);
        updateData();
        return view;
    }

    private void updateData() {
        if (MOCK_DATA) {
            artworks = ARTWORKS_MOCK;
            artworksAdapter.setArtworks(artworks);
            artworksAdapter.notifyDataSetChanged();
            return;
        }

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
                }
                @Override
                public void onError(ANError anError) {

                }
            });
    }
}

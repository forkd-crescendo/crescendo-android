package com.forkd.crescendo.viewcontrollers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.Artwork;
import com.forkd.crescendo.models.User;
import com.forkd.crescendo.viewcontrollers.adapters.ArtworksAdapter;

import java.util.ArrayList;
import java.util.List;

public class ArtistDetailActivity extends AppCompatActivity {

    private List<Artwork> artworks;
    private ANImageView imageUser;
    private TextView textName;
    private TextView musicRole;
    private TextView musicGenre;
    private RecyclerView artworksRecyclerView;
    private RecyclerView.LayoutManager artworkssLayoutManager;
    private ArtworksAdapter artworksAdapter;

    User user;

    private boolean MOCK_DATA = true;
    private ArrayList<Artwork> ARTWORKS_MOCK = new ArrayList<Artwork>();

    public ArtistDetailActivity() {
        ARTWORKS_MOCK.add(new Artwork("R29Pq23T6xE", "Top 5 Covers of MARCH 2018 | Best Cover Songs 2018", "Subscribe! http://smarturl.it/SubCoverNation Click That Bell to Turn On Notifications Covers performed by: Emma Heesters ( Friends - Marshmello ...", "https://i.ytimg.com/vi/R29Pq23T6xE/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("3G8CM-6BZC4", "Perfect - Ed Sheeran & Beyoncé (Boyce Avenue acoustic cover) on Spotify & Apple", "Our friends at MVMT are offering $15 off any purchase at http://mvmt.com/boyce - just use the code \"BOYCE\" :) Tickets + VIP Meet & Greets: ...", "https://i.ytimg.com/vi/3G8CM-6BZC4/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("MhQKe-aERsU", "Ed Sheeran - Shape Of You ( cover by J.Fla )", "My New album is out Now! Listen to Rose: http://bit.ly/JflacompleteworksonSpotify http://bit.ly/JFlaRoseAppleMusic http://bit.ly/jflaRoseonSpotify ...", "https://i.ytimg.com/vi/MhQKe-aERsU/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("39_OmBO9jVg", "All of Me - John Legend Cover (Luciana Zogbi)", "All of Me - John Legend (Cover by Luciana Zogbi) Available on Itunes and Spotify Itunes: ...", "https://i.ytimg.com/vi/39_OmBO9jVg/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("WsptdUFthWI", "Closer - The Chainsmokers ft. Halsey (Boyce Avenue ft. Sarah Hyland cover) on Spotify & Apple", "Tickets + VIP Meet & Greets: http://smarturl.it/BATour Spotify: http://smarturl.it/CSV4Spotify1 Apple: http://smarturl.it/CSV4Apple1 iTunes: ...", "https://i.ytimg.com/vi/WsptdUFthWI/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("i1R4R84-EPA", "Camila Cabello - Havana ( cover by J.Fla )", "My New album is out today! Listen to Rose: http://bit.ly/JflacompleteworksonSpotify http://bit.ly/JFlaRoseAppleMusic http://bit.ly/jflaRoseonSpotify ...", "https://i.ytimg.com/vi/i1R4R84-EPA/default.jpg"));
        ARTWORKS_MOCK.add(new Artwork("jPulLuBRfWQ", "No te creas tan importante- El bebeto (Cover by Xandra Garsem)", "SOCIAL: http://facebook.com/xandragarsem http://instagram.com/xandragarsem http://twitter.com/xandragarsem VISIT MY BLOG: http://bonjourxandra.com.", "https://i.ytimg.com/vi/jPulLuBRfWQ/default.jpg"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent == null) return;

        user = User.Builder.from(intent.getExtras()).build();

        artworks = new ArrayList<>();

        artworksRecyclerView = (RecyclerView) findViewById(R.id.artworks_recycler);
        imageUser = (ANImageView) findViewById(R.id.image_user);
        textName = (TextView) findViewById(R.id.text_name);
        musicRole = (TextView) findViewById(R.id.text_music_role);
        musicGenre = (TextView) findViewById(R.id.text_music_genre);

        artworksAdapter = new ArtworksAdapter(artworks);
        artworkssLayoutManager = new LinearLayoutManager(this);
        artworksRecyclerView.setAdapter(artworksAdapter);
        artworksRecyclerView.setLayoutManager(artworkssLayoutManager);

        updateViews(user);
    }

    private void updateViews(final User user) {
        imageUser.setDefaultImageResId(R.mipmap.ic_launcher);
        imageUser.setErrorImageResId(R.mipmap.ic_launcher);
        imageUser.setImageUrl(user.getPhoto());

        textName.setText(user.getName());
        musicRole.setText(user.getRole());
        musicGenre.setText(user.getGenre());

        if (MOCK_DATA) {
            artworks = ARTWORKS_MOCK;
            artworksAdapter.setArtworks(artworks);
            artworksAdapter.notifyDataSetChanged();
        }
    }

}

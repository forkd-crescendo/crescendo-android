package com.forkd.crescendo.viewcontrollers.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.widget.ANImageView;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.Artwork;
import com.forkd.crescendo.models.User;
import com.forkd.crescendo.network.CrescendoApi;
import com.forkd.crescendo.viewcontrollers.adapters.ArtworksAdapter;

import org.json.JSONArray;

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
    private String JWT;

    public ArtistDetailActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences mPrefs = getSharedPreferences("jwt", 0);
        JWT = mPrefs.getString("jwt", "");

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

        AndroidNetworking
                .get(CrescendoApi.getArtworks(user.getId()))
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
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("CrescendoAppFail", anError.getErrorDetail());
                        Toast.makeText(getApplicationContext(), "Error de conexión.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}

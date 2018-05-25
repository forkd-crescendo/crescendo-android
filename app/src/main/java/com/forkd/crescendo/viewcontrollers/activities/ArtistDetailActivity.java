package com.forkd.crescendo.viewcontrollers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.User;

public class ArtistDetailActivity extends AppCompatActivity {

    private ANImageView imageUser;
    private TextView textName;
    private TextView musicRole;
    private TextView musicGenre;
    private TextView artworksCount;
    private TextView followersCount;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent == null) return;

        user = User.Builder.from(intent.getExtras()).build();

        imageUser = (ANImageView) findViewById(R.id.image_user);
        textName = (TextView) findViewById(R.id.text_name);
        musicRole = (TextView) findViewById(R.id.text_music_role);
        musicGenre = (TextView) findViewById(R.id.text_music_genre);
        artworksCount = (TextView) findViewById(R.id.text_artwoks_count);
        followersCount = (TextView) findViewById(R.id.text_followers_count);

        updateViews(user);

    }

    private void updateViews(final User user) {
        imageUser.setDefaultImageResId(R.mipmap.ic_launcher);
        imageUser.setErrorImageResId(R.mipmap.ic_launcher);
        imageUser.setImageUrl(user.getAvatar());

        textName.setText(user.getName());
        musicRole.setText(user.getMusicRole());
        musicGenre.setText(user.getMusicGenre());
        artworksCount.setText(String.valueOf(user.getFollowerCount()));
        followersCount.setText(String.valueOf(user.getFollowerCount()));
    }

}

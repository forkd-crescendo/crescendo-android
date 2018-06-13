package com.forkd.crescendo.viewcontrollers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

        updateViews(user);

    }

    private void updateViews(final User user) {
        imageUser.setDefaultImageResId(R.mipmap.ic_launcher);
        imageUser.setErrorImageResId(R.mipmap.ic_launcher);
        imageUser.setImageUrl(user.getAvatar());

        textName.setText(user.getName());
        musicRole.setText(user.getMusicRole());
        musicGenre.setText(user.getMusicGenre());
    }

}

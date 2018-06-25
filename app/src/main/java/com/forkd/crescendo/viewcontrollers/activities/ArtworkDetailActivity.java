package com.forkd.crescendo.viewcontrollers.activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.forkd.crescendo.R;
import com.forkd.crescendo.models.Artwork;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class ArtworkDetailActivity extends YouTubeBaseActivity {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;

    private Artwork artwork;
    private TextView artworkTitle;
    private TextView artworkDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_detail);

        final Activity activity = this;

        Intent intent = getIntent();
        if (intent == null) return;
        artwork = Artwork.Builder.from(intent.getExtras()).build();

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        artworkTitle = (TextView) findViewById(R.id.artwork_detail_title);
        artworkDescription = (TextView) findViewById(R.id.artwork_detail_description);

        youTubeView.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
                if (!wasRestored) {
                    player.loadVideo(artwork.getVideoId());
                    player.play();
                }
            }

            @Override
            public void onInitializationFailure(Provider provider, YouTubeInitializationResult error) {
                if (error.isUserRecoverableError()) {
                    error.getErrorDialog(activity, RECOVERY_REQUEST).show();
                } else {
                    String errorMessage = String.format(getString(R.string.player_error), error.toString());
                    Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        });

        artworkTitle.setText(artwork.getTitle());
        artworkDescription.setText(artwork.getDescription());
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}
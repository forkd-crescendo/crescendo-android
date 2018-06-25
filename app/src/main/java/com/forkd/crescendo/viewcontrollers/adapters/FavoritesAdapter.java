package com.forkd.crescendo.viewcontrollers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.forkd.crescendo.R;
import com.forkd.crescendo.models.Artwork;
import com.forkd.crescendo.models.User;
import com.forkd.crescendo.viewcontrollers.activities.ArtistDetailActivity;
import com.forkd.crescendo.viewcontrollers.activities.ArtworkDetailActivity;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private List<Artwork> artworks;

    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false));
    }

    @Override
    public void onBindViewHolder(FavoritesAdapter.ViewHolder holder, int position) {
        holder.updatesViews(artworks.get(position));
    }

    @Override
    public int getItemCount() {
        return artworks.size();
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public FavoritesAdapter setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
        return this;
    }

    public FavoritesAdapter() {

    }

    public FavoritesAdapter(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView artworkThumbnail;
        private TextView artworkTitle;
        private TextView artworkArtist;
        private ConstraintLayout artworkLayout;
        public ViewHolder(View view) {
            super(view);
            artworkThumbnail = (ANImageView) view.findViewById(R.id.item_favorite_thumbnail);
            artworkTitle = (TextView) view.findViewById(R.id.item_favorite_title);
            artworkArtist = (TextView) view.findViewById(R.id.item_favorite_artist);
            artworkLayout = (ConstraintLayout) view.findViewById(R.id.layout_favorite_item);
        }

        public void updatesViews(final Artwork artwork) {
            artworkThumbnail.setDefaultImageResId(R.mipmap.ic_launcher);
            artworkThumbnail.setErrorImageResId(R.mipmap.ic_launcher);
            artworkThumbnail.setImageUrl(artwork.getThumbnail());
            artworkTitle.setText(artwork.getTitle());
            artworkArtist.setText(artwork.getArtist());
            artworkLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    context.startActivity(new Intent(
                            context, ArtworkDetailActivity.class
                    ).putExtras(artwork.toBundle()));
                }
            });
        }
    }
}
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
import com.forkd.crescendo.viewcontrollers.activities.ArtworkDetailActivity;

import java.util.List;

public class ArtworksAdapter extends RecyclerView.Adapter<ArtworksAdapter.ViewHolder> {

    private List<Artwork> artworks;

    @Override
    public ArtworksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
            LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_artwork, parent, false));
    }

    @Override
    public void onBindViewHolder(ArtworksAdapter.ViewHolder holder, int position) {
        holder.updatesViews(artworks.get(position));
    }

     @Override
    public int getItemCount() {
        return artworks.size();
    }

    public List<Artwork> getArtworks() {
        return artworks;
    }

    public ArtworksAdapter setArtworks(List<Artwork> artworks) {
        this.artworks = artworks;
        return this;
    }

    public ArtworksAdapter() {

    }

    public ArtworksAdapter(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView artworkThumbnail;
        private TextView artworkTitle;
        private ConstraintLayout artworkLayout;
        public ViewHolder(View view) {
            super(view);
            artworkThumbnail = (ANImageView) view.findViewById(R.id.image_thumbnail);
            artworkTitle = (TextView) view.findViewById(R.id.text_title_video);
            artworkLayout = (ConstraintLayout) view.findViewById(R.id.layout_artwork_item);
        }

        public void updatesViews(final Artwork artwork) {
            artworkThumbnail.setDefaultImageResId(R.mipmap.ic_launcher);
            artworkThumbnail.setErrorImageResId(R.mipmap.ic_launcher);
            artworkThumbnail.setImageUrl(artwork.getThumbnail());
            artworkTitle.setText(artwork.getTitle());
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

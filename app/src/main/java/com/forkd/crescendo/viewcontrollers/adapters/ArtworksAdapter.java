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
import com.forkd.crescendo.viewcontrollers.activities.ArtistDetailActivity;

import java.util.List;

public class ArtworksAdapter extends RecyclerView.Adapter<ArtworksAdapter.ViewHolder> {

    private List<Artwork> artworks;
    public ArtworksAdapter(List<Artwork> artworks) {
        this.artworks = artworks;
    }

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView artworkThumbnail;
        private TextView artworkTitle;
        private TextView artworkDescription;
        private ConstraintLayout artworkLayout;
        public ViewHolder(View view) {
            super(view);
            artworkThumbnail = (ANImageView) view.findViewById(R.id.image_thumbnail);
            artworkTitle = (TextView) view.findViewById(R.id.text_title_video);
            artworkDescription = (TextView) view.findViewById(R.id.text_description_video);
            artworkLayout = (ConstraintLayout) view.findViewById(R.id.layout_artwork_item);
        }

        public void updatesViews(final Artwork artwork) {
            artworkThumbnail.setDefaultImageResId(R.mipmap.ic_launcher);
            artworkTitle.setText(artwork.getTitle());
            artworkDescription.setText(artwork.getDescription());
            artworkLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    context.startActivity(new Intent(
                            context, ArtistDetailActivity.class
                    ).putExtras(artwork.toBundle()));
                }
            });
        }
    }
}

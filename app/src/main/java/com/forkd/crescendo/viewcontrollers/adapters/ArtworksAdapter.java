package com.forkd.crescendo.viewcontrollers.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.forkd.crescendo.models.Artwork;

import java.util.List;

public class ArtworksAdapter extends RecyclerView.Adapter<ArtworksAdapter.ViewHolder> {

    private List<Artwork> artworks;

    public ArtworksAdapter(List<Artwork> artworks) {
        this.artworks = artworks;
    }

    @Override
    public ArtworksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ArtworksAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

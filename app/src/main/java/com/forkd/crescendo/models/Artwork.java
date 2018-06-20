package com.forkd.crescendo.models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Artwork implements Parcelable{
    private String videoUrl;
    private String title;
    private String description;
    private String thumbnail;

    public Artwork(String videoUrl, String title, String description, String thumbnail) {
        this.videoUrl = videoUrl;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public Artwork() {
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Artwork setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Artwork setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Artwork setTitle(String title) {
        this.title = title;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("videoURL", getVideoUrl());
        bundle.putString("title", getTitle());
        bundle.putString("description", getDescription());
        return bundle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(videoUrl);
        parcel.writeString(title);
        parcel.writeString(description);
    }

    public Artwork(Parcel in){
        videoUrl=in.readString();
        title=in.readString();
        description=in.readString();
    }
    public static final Parcelable.Creator<Artwork> CREATOR=new Parcelable.Creator<Artwork>()
    {

        @Override
        public Artwork createFromParcel(Parcel parcel) {
            return new Artwork(parcel);
        }

        @Override
        public Artwork[] newArray(int i) {
            return new Artwork[i];
        }
    };

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public static class Builder {
        private Artwork artwork;
        private ArrayList<Artwork> artworks;

        public Builder() {
            this.artwork = new Artwork();
            this.artworks = new ArrayList<>();
        }

        public Builder(Artwork artwork) {
            this.artwork = artwork;
        }

        public Builder(ArrayList<Artwork> artworks) {
            this.artworks = artworks;
        }

        public Artwork build() {
            return artwork;
        }

        public ArrayList<Artwork> buildAll() {
            return artworks;
        }

        public static Builder from(Bundle bundle) {
            return new Builder(new Artwork(
                    bundle.getString("videoURL"),
                    bundle.getString("title"),
                    bundle.getString("description"),
                    bundle.getString("thumbnail")));
        }

        public static Builder from(JSONObject jsonArkwork) {
            try {
                return new Builder(new Artwork(
                        jsonArkwork.getString("videoURL"),
                        jsonArkwork.getString("title"),
                        jsonArkwork.getString("description"),
                        jsonArkwork.getString("thumbnail")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Builder from(JSONArray jsonArkworks) {
            int length = jsonArkworks.length();
            ArrayList<Artwork> artworks = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                try {
                    artworks.add(Builder.from(jsonArkworks.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(artworks);
        }
    }
}


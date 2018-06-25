package com.forkd.crescendo.models;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Artwork implements Parcelable{
    private String videoId;
    private String title;
    private String description;
    private String thumbnail;
    private String artist;
    private String id;

    public Artwork(String videoId, String title, String description, String thumbnail, String artist, String id) {
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.artist = artist;
        this.id = id;
    }

    public Artwork() {
    }

    public String getVideoId() {
        return videoId;
    }

    public Artwork setVideoId(String videoId) {
        this.videoId = videoId;
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
        bundle.putString("videoId", getVideoId());
        bundle.putString("title", getTitle());
        bundle.putString("description", getDescription());
        bundle.putString("thumbnail", getThumbnail());
        bundle.putString("artist", getArtist());
        bundle.putString("id", getId());

        return bundle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(videoId);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(thumbnail);
        parcel.writeString(artist);
        parcel.writeString(id);
    }

    public Artwork(Parcel in){
        videoId =in.readString();
        title=in.readString();
        description=in.readString();
        thumbnail=in.readString();
        artist=in.readString();
        id=in.readString();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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
                    bundle.getString("videoId"),
                    bundle.getString("title"),
                    bundle.getString("description"),
                    bundle.getString("thumbnail"),
                    bundle.getString("artist"),
                    bundle.getString("id")));
        }

        public static Builder from(JSONObject jsonArkwork) {
            try {
                return new Builder(new Artwork(
                        jsonArkwork.getString("videoId"),
                        jsonArkwork.getString("title"),
                        jsonArkwork.getString("description"),
                        jsonArkwork.getString("thumbnail"),
                        jsonArkwork.getString("artist"),
                        jsonArkwork.getString("id")));
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


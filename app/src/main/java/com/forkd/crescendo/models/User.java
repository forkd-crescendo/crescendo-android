package com.forkd.crescendo.models;

import android.os.Bundle;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class User {

    private String name;
    private String email;
    private String avatar;
    private String location;
    private String district;
    private String musicRole;
    private String musicGenre;
    private String followerCount;
    private ArrayList<Artwork> artworks=new ArrayList<Artwork>();
    private int age;
    private String id;

    public User(String name, String email, String avatar, String location, String district, String musicRole, String musicGenre, String followerCount, int age, String id) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.location = location;
        this.district = district;
        this.musicRole = musicRole;
        this.musicGenre = musicGenre;
        this.followerCount = followerCount;
        this.age = age;
        this.id = id;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public User setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public User setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public User setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getMusicRole() {
        return musicRole;
    }

    public User setMusicRole(String musicRole) {
        this.musicRole = musicRole;
        return this;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public User setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
        return this;
    }

    public String getFollowerCount() {
        return followerCount;
    }

    public User setFollowerCount(String followerCount) {
        this.followerCount = followerCount;
        return this;
    }

    public ArrayList<Artwork> getArtworks() {
        return artworks;
    }

    public User setArtworks(ArrayList<Artwork> artworks) {
        this.artworks = artworks;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("name",getName());
        bundle.putString("email",getEmail());
        bundle.putString("avatar",getAvatar());
        bundle.putString("location",getLocation());
        bundle.putString("distric",getDistrict());
        bundle.putString("musicRole",getMusicRole());
        bundle.putString("musicGenre",getMusicGenre());
        bundle.putString("followerCount",getFollowerCount());
        bundle.putInt("age",getAge());
        bundle.putString("_id",getId());
        return bundle;
    }
    public static class Builder {
        private User user;
        private List<User> users;

        public Builder(User user, List<User> users) {
            this.user = user;
            this.users = users;
        }

        public Builder(User user) {
            this.user = user;
        }

        public Builder(List<User> users) {
            this.users = users;
        }

        public User build() {
            return user;
        }

        public List<User> buildAll() {
            return users;
        }

        public static Builder from(Bundle bundle) {
            return new Builder(new User(
                    bundle.getString("name"),
                    bundle.getString("email"),
                    bundle.getString("avatar"),
                    bundle.getString("location"),
                    bundle.getString("district"),
                    bundle.getString("musicRole"),
                    bundle.getString("musicGenre"),
                    bundle.getString("followerCount"),
                    bundle.getInt("age"),
                    bundle.getString("_id")));
        }

        public static Builder from(JSONObject jsonUser) {
            try {
                return new Builder(new User(
                        jsonUser.getString("name"),
                        jsonUser.getString("email"),
                        jsonUser.getString("avatar"),
                        jsonUser.getString("location"),
                        jsonUser.getString("district"),
                        jsonUser.getString("musicRole"),
                        jsonUser.getString("musicGenre"),
                        jsonUser.getString("followerCount"),
                        jsonUser.getInt("age"),
                        jsonUser.getString("_id")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Builder from(JSONArray jsonUsers) {
            List<User> users = new ArrayList<>();
            int length = jsonUsers.length();

            for (int i = 0; i < length; i++) {
                try {
                    users.add(Builder.from(jsonUsers.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(users);
        }

    }
}


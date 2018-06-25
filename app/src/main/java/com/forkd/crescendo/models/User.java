package com.forkd.crescendo.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class User {

    private String name;
    private String email;
    private String photo;
    private String role;
    private String genre;
    private ArrayList<Artwork> artworks=new ArrayList<Artwork>();
    private String id;

    public User(String name, String email, String photo, String role, String genre, String id) {
        this.name = name;
        this.email = email;
        this.photo = photo;
        this.role = role;
        this.genre = genre;
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

    public String getPhoto() {
        return photo;
    }

    public User setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public User setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public ArrayList<Artwork> getArtworks() {
        return artworks;
    }

    public User setArtworks(ArrayList<Artwork> artworks) {
        this.artworks = artworks;
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
        bundle.putString("photo", getPhoto());
        bundle.putString("role", getRole());
        bundle.putString("genre", getGenre());
        bundle.putString("id",getId());
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
                    bundle.getString("photo"),
                    bundle.getString("role"),
                    bundle.getString("genre"),
                    bundle.getString("id")));
        }

        public static Builder from(JSONObject jsonUser) {
            try {
                return new Builder(new User(
                        jsonUser.getString("name"),
                        jsonUser.getString("email"),
                        jsonUser.getString("photo"),
                        jsonUser.getString("role"),
                        jsonUser.getString("genre"),
                        jsonUser.getString("id")));
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

    public static class IdBuilder {
        public static String from(JSONObject jsonId) {
            try {
                return jsonId.getString("favourite_id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static List<String> from(JSONArray jsonIds) {
            List<String> ids = new ArrayList<>();
            int length = jsonIds.length();

            for (int i = 0; i < length; i++) {
                try {
                    ids.add(IdBuilder.from(jsonIds.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return ids;
        }
    }
}


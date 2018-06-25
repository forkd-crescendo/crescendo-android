package com.forkd.crescendo.network;

public class CrescendoApi {
    private static String BASE_URL = "http://riverarivas.com/crescendo/";


    public static String authentication() {
        return BASE_URL + "auth/login";
    }
    public static String getUsers() {
        return BASE_URL + "users";
    }
    public static String getArtworks(String userId) {
        return BASE_URL + "users/" + userId + "/artworks";
    }
    public static String getUserArtworks() {
        return BASE_URL + "me/artworks";
    }
    public static String getUserFavoriteArtworks() {
        return BASE_URL + "me/favourites/artworks";
    }
    public static String getFavoritesUsers() {
        return BASE_URL + "me/favourites";
    }
    public static String setUserFavorite() {
        return BASE_URL + "me/favourites";
    }
    public static String deleteUserFavorite(String userId) {
        return BASE_URL + "me/favourites/" + userId;
    }
}

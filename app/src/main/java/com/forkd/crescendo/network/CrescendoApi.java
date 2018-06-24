package com.forkd.crescendo.network;

public class CrescendoApi {
    private static String BASE_URL = "http://192.168.0.10:3000/";


    public static String authentication() {
        return BASE_URL + "auth/login";
    }
    public static String getUsers() {
        return BASE_URL + "users";
    }
    public static String getFavorites() {
        return BASE_URL + "favorites";
    }
}

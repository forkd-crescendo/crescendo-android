package com.forkd.crescendo.network;

public class CrescendoApi {
//    private static String BASE_URL = "https://crescendo-api-tirwrpcnoo.now.sh/";
    private static String BASE_URL = "https://crescendo-app.herokuapp.com/";


    public static String authentication() {
        return BASE_URL + "authentication";
    }
    public static String getUsers() {
        return BASE_URL + "users";
    }
    public static String getFavorites() {
        return BASE_URL + "favorites";
    }
}

package com.example.luba.supergraandroid;

import android.provider.Settings;

final class Config {
    private static String ip = null;
    private static String ANDROID_ID = null;

    static final String PREFIX_IP = "http://";
    static final String API_GET_QUEUE = ":34450/api/queue/GetQueue";
    static final String API_ADD_QUEUE = ":34450/api/queue/AddQueue";
    static final String API_CREATE_CHARACTER = ":34450/api/character/CreateCharacter";
    static final String API_UPDATE_CHARACTER = ":34450/api/character/Update";
    static final String API_GET_CHARACTER = ":34450/api/character/";

    static void setIp(String _ip) {
        ip = _ip;
    }

    static void setAndroidId(String _id) {
        ANDROID_ID = _id;
    }

    static String getAndroidId() {
        return ANDROID_ID;
    }

    static String getApiGetQueue() {
        return (ip != null) ? PREFIX_IP + ip + API_GET_QUEUE : null;
    }

    static String getApiAddQueue() {
        return (ip != null) ? PREFIX_IP + ip + API_ADD_QUEUE : null;
    }

    static String getApiCreateCharacter() {
        return (ip != null) ? PREFIX_IP + ip + API_CREATE_CHARACTER : null;
    }

    static String getApiUpdateCharacter() {
        return (ip != null) ? PREFIX_IP + ip + API_UPDATE_CHARACTER : null;
    }

    static String getApiGetCharacter() {
        return (ip != null) ? PREFIX_IP + ip + API_GET_CHARACTER : null;
    }

}

package com.example.leo.master.Main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.leo.master.Notification.NotificationSocket;

import java.net.URI;
import java.net.URISyntaxException;

public class Common {
//    public static String URL = "http://192.168.43.105:8080/Master";
    public static String URL = "http://192.168.197.13:8080/Master";
    public static final String SOCKET_URI =
            "ws://192.168.197.13:8080/Master/NotificationSocket/";
    public static String user_id = "girl";
    public static NotificationSocket notificationSocket;
    private final static String TAG = "Common";

    public static void connectSocket(Context context) {
        URI uri = null;
        try {
            uri = new URI(SOCKET_URI + user_id);
        } catch (URISyntaxException e) {
            Log.e(TAG, e.toString());
        }
        if (notificationSocket == null) {
            notificationSocket = new NotificationSocket(uri, context);
            notificationSocket.connect();
        }
    }

    public static void disconnectSocket() {
        if (notificationSocket != null) {
            notificationSocket.close();
            notificationSocket = null;
        }
    }



    public static boolean networkConnected(Context context) {
        ConnectivityManager conManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager != null ? conManager.getActiveNetworkInfo() : null;
        return networkInfo != null && networkInfo.isConnected();
    }

}

package io.ashdavies.cumin.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class NetworkUtils {
    private NetworkUtils() {
        throw new IllegalStateException("No instances");
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo network = manager.getActiveNetworkInfo();
        return network != null && network.isConnected();
    }

    public static boolean isConnectedOrConnecting(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo network = manager.getActiveNetworkInfo();
        return network != null && network.isConnectedOrConnecting();
    }
}

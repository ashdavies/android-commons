package io.ashdavies.cumin.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class NetworkUtils {
  private NetworkUtils() {
    throw new IllegalStateException("No instances");
  }

  public static boolean isConnected(Context context) {
    NetworkInfo network = getManager(context).getActiveNetworkInfo();
    return network != null && network.isConnected();
  }

  public static boolean isConnectedOrConnecting(Context context) {
    NetworkInfo network = getManager(context).getActiveNetworkInfo();
    return network != null && network.isConnectedOrConnecting();
  }

  private static ConnectivityManager getManager(Context context) {
    return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
  }
}

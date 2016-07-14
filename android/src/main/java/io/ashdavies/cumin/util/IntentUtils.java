package io.ashdavies.cumin.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

public final class IntentUtils {
  private IntentUtils() {
    throw new IllegalStateException("No instances");
  }

  public static Intent share(String text) {
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, text);
    sendIntent.setType("text/plain");

    return sendIntent;
  }

  public static void showUri(Context context, @NonNull String uri) {
    showUri(context, Uri.parse(uri));
  }

  public static void showUri(Context context, Uri uri) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(uri);

    context.startActivity(intent);
  }

  public static void startActivity(Context context, Class<?> kls) {
    context.startActivity(newIntent(context, kls));
  }

  public static void startActivity(Context context, Class<?> kls, Bundle bundle) {
    context.startActivity(newIntent(context, kls, bundle));
  }

  public static Intent newIntent(Context context, Class<?> kls) {
    return newIntent(context, kls, new Bundle());
  }

  public static Intent newIntent(Context context, Class<?> kls, Bundle bundle) {
    Intent intent = new Intent(context, kls);
    intent.putExtras(bundle);

    return intent;
  }
}

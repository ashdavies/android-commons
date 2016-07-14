package io.ashdavies.cumin;

import android.app.Application;
import android.content.Context;

import org.robolectric.DefaultTestLifecycle;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.TestLifecycleApplication;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;

import java.lang.reflect.Method;

public class MockApplication extends Application implements TestLifecycleApplication {

    @Override
    public void beforeTest(Method method) {
        // Not implemented
    }

    @Override
    public void prepareTest(Object test) {
        // Not implemented
    }

    @Override
    public void afterTest(Method method) {
        // Not implemented
    }

    public static Context getContext() {
        return RuntimeEnvironment.application;
    }

    public static class MockTestLifecycle extends DefaultTestLifecycle {

        @Override
        public Application createApplication(Method method, AndroidManifest manifest, Config config) {
            return new MockApplication();
        }
    }
}

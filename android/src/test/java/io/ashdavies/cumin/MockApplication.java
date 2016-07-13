package io.ashdavies.cumin;

import android.app.Application;
import android.content.Context;

import org.mockito.Mockito;

import java.lang.reflect.Method;

public class MockApplication extends Application implements TestLifecycleApplication {

    @Override
    protected void initRealm() {
        // Do nothing
    }

    @Override
    protected ApplicationComponent createComponent() {
        return Mockito.mock(ApplicationComponent.class, Mockito.RETURNS_MOCKS);
    }

    @Override
    protected void initTracking() {
        // Do nothing
    }

    @Override
    public void beforeTest(Method method) {
        TrackingManager.setUp();
    }

    @Override
    public void prepareTest(Object test) {
        // Do nothing
    }

    @Override
    public void afterTest(Method method) {
        TrackingManager.tearDown();
    }

    public static class MockTestLifecycle extends DefaultTestLifecycle {

        @Override
        public android.app.Application createApplication(Method method, AndroidManifest manifest, Config config) {
            return new MockApplication();
        }
    }

    public static Context getContext() {
        return RuntimeEnvironment.application;
    }

    public static ApplicationComponent getMockComponent() {
        return from(getContext()).getComponent();
    }
}

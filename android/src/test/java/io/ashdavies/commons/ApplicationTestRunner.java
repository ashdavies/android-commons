package io.ashdavies.commons;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.TestLifecycle;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.FileFsFile;
import org.robolectric.util.ReflectionHelpers;

public class ApplicationTestRunner extends RobolectricGradleTestRunner {
  private static final String CONSTANT_APPLICATION_ID = "APPLICATION_ID";
  private static final String CONSTANT_BUILD_TYPE = "BUILD_TYPE";
  private static final String CONSTANT_FLAVOR = "FLAVOR";

  private static final String ANDROID_MANIFEST = "AndroidManifest.xml";
  private static final String BUILD_OUTPUT = "build/intermediates";

  private static final String TEST_RESOURCES = "src/test/res";

  public ApplicationTestRunner(Class<?> kls) throws InitializationError, InvocationTargetException {
    super(kls);
  }

  @Override
  protected Class<? extends TestLifecycle> getTestLifecycleClass() {
    return MockApplication.MockTestLifecycle.class;
  }

  @Override
  protected AndroidManifest getAppManifest(Config config) {
    if (config.constants() == Void.class) {
      throw new RuntimeException("No 'constants' field in @Config annotation!");
    }

    String applicationId = getStaticField(config.constants(), CONSTANT_APPLICATION_ID);
    String type = getStaticField(config.constants(), CONSTANT_BUILD_TYPE);
    String flavor = getStaticField(config.constants(), CONSTANT_FLAVOR);

    FileFsFile manifest = getManifest(flavor, type);
    FileFsFile res = getResources(flavor, type);
    FileFsFile assets = getAssets(flavor, type);

    return new AndroidManifest(manifest, res, assets, applicationId);
  }

  @Override
  protected Properties getConfigProperties() {
    FileFsFile file = FileFsFile.from(TEST_RESOURCES, "robolectric.properties");
    if (!file.exists()) {
      throw new RuntimeException("No 'constants' file found!");
    }

    Properties properties = new Properties();

    try {
      properties.load(file.getInputStream());
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }

    return properties;
  }

  private static String getStaticField(Class<?> constants, String field) {
    try {
      return ReflectionHelpers.getStaticField(constants, field);
    } catch (Throwable exception) {
      return null;
    }
  }

  private static FileFsFile getManifest(String flavor, String type) {
    FileFsFile manifest = FileFsFile.from(BUILD_OUTPUT, "manifests", "full", flavor, type, ANDROID_MANIFEST);
    if (manifest.exists()) {
      return manifest;
    }

    return FileFsFile.from(BUILD_OUTPUT, "bundles", flavor, type, ANDROID_MANIFEST);
  }

  private static FileFsFile getResources(String flavor, String type) {
    FileFsFile resources = FileFsFile.from(BUILD_OUTPUT, "res", flavor, type);
    if (resources.exists()) {
      return resources;
    }

    return FileFsFile.from(BUILD_OUTPUT, "res/merged", flavor, type);
  }

  private static FileFsFile getAssets(String flavor, String type) {
    return FileFsFile.from(BUILD_OUTPUT, "assets", flavor, type);
  }
}

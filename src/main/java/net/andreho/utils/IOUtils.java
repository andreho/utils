package net.andreho.utils;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by a.hofmann on 11.04.2015.
 */
public class IOUtils {

  /**
   * @param closeable
   */
  public static void close(Closeable closeable) {
    Objects.requireNonNull(closeable, "Given closeable is null.");
    try {
      closeable.close();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to close given closeable instance.", e);
    }
  }

  private IOUtils() {
  }
}

package net.andreho.utils;

import java.util.Map;

import static net.andreho.utils.MathUtils.isOdd;

/**
 * <br/>Created by a.hofmann on 24.01.2016.<br/>
 */
public class MapUtils {
  /**
   * @param map
   * @param pairs
   * @param <K>
   * @param <V>
   * @return
   */
  public static <K, V> Map<K, V> create(Map<K, V> map,
                                        Object... pairs) {
    if (isOdd(pairs.length)) {
      throw new IllegalArgumentException("Length of pairs must be even: " + pairs.length);
    }
    for (int i = 0; i < pairs.length; i += 2) {
      map.put((K) pairs[i], (V) pairs[i + 1]);
    }
    return map;
  }
}

package net.andreho.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <br/>Created by a.hofmann on 03.12.2016 at 16:52.
 */
public class ReflectionUtilsTest {
   @Test
   public void distance_estimate_must_calculate_proper_values() throws Exception {
      assertEquals(1_000_000_000, ReflectionUtils.estimateDistance(Object.class, Integer.class));
      assertEquals(1, ReflectionUtils.estimateDistance(Number.class, Integer.class));
//      assertEquals(1_001_001, ReflectionUtils.estimateDistance(Serializable.class, Integer.class));
      assertEquals(1_000, ReflectionUtils.estimateDistance(Comparable.class, Integer.class));
   }
}
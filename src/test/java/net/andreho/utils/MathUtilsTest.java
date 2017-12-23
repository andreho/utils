package net.andreho.utils;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by a.hofmann on 29.06.2016.
 */
public class MathUtilsTest {

  public static int TEMP = 0;

  @Test
  void divu10_randomInputs_validResults() {
    final ThreadLocalRandom r = ThreadLocalRandom.current();

    int sum = 0;
    for (int i = 0; i < 1000000000; i++) {
      final int n = 0x7FFFFFFF & r.nextInt();
      final int d = MathUtils.divu10(n);
      assertEquals(n / 10, d);
      sum += d;
    }
    TEMP += sum;
  }
}
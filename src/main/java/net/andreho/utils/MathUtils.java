package net.andreho.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <br/>Created by a.hofmann on 17.05.2014 at 16:17<br/>
 */
public final class MathUtils {
   private MathUtils() {
   }

   //##############################################################################

   /**
    * @param n
    * @return
    * @implSpec http://www.hackersdelight.org/divcMore.pdf
    */
   public static int divu3(final int n) {
      int q = (n >> 2) + (n >> 4);
      q = q + (q >> 4);
      q = q + (q >> 8);
      q = q + (q >> 16);
      int r = n - q * 3;
      return q + ((11 * r) >> 5);
   }

   /**
    * @param n
    * @return
    * @implSpec http://www.hackersdelight.org/divcMore.pdf
    */
   public static int divu5(final int n) {
      int q = (n >> 3) + (n >> 4);
      q = q + (q >> 4);
      q = q + (q >> 8);
      q = q + (q >> 16);
      int r = n - q * 5;
      return q + ((13 * r) >> 6);
   }

   /**
    * @param n
    * @return
    * @implSpec http://www.hackersdelight.org/divcMore.pdf
    */
   public static int divu6(final int n) {
      int q = (n >> 1) + (n >> 3);
      q = q + (q >> 4);
      q = q + (q >> 8);
      q = q + (q >> 16);
      q = q >> 2;
      int r = n - q * 6;
      return q + ((r + 2) >> 3);
   }

   /**
    * @param n
    * @return
    * @implSpec http://www.hackersdelight.org/divcMore.pdf
    */
   public static int divu7(final int n) {
      int q = (n >> 1) + (n >> 4);
      q = q + (q >> 6);
      q = q + (q >> 12) + (q >> 24);
      q = q >> 2;
      int r = n - q * 7;
      return q + ((r + 1) >> 3);
   }

   /**
    * @param n
    * @return
    * @implSpec http://www.hackersdelight.org/divcMore.pdf
    */
   public static int divu9(final int n) {
      int q = n - (n >> 3);
      q = q + (q >> 6);
      q = q + (q >> 12) + (q >> 24);
      q = q >> 3;
      int r = n - q * 9;
      return q + ((r + 7) >> 4);
   }

   /**
    * @param n
    * @return
    * @implSpec http://www.hackersdelight.org/divcMore.pdf
    */
   public static int divu10(final int n) {
      int q = (n >> 1) + (n >> 2);
      q = q + (q >> 4);
      q = q + (q >> 8);
      q = q + (q >> 16);
      q = q >> 3;
      int r = n - q * 10;
      return q + ((r + 6) >> 4);
   }

   /**
    * @param n
    * @return
    * @implSpec http://www.hackersdelight.org/divcMore.pdf
    */
   public static int divu11(final int n) {
      int q = (n >> 1) + (n >> 2) -
              (n >> 5) + (n >> 7);
      q = q + (q >> 10);
      q = q + (q >> 20);
      q = q >> 3;
      int r = n - q * 11;
      return q + ((r + 5) >> 4);
   }

   /**
    * @param n
    * @return
    * @implSpec http://www.hackersdelight.org/divcMore.pdf
    */
   public static int divu12(final int n) {
      int q = (n >> 1) + (n >> 3);
      q = q + (q >> 4);
      q = q + (q >> 8);
      q = q + (q >> 16);
      q = q >> 3;
      int r = n - q * 12;
      return q + ((r + 4) >> 4);
   }

   /**
    * @param n
    * @return
    * @implSpec http://www.hackersdelight.org/divcMore.pdf
    */
   public static int divu13(final int n) {
      int q = (n >> 1) + (n >> 4);
      q = q + (q >> 4) + (q >> 5);
      q = q + (q >> 12) + (q >> 24);
      q = q >> 3;
      int r = n - q * 13;
      return q + ((r + 3) >> 4);
   }

   //##############################################################################

   public static long sum(long a, long b) {
      return a + b;
   }

   public static long sum(long a, long b, long c) {
      return a + b + c;
   }

   public static long sum(long a, long b, long c, long d) {
      return a + b + c + d;
   }

   public static long sum(long a, long b, long c, long d, long e) {
      return a + b + c + d + e;
   }

   public static long sum(long a, long b, long c, long d, long e, long f) {
      return a + b + c + d + e + f;
   }

   public static long sum(long a, long b, long c, long d, long e, long f, long g) {
      return a + b + c + d + e + f + g;
   }

   public static long sum(long a, long b, long c, long d, long e, long f, long g, long h) {
      return a + b + c + d + e + f + g + h;
   }

   public static long sum(int a, int b) {
      return a + b;
   }

   public static int sum(int a, int b, int c) {
      return a + b + c;
   }

   public static int sum(int a, int b, int c, int d) {
      return a + b + c + d;
   }

   public static int sum(int a, int b, int c, int d, int e) {
      return a + b + c + d + e;
   }

   public static int sum(int a, int b, int c, int d, int e, int f) {
      return a + b + c + d + e + f;
   }

   public static int sum(int a, int b, int c, int d, int e, int f, int g) {
      return a + b + c + d + e + f + g;
   }

   public static int sum(int a, int b, int c, int d, int e, int f, int g, int h) {
      return a + b + c + d + e + f + g + h;
   }

   //##############################################################################

   public static final boolean isPrime(final int val) {
      switch (val) {
         case 2:
         case 3:
         case 5:
         case 7:
         case 11:
         case 13:
            return true;
      }

      if (MathUtils.isEven(val) ||
          ((val % 3) == 0) ||
          ((val % 5) == 0) ||
          ((val % 7) == 0)) {
         return false;
      }

      for (int i = 11, l = val >>> 1; i <= l; ) {
         //after 9, values having 1-3-7-9 as last digits may be a prime, example 11, 13, 17, 19 and so on
         if (((val % i) == 0)) {
            return false;
         }
         i += 2;
         if (((val % i) == 0)) {
            return false;
         }
         i += 4;
         if (((val % i) == 0)) {
            return false;
         }
         i += 2;
         if (((val % i) == 0)) {
            return false;
         }
         i += 2;
      }

      return true;
   }

   public static int nextPrime(int n) {
      for (n += (isEven(n)) ? 1 : 0; !isPrime(n); n += 2) {
      }

      return n;
   }

   public static int prevPrime(int n) {
      for (n -= (isEven(n)) ? 1 : 0; n >= 2 && !isPrime(n); n -= 2) {
      }

      return n;
   }

   //##############################################################################

   public static final int ggT(int a, int b) {
      while (b != 0) {
         int h = a % b;
         a = b;
         b = h;
      }
      return a;
   }

   public static final long ggT(long a, long b) {
      while (b != 0) {
         long h = a % b;
         a = b;
         b = h;
      }
      return a;
   }

   //##############################################################################

   public static final int modulo(final int i, final int mod) {
      return (i - ((i / mod) * mod));
   }

   public static final long modulo(final long i, final long mod) {
      return (i - ((i / mod) * mod));
   }

   //##############################################################################

   public static boolean isPowerOfTwo(int i) {
      return (i & (i - 1)) == 0;
   }

   public static boolean isPowerOfTwo(long i) {
      return (i & (i - 1L)) == 0;
   }

   //##############################################################################

   public static int bitMask(int value) {
      return (value == 0) ? 0 :
             (1 << (32 - Integer.numberOfLeadingZeros(value))) - 1;
   }

   public static int previousPowerOfTwo(int value) {
      value -= 1;

      if (value <= 0) {
         return 0;
      }

      return 1 << (32 - Integer.numberOfLeadingZeros((value) >>> 1));
   }

   public static int currentPowerOfTwo(final int value) {
      if (value < 0) {
         return Integer.MAX_VALUE;
      }

      return 1 << (32 - Integer.numberOfLeadingZeros(value));
   }

   public static int nextPowerOfTwo(final int value) {
      if (value < 0) {
         return Integer.MAX_VALUE;
      }

      return 1 << (32 - Integer.numberOfLeadingZeros(value << 1));
   }

   //##############################################################################

   public static int min(final int a, final int b, final int c) {
      return (a < b) ? (a < c) ? a : c : (b < c) ? b : c;
   }

   public static float min(final float a, final float b, final float c) {
      return (a < b) ? (a < c) ? a : c : (b < c) ? b : c;
   }

   public static long min(final long a, final long b, final long c) {
      return (a < b) ? (a < c) ? a : c : (b < c) ? b : c;
   }

   public static double min(final double a, final double b, final double c) {
      return (a < b) ? (a < c) ? a : c : (b < c) ? b : c;
   }

   //##############################################################################

   public static int abs(final int val) {
      final int mask = val >>> 31;
      return (val + mask) ^ mask;
   }

   public static long abs(final long val) {
      final long mask = val >>> 63;
      return (val + mask) ^ mask;
   }

   //##############################################################################

   public static int neg(final int val) {
      return (val > 0) ? 0 - val : val;
   }

   public static long neg(final long val) {
      return (val > 0) ? 0 - val : val;
   }

   public static float neg(final float val) {
      return (val > 0.0F) ? 0.0F - val : val;
   }

   public static double neg(final double val) {
      return (val > 0.0D) ? 0.0D - val : val;
   }

   //##############################################################################

   public static int diff(final int a, final int b) {
      return abs(a - b); //(a > b)? a - b : b - a;
   }

   public static long diff(final long a, final long b) {
      return abs(a - b);
   }

   public static float diff(final float a, final float b) {
      return (a > b) ? a - b : b - a;
   }

   public static double diff(final double a, final double b) {
      return (a > b) ? a - b : b - a;
   }

   //##############################################################################

   public static int even(final int a) {
      return a & (0xFFFFFFFE);
   }

   public static long even(final long a) {
      return a & (0xFFFFFFFFFFFFFFFEL);
   }

   public static boolean isEven(final long a) {
      return (a & 1) == 0;
   }

   public static boolean isEven(final int a) {
      return (a & 1) == 0;
   }

   //##############################################################################

   public static int odd(final int a) {
      return a | 1;
   }

   public static long odd(final long a) {
      return a | 1L;
   }

   public static boolean isOdd(final int a) {
      return (a & 1) != 0;
   }

   public static boolean isOdd(final long a) {
      return (a & 1) != 0;
   }

   //##############################################################################

   public static int unique(int notA) {
      return unique(notA, notA, notA);
   }

   public static int unique(int notA, int notB) {
      return unique(notA, notB, notB);
   }

   public static int unique(int notA, int notB, int notC) {
      final ThreadLocalRandom current = ThreadLocalRandom.current();
      int unique;
      do {
         unique = current.nextInt();
      }
      while (unique == notA || unique == notB || unique == notC);
      return unique;
   }

   //##############################################################################
}

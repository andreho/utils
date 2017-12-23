package net.andreho.utils;

/**
 * <br/>Created by a.hofmann on 17.05.2014 at 16:47<br/>
 */
public final class BitUtils {

  private static final int INT_BITS_MASK = 31;
  private static final int INT_IN_POWER_OF_TWO = 5;
  private static final int LONG_BITS_MASK = 63;
  private static final int LONG_IN_POWER_OF_TWO = 6;

  public static int[] intBits(int length) {
    return new int[(length | 32) >>> INT_IN_POWER_OF_TWO];
  }

  public static long[] longBits(int length) {
    return new long[((length | 64) >>> LONG_IN_POWER_OF_TWO)];
  }

  public static int setBit(int x,
                           int pos) {
    return (x | (1 << pos));
  }

  public static long setBit(long x,
                            int pos) {
    return (x | (1L << pos));
  }

  public static int flipBit(int x,
                            int pos) {
    return (x ^ (1 << pos));
  }

  public static long flipBit(long x,
                             int pos) {
    return (x ^ (1L << pos));
  }

  public static int clearBit(int x,
                             int pos) {
    return x & ~(1 << pos);
  }

  public static long clearBit(long x,
                              int pos) {
    return x & ~(1L << pos);
  }

  public static boolean getBit(int x,
                               int pos) {
    return (x & (1 << pos)) != 0;
  }

  public static boolean getBit(long x,
                               int pos) {
    return (x & (1L << pos)) != 0;
  }

  public static boolean hasMaskedBits(int x,
                                      int mask) {
    return (x & mask) != 0;
  }

  public static boolean hasMaskedBits(long x,
                                      long mask) {
    return (x & mask) != 0L;
  }

  public static int bitsAfter(int bits,
                              int pos) {
    return bits >>> pos;
  }

  public static long bitsAfter(long bits,
                               int pos) {
    return bits >>> pos;
  }

  public static boolean hasBitsAfter(int bits,
                                     int pos) {
    return bitsAfter(bits, pos) != 0;
  }

  public static boolean hasBitsAfter(long bits,
                                     int pos) {
    return bitsAfter(bits, pos) != 0L;
  }

  /**
   * @param index of bit
   * @return (1 & lt ; & lt ; ( 31 & index))
   */
  public static int bitAt(int index) {
    return (1 << (INT_BITS_MASK & index));
  }

  //#############################################################################################

  /**
   * @param a index of bit
   * @param b index of bit
   * @return (1 & lt ; & lt ; a) | (1 &lt;&lt; b)
   */
  public static int mergeBits(int a,
                              int b) {
    return bitAt(a) | bitAt(b);
  }

  /**
   * @param a index of bit
   * @param b index of bit
   * @param c index of bit
   * @return (1 & lt ; & lt ; a) | (1 &lt;&lt; b) | (1 &lt;&lt; c)
   */
  public static int mergeBits(int a,
                              int b,
                              int c) {
    return bitAt(a) | bitAt(b) | bitAt(c);
  }

  /**
   * @param a index of bit
   * @param b index of bit
   * @param c index of bit
   * @param d index of bit
   * @return (1 & lt ; & lt ; a) | (1 &lt;&lt; b) | (1 &lt;&lt; c) | (1 &lt;&lt; d)
   */
  public static int mergeBits(int a,
                              int b,
                              int c,
                              int d) {
    return bitAt(a) | bitAt(b) | bitAt(c) | bitAt(d);
  }

  /**
   * @param bits
   * @return
   */
  public static int mergeBits(int... bits) {
    int res = 0;
    for (int x : bits) {
      res |= bitAt(x);
    }
    return res;
  }

  //#############################################################################################

  public static void orBit(int[] array,
                           int index) {
    array[index >>> INT_IN_POWER_OF_TWO] |= (1 << (index & INT_BITS_MASK));
  }

  public static void orBit(long[] array,
                           int index) {
    array[index >>> LONG_IN_POWER_OF_TWO] |= (1L << (index & LONG_BITS_MASK));
  }

  //#############################################################################################

  public static void setBit(int[] array,
                            final int index,
                            int value) {
    int key = array[index >>> INT_IN_POWER_OF_TWO];
    value = value << (index & INT_BITS_MASK);
    key &= ~(1 << (index & INT_BITS_MASK)); //CLEAR KEY
    key |= value; //SET UP KEY
    array[index >>> INT_IN_POWER_OF_TWO] = key;
  }

  public static void setBit(long[] array,
                            final int index,
                            long value) {
    long key = array[index >>> LONG_IN_POWER_OF_TWO];
    value = value << (index & LONG_BITS_MASK);
    key &= ~(1L << (index & LONG_BITS_MASK)); //CLEAR KEY
    key |= value; //SET UP KEY
    array[index >>> LONG_IN_POWER_OF_TWO] = key;
  }

  //#############################################################################################

  public static boolean orBitAndGet(final int[] array,
                                    final int index) {
    final int key = array[index >>> INT_IN_POWER_OF_TWO];
    array[index >>> INT_IN_POWER_OF_TWO] = key | (1 << (index & INT_BITS_MASK));
    return (key & (1 << (index & INT_BITS_MASK))) != 0;
  }

  public static boolean orBitAndGet(final long[] array,
                                    final int index) {
    final long key = array[index >>> LONG_IN_POWER_OF_TWO];
    array[index >>> LONG_IN_POWER_OF_TWO] = key | (1 << (index & LONG_BITS_MASK));
    return (key & (1L << (index & LONG_BITS_MASK))) != 0;
  }

  //#############################################################################################

  public static void clearBit(final int[] array,
                              final int index) {
    array[index >> INT_IN_POWER_OF_TWO] &= ~(1 << (index & INT_BITS_MASK));
  }

  public static void clearBit(final long[] array,
                              final int index) {
    array[index >>> LONG_IN_POWER_OF_TWO] &= ~(1L << (index & LONG_BITS_MASK));
  }

  public static void flipBit(final int[] array,
                             final int index) {
    array[index >>> INT_IN_POWER_OF_TWO] ^= (1 << (index & INT_BITS_MASK));
  }

  public static void flipBit(final long[] array,
                             final int index) {
    array[index >>> LONG_IN_POWER_OF_TWO] ^= (1L << (index & LONG_BITS_MASK));
  }

  private BitUtils() {
  }
}

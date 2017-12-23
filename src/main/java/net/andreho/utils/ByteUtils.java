package net.andreho.utils;

/**
 * <br/>Created by a.hofmann on 17.05.2014 at 16:55<br/>
 */
public final class ByteUtils {

  public static char makeChar(byte b0,
                              byte b1) {
    return (char) (((b1 & 0xFF) << 8) | (b0 & 0xFF));
  }

  //##############################################################################

  public static short makeShort(byte b0,
                                byte b1) {
    return (short) (((b1 & 0xFF) << 8) | (b0 & 0xFF));
  }

  public static int makeMedium(byte b0,
                               byte b1,
                               byte b2) {
    return ((((b2 & 0xFF) << 24) >> 8) |
            ((b1 & 0xFF) << 8)) |
           (b0 & 0xFF);
  }

  public static int makeMedium(int ub0,
                               int ub1,
                               int ub2) {
    return ((ub2 << 24) >> 8) | (ub1 << 8) | ub0;
  }

  public static int makeInt(short s0,
                            short s1) {
    return ((s1 & 0xFFFF) << 16) |
           (s0 & 0xFFFF);
  }

  public static int makeInt(byte b0,
                            byte b1,
                            byte b2,
                            byte b3) {
    return (((b3 & 0xFF) << 24) |
            ((b2 & 0xFF) << 16) |
            ((b1 & 0xFF) << 8) |
            (b0 & 0xFF));
  }

  public static int makeInt(int ub0,
                            int ub1,
                            int ub2,
                            int ub3) {
    return ((ub3 << 24) | (ub2 << 16) | (ub1 << 8) | ub0);
  }

  public static long makeLong(int i0,
                              int i1) {
    return (((i1 & 0xFFFFFFFFL) << 32) |
            (i0 & 0xFFFFFFFFL));
  }

  public static long makeLong(short s0,
                              short s1,
                              short s2,
                              short s3) {
    return ((s3 & 0xFFFFL) << 48) |
           ((s2 & 0xFFFFL) << 32) |
           ((s1 & 0xFFFFL) << 16) |
           (s0 & 0xFFFFL);
  }

  public static long makeLong(byte b0,
                              byte b1,
                              byte b2,
                              byte b3,
                              byte b4,
                              byte b5,
                              byte b6,
                              byte b7) {
    return (((b7 & 0xFFL) << 56) |
            ((b6 & 0xFFL) << 48) |
            ((b5 & 0xFFL) << 40) |
            ((b4 & 0xFFL) << 32) |
            ((b3 & 0xFFL) << 24) |
            ((b2 & 0xFFL) << 16) |
            ((b1 & 0xFFL) << 8) |
            ((b0 & 0xFFL)));
  }

  public static long makeLong(int ub0,
                              int ub1,
                              int ub2,
                              int ub3,
                              int ub4,
                              int ub5,
                              int ub6,
                              int ub7) {
    return ((((long) ub7) << 56) |
            (((long) ub6) << 48) |
            (((long) ub5) << 40) |
            (((long) ub4) << 32) |
            (((long) ub3) << 24) |
            (((long) ub2) << 16) |
            (((long) ub1) << 8) |
            (((long) ub0)));
  }

  public static void putByte(byte[] array,
                             int index,
                             byte value) {
    array[index] = value;
  }

  //##############################################################################

  public static void putShort(byte[] array,
                              int index,
                              short value,
                              boolean bigEndian) {
    if (bigEndian) {
      putShortBE(array, index, value);
    } else {
      putShortLE(array, index, value);
    }
  }

  public static void putShortBE(byte[] array,
                                int index,
                                short value) {
    array[index] = (byte) (value >>> 8);
    array[index + 1] = (byte) (value & 0xFF);
  }

  public static void putShortLE(byte[] array,
                                int index,
                                short value) {
    array[index] = (byte) (value & 0xFF);
    array[index + 1] = (byte) (value >>> 8);
  }

  public static void putChar(byte[] array,
                             int index,
                             char value,
                             boolean bigEndian) {
    if (bigEndian) {
      putCharBE(array, index, value);
    } else {
      putCharLE(array, index, value);
    }
  }

  public static void putCharBE(byte[] array,
                               int index,
                               char value) {
    array[index] = (byte) (value >>> 8);
    array[index + 1] = (byte) (value & 0xFF);
  }

  public static void putCharLE(byte[] array,
                               int index,
                               char value) {
    array[index] = (byte) (value & 0xFF);
    array[index + 1] = (byte) (value >>> 8);
  }

  public static void putInt(byte[] array,
                            int index,
                            int value,
                            boolean bigEndian) {
    if (bigEndian) {
      putIntBE(array, index, value);
    } else {
      putIntLE(array, index, value);
    }
  }

  public static void putIntBE(byte[] array,
                              int index,
                              int value) {
    array[index] = (byte) (value >>> 24);
    array[index + 1] = (byte) ((value >>> 16) & 0xFF);
    array[index + 2] = (byte) ((value >>> 8) & 0xFF);
    array[index + 3] = (byte) (value & 0xFF);
  }

  public static void putIntLE(byte[] array,
                              int index,
                              int value) {
    array[index] = (byte) (value & 0xFF);
    array[index + 1] = (byte) ((value >>> 8) & 0xFF);
    array[index + 2] = (byte) ((value >>> 16) & 0xFF);
    array[index + 3] = (byte) (value >>> 24);
  }

  public static void putFloat(byte[] array,
                              int index,
                              float value,
                              boolean bigEndian) {
    if (bigEndian) {
      putFloatBE(array, index, value);
    } else {
      putFloatLE(array, index, value);
    }
  }

  public static void putFloatBE(byte[] array,
                                int index,
                                float value) {
    putIntBE(array, index, Float.floatToRawIntBits(value));
  }

  public static void putFloatLE(byte[] array,
                                int index,
                                float value) {
    putIntLE(array, index, Float.floatToRawIntBits(value));
  }

  public static void putLong(byte[] array,
                             int index,
                             long value,
                             boolean bigEndian) {
    if (bigEndian) {
      putLongBE(array, index, value);
    } else {
      putLongLE(array, index, value);
    }
  }

  public static void putLongBE(byte[] array,
                               int index,
                               long value) {

    array[index] = (byte) (value >>> 56);
    array[index + 1] = (byte) ((value >>> 48) & 0xFF);
    array[index + 2] = (byte) ((value >>> 40) & 0xFF);
    array[index + 3] = (byte) ((value >>> 32) & 0xFF);

    array[index + 4] = (byte) ((value >>> 24) & 0xFF);
    array[index + 5] = (byte) ((value >>> 16) & 0xFF);
    array[index + 6] = (byte) ((value >>> 8) & 0xFF);
    array[index + 7] = (byte) ((value) & 0xFF);
  }

  public static void putLongLE(byte[] array,
                               int index,
                               long value) {

    array[index] = (byte) ((value) & 0xFF);
    array[index + 1] = (byte) ((value >>> 8) & 0xFF);
    array[index + 2] = (byte) ((value >>> 16) & 0xFF);
    array[index + 3] = (byte) ((value >>> 24) & 0xFF);

    array[index + 4] = (byte) ((value >>> 32) & 0xFF);
    array[index + 5] = (byte) ((value >>> 40) & 0xFF);
    array[index + 6] = (byte) ((value >>> 48) & 0xFF);
    array[index + 7] = (byte) (value >>> 56);
  }

  public static void putDouble(byte[] array,
                               int index,
                               double value,
                               boolean bigEndian) {
    if (bigEndian) {
      putDoubleBE(array, index, value);
    } else {
      putDoubleLE(array, index, value);
    }
  }

  public static void putDoubleBE(byte[] array,
                                 int index,
                                 double value) {
    putLongBE(array, index, Double.doubleToRawLongBits(value));
  }

  public static void putDoubleLE(byte[] array,
                                 int index,
                                 double value) {
    putLongLE(array, index, Double.doubleToRawLongBits(value));
  }

  public static byte getByte(byte[] array,
                             int index) {
    return array[index];
  }

  //##############################################################################

  public static short getShort(byte[] array,
                               int index,
                               boolean bigEndian) {
    return bigEndian ? getShortBE(array, index) : getShortLE(array, index);
  }

  public static short getShortBE(byte[] array,
                                 int index) {
    return makeShort(array[index + 1], array[index]);
  }

  public static short getShortLE(byte[] array,
                                 int index) {
    return makeShort(array[index], array[index + 1]);
  }

  public static char getChar(byte[] array,
                             int index,
                             boolean bigEndian) {
    return bigEndian ? getCharBE(array, index) : getCharLE(array, index);
  }

  public static char getCharBE(byte[] array,
                               int index) {
    return makeChar(array[index + 1], array[index]);
  }

  public static char getCharLE(byte[] array,
                               int index) {
    return makeChar(array[index], array[index + 1]);
  }

  public static int getInt(byte[] array,
                           int index,
                           boolean bigEndian) {
    return bigEndian ? getIntBE(array, index) : getIntLE(array, index);
  }

  public static int getIntBE(byte[] array,
                             int index) {
    return makeInt(array[index + 3], array[index + 2], array[index + 1], array[index]);
  }

  public static int getIntLE(byte[] array,
                             int index) {
    return makeInt(array[index], array[index + 1], array[index + 2], array[index + 3]);
  }

  public static float getFloat(byte[] array,
                               int index,
                               boolean bigEndian) {
    return bigEndian ? getFloatBE(array, index) : getFloatLE(array, index);
  }

  public static float getFloatBE(byte[] array,
                                 int index) {
    return Float.intBitsToFloat(
      makeInt(array[index + 3], array[index + 2], array[index + 1], array[index]));
  }

  public static float getFloatLE(byte[] array,
                                 int index) {
    return Float.intBitsToFloat(
      makeInt(array[index], array[index + 1], array[index + 2], array[index + 3]));
  }

  public static long getLong(byte[] array,
                             int index,
                             boolean bigEndian) {
    return bigEndian ? getLongBE(array, index) : getLongLE(array, index);
  }

  public static long getLongBE(byte[] array,
                               int index) {
    return makeLong(array[index + 7], array[index + 6], array[index + 5], array[index + 4],
                              array[index + 3], array[index + 2], array[index + 1], array[index]);
  }

  public static long getLongLE(byte[] array,
                               int index) {
    return makeLong(array[index], array[index + 1], array[index + 2], array[index + 3], array[index + 4],
                              array[index + 5], array[index + 6], array[index + 7]);
  }

  public static double getDouble(byte[] array,
                                 int index,
                                 boolean bigEndian) {
    return bigEndian ? getDoubleBE(array, index) : getDoubleLE(array, index);
  }

  public static double getDoubleBE(byte[] array,
                                   int index) {
    return Double.longBitsToDouble(
      makeLong(array[index + 7], array[index + 6], array[index + 5], array[index + 4], array[index + 3],
                         array[index + 2], array[index + 1], array[index]));
  }

  public static double getDoubleLE(byte[] array,
                                   int index) {
    return Double.longBitsToDouble(
      makeLong(array[index], array[index + 1], array[index + 2], array[index + 3], array[index + 4],
                         array[index + 5], array[index + 6], array[index + 7]));
  }

  public static byte char1(char x) {
    return (byte) (x >> 8);
  }

  //##############################################################################

  public static byte char0(char x) {
    return (byte) (x >> 0);
  }

  public static byte short1(short x) {
    return (byte) (x >> 8);
  }

  //##############################################################################

  public static byte short0(short x) {
    return (byte) (x >> 0);
  }

  public static byte int3(int x) {
    return (byte) (x >> 24);
  }

  //##############################################################################

  public static byte int2(int x) {
    return (byte) (x >> 16);
  }

  public static byte int1(int x) {
    return (byte) (x >> 8);
  }

  public static byte int0(int x) {
    return (byte) (x >> 0);
  }

  public static byte long7(long x) {
    return (byte) (x >> 56);
  }

  //##############################################################################

  public static byte long6(long x) {
    return (byte) (x >> 48);
  }

  public static byte long5(long x) {
    return (byte) (x >> 40);
  }

  public static byte long4(long x) {
    return (byte) (x >> 32);
  }

  public static byte long3(long x) {
    return (byte) (x >> 24);
  }

  public static byte long2(long x) {
    return (byte) (x >> 16);
  }

  public static byte long1(long x) {
    return (byte) (x >> 8);
  }

  public static byte long0(long x) {
    return (byte) (x >> 0);
  }

  public static short swap(short x) {
    return Short.reverseBytes(x);
    //return (short) ((x << 8) | ((x >> 8) & 0xFF));
  }

  //##############################################################################

  public static char swap(char x) {
    return Character.reverseBytes(x);
    //return (char) ((x << 8) | ((x >> 8) & 0xFF));
  }

  public static int swap(int x) {
    return Integer.reverseBytes(x);
    //return (x >>> 24) | ((x & 0xFF0000) >> 8) | ((x & 0xFF00) << 8) | (x << 24);
  }

  public static long swap(long x) {
    return Long.reverseBytes(x);
    //return ((((long) swap((int) x)) << 32) | (swap((int) (x >>> 32)) & 0xFFFFFFFFL));
  }

  private ByteUtils() {
  }

  //##############################################################################
}

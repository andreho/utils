/*******************************************************************************
 * Copyright (C) Andre Hofmann [gumengangler@gmail.com]
 ******************************************************************************/
package net.andreho.utils;

public final class ParseUtils {

  private static final char[] INT_MAXIMAL = "2147483647".toCharArray();
  private static final char[] INT_MINIMAL = "2147483648".toCharArray();
  private static final double[] EXPONENTS = new double[Double.MAX_EXPONENT - Double.MIN_EXPONENT + 2];

  //##############################################################################

  static {
    int idx = 0;

    for (int i = Double.MIN_EXPONENT; i < 0; i++) {
      EXPONENTS[idx++] = Math.pow(10, i);
    }

    for (int i = 0; i <= Double.MAX_EXPONENT; i++) {
      EXPONENTS[idx++] = Math.pow(10, i);
    }
  }

  private static int toHexValue(final char ch) {
    if (isDigit(ch)) {
      return ch - '0';
    }
    if (isInRange(ch, 'A', 'F')) {
      return 10 + (ch - 'A');
    }
    if (isInRange(ch, 'a', 'f')) {
      return 10 + (ch - 'a');
    }
    throw new IllegalArgumentException("Not a hex value: '" + ch + "', code: " + ((int) ch));
  }

  //##############################################################################

  /**
   * Builds an unicode value from given hex character values
   *
   * @param a first hex value
   * @param b second hex value
   * @param c third hex value
   * @param d fourth hex value
   * @return an unicode code point
   */
  public static int toUnicode(char a,
                              char b,
                              char c,
                              char d) {
    return (toHexValue(a) << 12) |
           (toHexValue(b) << 8) |
           (toHexValue(c) << 4) |
           (toHexValue(d));
  }

  /**
   * @param ch
   * @return
   */
  public static boolean isDigit(final int ch) {
    return '0' <= ch && ch <= '9';
  }

  //##############################################################################

  /**
   * @param ch
   * @return
   */
  public static boolean isLeadingDigit(final int ch) {
    return '1' <= ch && ch <= '9';
  }

  /**
   * @param ch
   * @param from
   * @param to
   * @return
   */
  public static boolean isInRange(final int ch,
                                  int from,
                                  int to) {
    return from <= ch && ch <= to;
  }

  /**
   * @param maximal
   * @param digits
   * @param offset
   * @param length
   * @return
   */
  public static boolean isNumericOverflow(final char[] maximal,
                                          final char[] digits,
                                          int offset,
                                          int length) {
    final int len = length - offset;
    if (len == maximal.length) {
      for (int i = 0; i < len; i++) {
        final char max = maximal[i];
        final char current = digits[offset + i];

        if (current > max) {
          return true;
        } else if (current < max) {
          return false;
        }
      }
      return true;
    }
    return len > maximal.length;
  }

  /**
   * @param ch
   * @param a
   * @param b
   * @return
   */
  public static boolean isOneOf(final int ch,
                                char a,
                                char b) {
    return ch == a ||
           ch == b;
  }

  /**
   * @param ch
   * @param a
   * @param b
   * @param c
   * @return
   */
  public static boolean isOneOf(final int ch,
                                char a,
                                char b,
                                char c) {
    return ch == a ||
           ch == b ||
           ch == c;
  }

  /**
   * @param ch
   * @param a
   * @param b
   * @param c
   * @param d
   * @return
   */
  public static boolean isOneOf(final int ch,
                                char a,
                                char b,
                                char c,
                                char d) {
    return ch == a ||
           ch == b ||
           ch == c ||
           ch == d;
  }

  /**
   * @param ch
   * @param a
   * @param b
   * @param c
   * @param d
   * @param e
   * @return
   */
  public static boolean isOneOf(final int ch,
                                char a,
                                char b,
                                char c,
                                char d,
                                char e) {
    return ch == a ||
           ch == b ||
           ch == c ||
           ch == d ||
           ch == e;
  }

  /**
   * @param i
   * @return
   */
  public static int binaryLength(int i) {
    if (i == 0) {
      return 1;
    }
    if (i < 0) {
      return 32;
    }
    return 32 - Integer.numberOfLeadingZeros(i);
  }

  //################################################################################################################

  /**
   * @param l
   * @return
   */
  public static int binaryLength(long l) {
    if (l == 0L) {
      return 1;
    }
    if (l < 0L) {
      return 64;
    }

    return 64 - Long.numberOfLeadingZeros(l);
  }

  /**
   * @param i
   * @return
   */
  public static int hexLength(int i) {
    int binaryLength = binaryLength(i);
    return (binaryLength >>> 2) + ((binaryLength & 3) == 0 ? 0 : 1);
  }

  //################################################################################################################

  /**
   * @param l
   * @return
   */
  public static int hexLength(long l) {
    int binaryLength = binaryLength(l);
    return (binaryLength >>> 2) + ((binaryLength & 3) == 0 ? 0 : 1);
  }

  /**
   * @param i
   * @return
   */
  public static int decimalLength(int i) {
    return (i < 0) ? (i == Integer.MIN_VALUE) ? 1 + 10
                                              : 1 + decimalLengthInternal(0 - i)
                   : decimalLengthInternal(i);
  }

  //################################################################################################################

  /**
   * @param l
   * @return
   */
  public static int decimalLength(long l) {
    return (l < 0L) ? (l == Long.MIN_VALUE) ? 1 + 19
                                            : 1 + decimalLengthInternal(0 - l)
                    : decimalLengthInternal(l);
  }

  private static int decimalLengthInternal(int i) {
    if (i < 10_000_000) {
      if (i < 10_000) {
        if (i < 100) {
          return (i < 10) ? 1 : 2;
        }
        return (i < 1_000) ? 3 : 4;
      } else if (i < 1_000_000) {
        return (i < 100_000) ? 5 : 6;
      }
      return 7;
    } else if (i < 1_000_000_000) {
      return i < 100_000_000 ? 8 : 9;
    }
    return 10;
  }

  //##############################################################################

//	private static final char[] DEFAULT_INT = 		 "999999999".toCharArray();
//	private static final char[] POSITIVE_MAX_INT = 	"2147483647".toCharArray();
//	private static final char[] NEGATIVE_MAX_INT = 	"2147483648".toCharArray();

  private static int decimalLengthInternal(long l) {
        /*
         01* 1
		 02* 10,
		 03* 100, -
		 04* 1000,
		 05* 10000,
		 06* 100000, -
		 07* 1000000,
		 08* 10000000,
		 09* 100000000, -
		 10* 1000000000,
		 11* 10000000000, -
		 12* 100000000000,
		 13* 1000000000000,
		 14* 10000000000000,
		 15* 100000000000000,
		 16* 1000000000000000,
		 17* 10000000000000000,
		 18* 100000000000000000,
		 19* 1_000_000_000_000_000_000,
		 20* 9_223_372_036_854_775_807
		 */

    if (l < 10_000_000_000L) { //10
      if (l < 100_000L) { //5
        if (l < 100L) { //2
          return (l < 10L) ? 1 : 2;
        } else if (l < 10_000L) { //4
          return (l < 1000L) ? 3 : 4;
        }
        return 5;
      } else {
        if (l < 10_000_000L) { //7
          return (l < 1_000_000) ? 6 : 7;
        } else if (l < 1_000_000_000L) { //9
          return (l < 100_000_000L) ? 8 : 9;
        }
        return 10;
      }
    } else {
      if (l < 1_000_000_000_000_000L) { //15
        if (l < 1_000_000_000_000L) { //12
          return (l < 100_000_000_000L) ? 11 : 12;
        } else if (l < 100_000_000_000_000L) { //14
          return (l < 10_000_000_000_000L) ? 13 : 14;
        }
        return 15;
      } else {
        if (l < 100_000_000_000_000_000L) { //17
          return (l < 10_000_000_000_000_000L) ? 16 : 17;
        }
        return (l < 1_000_000_000_000_000_000L) ? 18 : 19;
      }
    }
  }

  //##############################################################################

  /**
   * Simple validation (not precise enough)
   *
   * @param digits
   * @param offset
   * @param length
   * @return
   */
  public static boolean isValidInteger(final char[] digits,
                                       int offset,
                                       final int length) {
    char[] maximal = INT_MAXIMAL;
    if ('-' == digits[offset]) {
      maximal = INT_MINIMAL;
      offset += 1;
    }

    boolean valid = isLeadingDigit(digits[offset]);

    switch (length - offset) {
      case 10:
        valid = valid && !isNumericOverflow(maximal, digits, offset, length) && isDigit(digits[offset + 9]);
      case 9:
        valid = valid && isDigit(digits[offset + 8]);
      case 8:
        valid = valid && isDigit(digits[offset + 7]);
      case 7:
        valid = valid && isDigit(digits[offset + 6]);
      case 6:
        valid = valid && isDigit(digits[offset + 5]);
      case 5:
        valid = valid && isDigit(digits[offset + 4]);
      case 4:
        valid = valid && isDigit(digits[offset + 3]);
      case 3:
        valid = valid && isDigit(digits[offset + 2]);
      case 2:
        valid = valid && isDigit(digits[offset + 1]);
      case 1:
        return valid && isDigit(digits[offset + 0]);
      default:
        return false;
    }
  }

  /**
   * Parses the given array as a text representation of a negative or positive integer value.<br/>
   * Minimal is {@link Integer#MIN_VALUE} and maximal value is {@link Integer #MAX_VALUE}.<br/>
   * This method doesn't make any syntactical/bound checks whether the given characters are all digits or not and
   * it only works with <b>radix 10</b>.
   *
   * @param digits an array with textual representation of corresponding integer value.
   * @return a corresponding integer value, it is negative if a minus character is present as first character or
   * <u>an overflow is appeared</u>.<br/>
   */
  public static int parseInteger(char[] digits) {
    return parseInteger(digits, 0, digits.length);
  }

  /**
   * Parses the given array as a text representation of a negative or positive integer value.<br/>
   * Minimal is {@link Integer #MIN_VALUE} and maximal value is {@link Integer #MAX_VALUE}.<br/>
   * This method doesn't make any syntactical/bound checks whether the given characters are all digits or not and
   * it only works with <b>radix 10</b>.
   *
   * @param digits an array with textual representation of corresponding integer value.
   * @param offset defines where to start
   * @param length defines index where is the end of integer (exclusive).
   * @return a corresponding integer value, it is negative if a minus character is present at offset or <u>an
   * overflow is appeared</u>.
   */
  public static int parseInteger(char[] digits,
                                 int offset,
                                 int length) {
    return (digits[offset] == '-') ?
           ~(parsePositiveInteger(digits, offset + 1, length) - 1)
                                   : parsePositiveInteger(digits, offset, length);
  }

  /**
   * Parses the given string as a text representation of a negative or positive integer value.<br/>
   * Minimal is {@link Integer #MIN_VALUE} and maximal value is {@link Integer #MAX_VALUE}.<br/>
   * This method doesn't make any syntactical checks whether the given characters are all digits or not and it only
   * works with <b>radix 10</b>.
   *
   * @param digits a string with textual representation of corresponding integer value.
   * @return a corresponding integer value, it is negative if a minus character is present as first character or
   * <u>an overflow is appeared</u>.
   */
  public static int parseInteger(String digits) {
    return parseInteger(digits, 0, digits.length());
  }

  //#############################################################################################################################################################

  /**
   * Parses the given string as a text representation of a negative or positive integer value.<br/>
   * Minimal is {@link Integer #MIN_VALUE} and maximal value is {@link Integer #MAX_VALUE}.<br/>
   * This method doesn't make any syntactical checks whether the given characters are all digits or not and it only
   * works with <b>radix 10</b>.
   *
   * @param digits a string with textual representation of corresponding integer value.
   * @param offset defines where to start
   * @param length defines index where is the end of integer (exclusive).
   * @return a corresponding integer value, it is negative if a minus character is present at offset or <u>an
   * overflow is appeared</u>.
   */
  public static int parseInteger(String digits,
                                 int offset,
                                 int length) {
    final char[] content = StringUtils.obtainContent(digits);
    return (content[offset] == '-') ?
           ~(parsePositiveInteger(content, offset + 1, length) - 1)
                                    : parsePositiveInteger(content, offset, length);
  }

  //#############################################################################################################################################################

  private static int parsePositiveInteger(char[] digits,
                                          int from,
                                          int to) {
    int value = 0;

    if (to > from) {
      value = digits[--to] - '0'; //0

      if (to > from) {
        value += (10 * (digits[--to] - '0')); //1

        if (to > from) {
          value += (100 * (digits[--to] - '0')); //2

          if (to > from) {
            value += (1000 * (digits[--to] - '0')); //3

            if (to > from) {
              value += (10000 * (digits[--to] - '0')); //4

              if (to > from) {
                value += (100000 * (digits[--to] - '0')); //5

                if (to > from) {
                  value += (1000000 * (digits[--to] - '0')); //6

                  if (to > from) {
                    value += (10000000 * (digits[--to] - '0')); //7

                    if (to > from) {
                      value += (100000000 * (digits[--to] - '0')); //8

                      if (to > from) {
                        //+2147483648 => Overflow
                        value += (1000000000 * (digits[--to] - '0')); //9
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return value;
  }

  /**
   * @param digits
   * @return
   */
  public static long parseLong(final char[] digits) {
    return parseLong(digits, 0, digits.length);
  }

  /**
   * @param digits
   * @param offset
   * @param length
   * @return
   */
  public static long parseLong(final char[] digits,
                               final int offset,
                               final int length) {
    return (digits[offset] == '-') ?
           ~(parsePositiveLong(digits, offset + 1, length) - 1L)
                                   : parsePositiveLong(digits, offset, length);
  }

  /**
   * @param digits
   * @return
   */
  public static long parseLong(final String digits) {
    return parseLong(digits, 0, digits.length());
  }

  //#############################################################################################################################################################

  /**
   * @param digits
   * @param offset
   * @param length
   * @return
   */
  public static long parseLong(final String digits,
                               final int offset,
                               final int length) {
    final char[] content = StringUtils.obtainContent(digits);
    return (content[offset] == '-') ?
           ~(parsePositiveLong(content, offset + 1, length) - 1L)
                                    : parsePositiveLong(content, offset, length);
  }

  //#############################################################################################################################################################

  private static long parsePositiveLong(final char[] digits,
                                        final int from,
                                        int to) {
    long value = 0;

    if (to > from) {
      value = digits[--to] - '0'; //0

      if (to > from) {
        value += (10 * (digits[--to] - '0')); //1

        if (to > from) {
          value += (100 * (digits[--to] - '0')); //2

          if (to > from) {
            value += (1000 * (digits[--to] - '0')); //3

            if (to > from) {
              value += (10000 * (digits[--to] - '0')); //4

              if (to > from) {
                value += (100000 * (digits[--to] - '0')); //5

                if (to > from) {
                  value += (1000000 * (digits[--to] - '0')); //6

                  if (to > from) {
                    value += (10000000 * (digits[--to] - '0')); //7

                    if (to > from) {
                      value += (100000000 * (digits[--to] - '0')); //8

                      if (to > from) {
                        value += (1000000000L * (digits[--to] - '0')); //9

                        if (to > from) {
                          value += (10000000000L * (digits[--to] - '0')); //10

                          if (to > from) {
                            value += (100000000000L * (digits[--to] - '0')); //11

                            if (to > from) {
                              value += (1000000000000L * (digits[--to] - '0')); //12

                              if (to > from) {
                                value += (10000000000000L * (digits[--to] - '0')); //13

                                if (to > from) {
                                  value += (100000000000000L * (digits[--to] - '0')); //14

                                  if (to > from) {
                                    value += (1000000000000000L * (digits[--to] - '0')); //15

                                    if (to > from) {
                                      value += (10000000000000000L * (digits[--to] - '0')); //16

                                      if (to > from) {
                                        value += (100000000000000000L * (digits[--to] - '0')); //17

                                        if (to > from) {
                                          value +=
                                            (1000000000000000000L * (digits[--to] - '0')); //18
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return value;
  }

  private static double exponent(int exponent) {
    return EXPONENTS[Double.MAX_EXPONENT + exponent];
  }

  public static double eatDouble(String s) {
    return eatDouble(s.toCharArray());
  }

  public static double eatDouble(char[] linearray) {
    int pos = 0;
    int len = linearray.length;
    double value = 0;
    boolean negative = false;
    boolean afterpoint = false;
    double divider = 1;
    char thischar = 0;
    int oldpos = pos;
    while (pos < len && (thischar = linearray[pos]) != ' ' && thischar != 'E' && thischar != 'e' &&
           thischar != '\t') {
      if (thischar == '-') {
        negative = true;
      } else if (thischar == '.') {
        afterpoint = true;
      } else {
        int thisdigit = thischar - '0';
        value = value * 10 + thisdigit;
        if (afterpoint) {
          divider *= 10;
        }
      }
      pos++;
    }
    if (thischar == 'E' || thischar == 'e') {
      pos++;
      boolean exponentnegative = false;
      int exponent = 0;
      while (pos < len && (thischar = linearray[pos]) != ' ' && thischar != '\t') {
        if (thischar == '-') {
          exponentnegative = true;
        } else if (thischar != '+') {
          exponent = exponent * 10 + (thischar - '0');
        }
        pos++;
      }
      if (exponentnegative) {
        exponent = -exponent;
      }
      value *= Math.pow(10, exponent);
    }
    if (negative) {
      value = -value;
    }
    value /= divider;
    if (pos == oldpos) {
      throw new RuntimeException();
    }
    return value;
  }

  private ParseUtils() {
  }
}

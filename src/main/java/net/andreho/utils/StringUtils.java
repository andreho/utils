package net.andreho.utils;

import java.lang.reflect.Field;

/**
 * <br/>Created by a.hofmann on 19.07.2014 at 03:00<br/>
 */
public class StringUtils {
   private static final Field VALUE_FIELD;

   static {
      Field contentField = null;
      for (Field field : String.class.getDeclaredFields()) {
         if ("value".equals(field.getName())) {
            contentField = field;
         }
      }
      if (contentField == null) {
         throw new IllegalStateException("Missing field 'value' of java.lang.String");
      }
      contentField.setAccessible(true);
      VALUE_FIELD = contentField;
   }

   //####################################################################################################

   /**
    * Retrieves inner character array from given string
    *
    * @param str wrapping string instance
    * @return wrapped char-array
    */
   public static char[] obtainContent(String str) {
      try {
         return (char[]) VALUE_FIELD.get(str);
      } catch (IllegalAccessException e) {
         throw new IllegalStateException("Unable to get strings' content.");
      }
   }

   /**
    * @param str
    * @param nullStr
    * @return
    */
   public static String notNull(String str, String nullStr) {
      return str != null ? str : nullStr;
   }

   //################################################################################################################

   /**
    * Multiplies given string instance * times
    *
    * @param str
    * @param times
    * @return
    */
   public static String multiply(String str, int times) {
      StringBuilder builder = new StringBuilder(str.length() * 2);

      while (times-- > 0) {
         builder.append(str);
      }

      return builder.toString();
   }

   /**
    * Prepends given character to the given string until its length is less then awaited length
    *
    * @param str    to prepend to
    * @param pad    character
    * @param length is the minimal expected length
    * @return
    */
   public static String padLeft(String str, char pad, int length) {
      if (str.length() < length) {
         final StringBuilder builder = new StringBuilder(length);
         length -= str.length();
         while (length-- > 0) {
            builder.append(pad);
         }
         return builder.append(str).toString();
      }
      return str;
   }

   /**
    * Appends given character to the given string until its length is less then awaited length
    *
    * @param str    to append to
    * @param pad    character
    * @param length is the minimal expected length
    * @return
    */
   public static String padRight(String str, char pad, int length) {
      if (str.length() < length) {
         final StringBuilder builder = new StringBuilder(length);
         builder.append(str);
         length -= str.length();
         while (length-- > 0) {
            builder.append(pad);
         }
         return builder.toString();
      }
      return str;
   }
}

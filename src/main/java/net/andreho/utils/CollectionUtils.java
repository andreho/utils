package net.andreho.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * <br/>Created by a.hofmann on 01.06.2015.<br/>
 */
public class CollectionUtils {
   /**
    * Merges given two iterators to one
    *
    * @param first
    * @param second
    * @param <T>
    * @return
    */
   public static <T> Iterator<T> mergeIterators(final Iterator<? extends T> first, final Iterator<? extends T> second) {
      return new Iterator<T>() {
         Iterator<? extends T> current = first;

         @Override
         public boolean hasNext() {
            boolean hasNext = current.hasNext();

            if (!hasNext && current != second) {
               current = second;
               hasNext = current.hasNext();
            }

            return hasNext;
         }

         @Override
         public T next() {
            return current.next();
         }

         @Override
         public void remove() {
            current.remove();
         }
      };
   }

   /**
    * Merges given iterators to one
    *
    * @param iterators
    * @param <T>
    * @return
    */
   public static <T> Iterator<T> mergeIterators(final Iterator<? extends T>... iterators) {
      if (iterators.length == 0) {
         return Collections.emptyIterator();
      }

      return new Iterator<T>() {
         int index = 0;
         Iterator<? extends T> current = iterators[0];

         @Override
         public boolean hasNext() {
            boolean hasNext = current.hasNext();

            if (!hasNext &&
                (index + 1) < iterators.length) {
               current = iterators[index++];
               hasNext = current.hasNext();
            }

            return hasNext;
         }

         @Override
         public T next() {
            return current.next();
         }

         @Override
         public void remove() {
            current.remove();
         }
      };
   }

   /**
    * Adds each element of given array into the given collection
    *
    * @param collection that receives array elements
    * @param elements   to add into collection
    * @param <C>        collection type
    * @param <E>        element type
    * @return
    */
   public static final <C extends Collection<E>, E> C toCollection(C collection, E... elements) {
      for (E e : elements) {
         collection.add(e);
      }

      return collection;
   }

   /**
    * @param <T>
    * @param <K>
    * @param key
    * @param list
    * @param extractor
    * @return
    */
   public static <T, K extends Comparable<K>> T binarySearch(final K key,
                                                             final List<? extends T> list,
                                                             final Function<T, K> extractor) {
      return binarySearch(key, list, extractor, Comparator.naturalOrder());
   }

   /**
    * @param <T>
    * @param <K>
    * @param key
    * @param list
    * @param extractor
    * @param comparator
    * @return
    */
   public static <T, K> T binarySearch(final K key,
                                       final List<? extends T> list,
                                       final Function<T, K> extractor,
                                       final Comparator<K> comparator) {
      return binarySearch(key, list, extractor, comparator, 0, list.size());
   }

   /**
    * @param <T>
    * @param <K>
    * @param key
    * @param list
    * @param extractor
    * @param comparator
    * @param fromIndex
    * @param toIndex
    * @return
    */
   public static <T, K> T binarySearch(
      final K key,
      final List<? extends T> list,
      final Function<T, K> extractor,
      final Comparator<K> comparator,
      final int fromIndex,
      final int toIndex) {

      int low = fromIndex;
      int high = toIndex - 1;

      while (low <= high) {
         final int mid = (low + high) >>> 1;
         final T target = list.get(mid);
         final K midVal = extractor.apply(target);

         int cmp = comparator.compare(midVal, key);
         if (cmp < 0) {
            low = mid + 1;
         } else if (cmp > 0) {
            high = mid - 1;
         } else {
            return target; // key found
         }
      }
      return null;
   }

   /**
    * @param <T>
    * @param <K>
    * @param key
    * @param list
    * @param extractor
    * @return
    */
   public static <T, K extends Comparable<K>> int binaryApproximation(final K key,
                                                             final List<? extends T> list,
                                                             final Function<T, K> extractor) {
      return binaryApproximation(key, list, extractor, Comparator.naturalOrder());
   }

   /**
    * @param <T>
    * @param <K>
    * @param key
    * @param list
    * @param extractor
    * @param comparator
    * @return
    */
   public static <T, K> int binaryApproximation(final K key,
                                       final List<? extends T> list,
                                       final Function<T, K> extractor,
                                       final Comparator<K> comparator) {
      return binaryApproximation(key, list, extractor, comparator, 0, list.size());
   }

   /**
    * @param <T>
    * @param <K>
    * @param key
    * @param list
    * @param extractor
    * @param comparator
    * @param fromIndex
    * @param toIndex
    * @return
    */
   public static <T, K> int binaryApproximation(
      final K key,
      final List<? extends T> list,
      final Function<T, K> extractor,
      final Comparator<K> comparator,
      final int fromIndex,
      final int toIndex) {

      int low = fromIndex;
      int high = toIndex - 1;

      while (low <= high) {
         final int mid = (low + high) >>> 1;
         final T target = list.get(mid);
         final K midVal = extractor.apply(target);

         int cmp = comparator.compare(midVal, key);
         if (cmp < 0) {
            low = mid + 1;
         } else if (cmp > 0) {
            high = mid - 1;
         } else {
            return mid; // key found
         }
      }
      return -(low + 1);
   }
}

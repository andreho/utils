package net.andreho.utils.internal;

/**
 * Created by a.hofmann on 17.06.2016.
 */
public class NamedThreadLocal<T> extends ThreadLocal<T> {
   private final String name;

   public NamedThreadLocal(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return name;
   }
}
package net.andreho.utils.internal;

/**
 * Created by a.hofmann on 17.06.2016.
 */
public class NamedInheritableThreadLocal<T> extends InheritableThreadLocal<T> {
   private final String name;

   public NamedInheritableThreadLocal(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return name;
   }
}
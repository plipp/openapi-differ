package io.github.plipp.openapi.diff.compare;

public interface Comparable<T> {

  boolean compare(T left, T right);
}

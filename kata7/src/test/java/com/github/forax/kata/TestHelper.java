package com.github.forax.kata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

class TestHelper {
  private TestHelper() {
    throw new AssertionError();
  }

  @SafeVarargs
  public static <T> List<T> listOf(T... elements) {
    ArrayList<T> list = new ArrayList<>();
    for(T element: elements) {
      list.add(requireNonNull(element));
    }
    return Collections.unmodifiableList(list);
  }

  public static <K,V> Map<K,V> mapOf() {
    return Collections.emptyMap();
  }
  public static <K,V> Map<K, V> mapOf(K key1, V value1) {
    requireNonNull(key1);
    requireNonNull(value1);
    return Collections.singletonMap(key1, value1);
  }
  public static <K,V> Map<K, V> mapOf(K key1, V value1, K key2, V value2) {
    requireNonNull(key1);
    requireNonNull(value1);
    requireNonNull(key2);
    requireNonNull(value2);
    if (key1.equals(key2)) {
      throw new IllegalArgumentException();
    }
    HashMap<K, V> map = new HashMap<>();
    map.put(key1, value1);
    map.put(key2, value2);
    return Collections.unmodifiableMap(map);
  }
}

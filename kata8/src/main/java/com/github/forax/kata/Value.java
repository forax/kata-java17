package com.github.forax.kata;

import java.util.Map;

import static java.util.Objects.requireNonNull;

public final class Value implements Expr {
  private final int value;

  public Value(int value) {
    this.value = value;
  }

  @Override
  public int eval(Map<String, Integer> variableMap) {
    requireNonNull(variableMap);
    return value;
  }

  @Override
  public String toString() {
    return "" + value;
  }

  public int value() {
    return value;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Value)) {
      return false;
    }
    Value value = (Value) obj;
    return this.value == value.value;
  }

  @Override
  public int hashCode() {
    return value;
  }
}

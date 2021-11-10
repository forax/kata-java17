package com.github.forax.kata;

import java.util.Map;

import static java.util.Objects.requireNonNull;

public final class Var implements Expr {
  private final String name;

  public Var(String name) {
    requireNonNull(name);
    this.name = name;
  }

  @Override
  public int eval(Map<String, Integer> variableMap) {
    requireNonNull(variableMap);
    return variableMap.getOrDefault(name, 0);
  }

  @Override
  public String toString() {
    return name;
  }

  public String name() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Var)) {
      return false;
    }
    var variable = (Var) obj;
    return name.equals(variable.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}

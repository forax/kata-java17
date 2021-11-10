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
    Integer result = variableMap.get(name);
    return result == null? 0: result;
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
    Var variable = (Var) obj;
    return name.equals(variable.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }
}

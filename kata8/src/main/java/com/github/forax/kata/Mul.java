package com.github.forax.kata;

import java.util.Map;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class Mul implements Expr {
  private final Expr left;
  private final Expr right;

  public Mul(Expr left, Expr right) {
    requireNonNull(left);
    requireNonNull(right);
    this.left = left;
    this.right = right;
  }

  @Override
  public int eval(Map<String, Integer> variableMap) {
    requireNonNull(variableMap);
    return left.eval(variableMap) * right.eval(variableMap);
  }

  @Override
  public String toString() {
    return left + " * " + right;
  }

  public Expr left() {
    return left;
  }

  public Expr right() {
    return right;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Mul)) {
      return false;
    }
    Mul mul = (Mul) obj;
    return left.equals(mul.left) && right.equals(mul.right);
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right);
  }
}

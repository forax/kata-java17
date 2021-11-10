package com.github.forax.kata;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class Expressions {
  private Expressions() {
    throw new AssertionError();
  }

  public static Expr parse(List<String> tokens) {
    requireNonNull(tokens);
    return parse(tokens.iterator());
  }

  private static Expr parse(Iterator<String> iterator) {
    String token = iterator.next();
    Expr expr;
    switch (token) {
      case "+":
        expr = new Add(parse(iterator), parse(iterator));
        break;
      case "*":
        expr = new Mul(parse(iterator), parse(iterator));
        break;
      default:
        try {
          expr = new Value(Integer.parseInt(token));
        } catch (NumberFormatException e) {
          expr = new Var(token);
        }
    }
    return expr;
  }
}

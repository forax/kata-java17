package com.github.forax.kata;

import java.util.Iterator;

class Expressions {
  private Expressions() {
    throw new AssertionError();
  }

  static Expr parse(Iterator<String> iterator) {
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

package com.github.forax.kata;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public interface Expr {
  int eval(Map<String, Integer> variableMap);

  static Expr parse(List<String> list) {
    requireNonNull(list);
    return parse(list.iterator());
  }

  private static Expr parse(Iterator<String> iterator) {
    var token = iterator.next();
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

package com.github.forax.kata;

import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public interface Expr {
  int eval(Map<String, Integer> variableMap);

  static Expr parse(List<String> tokens) {
    requireNonNull(tokens);
    return Expressions.parse(tokens.iterator());
  }
}

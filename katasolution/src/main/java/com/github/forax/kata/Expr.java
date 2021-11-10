package com.github.forax.kata;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public sealed interface Expr {
  default int eval(Map<String, Integer> variableMap) {
    return switch (this) {
      case Value value -> value.value;
      case Add add -> add.left.eval(variableMap) + add.right.eval(variableMap);
      case Mul mul -> mul.left.eval(variableMap) * mul.right.eval(variableMap);
      case Var variable -> variableMap.getOrDefault(variable.name, 0);
    };
  }

  static Expr parse(List<String> list) {
    requireNonNull(list);
    return parse(list.iterator());
  }

  private static Expr parse(Iterator<String> iterator) {
    var token = iterator.next();
    return switch (token) {
      case "+" -> new Add(parse(iterator), parse(iterator));
      case "*" -> new Mul(parse(iterator), parse(iterator));
      default -> {
        try {
          yield new Value(Integer.parseInt(token));
        } catch(NumberFormatException e) {
          yield new Var(token);
        }
      }
    };
  }

  record Value(int value) implements Expr {
    @Override
    public String toString() {
      return "" + value;
    }
  }

  record Add(Expr left, Expr right) implements Expr {
    public Add {
      requireNonNull(left);
      requireNonNull(right);
    }

    @Override
    public String toString() {
      return "(" + left + " + " + right + ")";
    }
  }

  record Mul(Expr left, Expr right) implements Expr {
    public Mul {
      requireNonNull(left);
      requireNonNull(right);
    }

    @Override
    public String toString() {
      return left + " * " + right;
    }
  }

  record Var(String name) implements Expr {
    public Var {
      requireNonNull(name);
    }

    @Override
    public String toString() {
      return name;
    }
  }
}

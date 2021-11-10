package com.github.forax.kata;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.forax.kata.TestHelper.listOf;
import static com.github.forax.kata.TestHelper.mapOf;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExprTest {

  @Test
  public void testValue() {
    Value value = new Value(13);
    assertAll(
        () -> assertEquals(13, value.value()),
        () -> assertEquals("13", value.toString()),
        () -> assertEquals(13, value.eval(mapOf()))
    );
  }
  @Test
  public void testAdd() {
    Add add = new Add(new Value(2), new Add(new Value(3), new Value(1)));
    assertAll(
        () -> assertEquals(new Value(2), add.left()),
        () -> assertEquals(new Add(new Value(3), new Value(1)), add.right()),
        () -> assertEquals("(2 + (3 + 1))", add.toString()),
        () -> assertEquals(6, add.eval(mapOf())),
        () -> assertThrows(NullPointerException.class, () -> new Add(new Value(13), null)),
        () -> assertThrows(NullPointerException.class, () -> new Add(null, new Value(31)))
    );
  }
  @Test
  public void testMul() {
    Mul mul = new Mul(new Value(3), new Add(new Value(7), new Value(4)));
    assertAll(
        () -> assertEquals(new Value(3), mul.left()),
        () -> assertEquals(new Add(new Value(7), new Value(4)), mul.right()),
        () -> assertEquals("3 * (7 + 4)", mul.toString()),
        () -> assertEquals(33, mul.eval(mapOf())),
        () -> assertThrows(NullPointerException.class, () -> new Mul(new Value(13), null)),
        () -> assertThrows(NullPointerException.class, () -> new Mul(null, new Value(31)))
    );
  }
  @Test
  public void testVar() {
    Var variable = new Var("x");
    assertAll(
        () -> assertEquals("x", variable.name()),
        () -> assertEquals("x", variable.toString()),
        () -> assertEquals(0, variable.eval(mapOf())),
        () -> assertEquals(42, variable.eval(mapOf("x", 42))),
        () -> assertThrows(NullPointerException.class, () -> new Var(null))
    );
  }

  @Test
  public void parse() {
    Expr expr = Expr.parse(listOf("+", "a", "*", "b", "3"));
    assertAll(
        () -> assertEquals(9, expr.eval(mapOf("a", 3, "b", 2))),
        () -> assertThrows(NullPointerException.class, () -> Expr.parse(null))
    );
  }

  @Test
  public void parseAll() {
    String exprsText = "+ a 4\n" +
                    "+ a b\n" +
                    "+ a * b 3\n";

    List<Expr> exprs =
        new BufferedReader(new StringReader(exprsText))
            .lines()
            .map(line -> listOf(line.split(" ")))
            .map(Expr::parse)
            .collect(Collectors.toList());

    assertEquals(listOf(7, 5, 9),
        exprs.stream().map(expr -> expr.eval(mapOf("a", 3, "b", 2))).collect(Collectors.toList()));
  }
}
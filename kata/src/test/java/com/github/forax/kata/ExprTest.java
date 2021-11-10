package com.github.forax.kata;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExprTest {

  @Test
  public void testValue() {
    var value = new Value(13);
    assertAll(
        () -> assertEquals(13, value.value()),
        () -> assertEquals("13", value.toString()),
        () -> assertEquals(13, value.eval(Map.of()))
    );
  }
  @Test
  public void testAdd() {
    var add = new Add(new Value(2), new Add(new Value(3), new Value(1)));
    assertAll(
        () -> assertEquals(new Value(2), add.left()),
        () -> assertEquals(new Add(new Value(3), new Value(1)), add.right()),
        () -> assertEquals("(2 + (3 + 1))", add.toString()),
        () -> assertEquals(6, add.eval(Map.of())),
        () -> assertThrows(NullPointerException.class, () -> new Add(new Value(13), null)),
        () -> assertThrows(NullPointerException.class, () -> new Add(null, new Value(31)))
    );
  }
  @Test
  public void testMul() {
    var mul = new Mul(new Value(3), new Add(new Value(7), new Value(4)));
    assertAll(
        () -> assertEquals(new Value(3), mul.left()),
        () -> assertEquals(new Add(new Value(7), new Value(4)), mul.right()),
        () -> assertEquals("3 * (7 + 4)", mul.toString()),
        () -> assertEquals(33, mul.eval(Map.of())),
        () -> assertThrows(NullPointerException.class, () -> new Mul(new Value(13), null)),
        () -> assertThrows(NullPointerException.class, () -> new Mul(null, new Value(31)))
    );
  }
  @Test
  public void testVar() {
    var variable = new Var("x");
    assertAll(
        () -> assertEquals("x", variable.name()),
        () -> assertEquals("x", variable.toString()),
        () -> assertEquals(0, variable.eval(Map.of())),
        () -> assertEquals(42, variable.eval(Map.of("x", 42))),
        () -> assertThrows(NullPointerException.class, () -> new Var(null))
    );
  }

  @Test
  public void parse() {
    Expr expr = Expr.parse(List.of("+", "a", "*", "b", "3"));
    assertAll(
        () -> assertEquals(9, expr.eval(Map.of("a", 3, "b", 2))),
        () -> assertThrows(NullPointerException.class, () -> Expr.parse(null))
    );
  }

  @Test
  public void parseAll() {
    var exprTexts = "+ a 4\n" +
                    "+ a b\n" +
                    "+ a * b 3\n";

    var exprs =
        exprTexts
            .lines()
            .map(line -> List.of(line.split(" ")))
            .map(Expr::parse)
            .collect(Collectors.toList());

    assertEquals(List.of(7, 5, 9),
        exprs.stream().map(expr -> expr.eval(Map.of("a", 3, "b", 2))).collect(Collectors.toList()));
  }
}
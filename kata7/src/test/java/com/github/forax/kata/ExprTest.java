package com.github.forax.kata;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static com.github.forax.kata.TestHelper.listOf;
import static com.github.forax.kata.TestHelper.mapOf;

import static org.junit.Assert.assertEquals;


public class ExprTest {

  @Test
  public void testValue() {
    Value value = new Value(13);
     assertEquals(13, value.value());
     assertEquals("13", value.toString());
     assertEquals(13, value.eval(TestHelper.<String, Integer>mapOf()));
  }
  @Test
  public void testAdd() {
    Add add = new Add(new Value(2), new Add(new Value(3), new Value(1)));
    assertEquals(new Value(2), add.left());
    assertEquals(new Add(new Value(3), new Value(1)), add.right());
    assertEquals("(2 + (3 + 1))", add.toString());
    assertEquals(6, add.eval(TestHelper.<String, Integer>mapOf()));
  }
  @Test(expected = NullPointerException.class)
  public void testAddPrecondition1() {
    new Add(new Value(13), null);
  }
  @Test(expected = NullPointerException.class)
  public void testAddPrecondition2() {
    new Add(null, new Value(31));
  }

  @Test
  public void testMul() {
    Mul mul = new Mul(new Value(3), new Add(new Value(7), new Value(4)));
    assertEquals(new Value(3), mul.left());
    assertEquals(new Add(new Value(7), new Value(4)), mul.right());
    assertEquals("3 * (7 + 4)", mul.toString());
    assertEquals(33, mul.eval(TestHelper.<String, Integer>mapOf()));
  }
  @Test(expected = NullPointerException.class)
  public void testMulPrecondition1() {
    new Mul(new Value(13), null);
  }
  @Test(expected = NullPointerException.class)
  public void testMulPrecondition2() {
    new Mul(null, new Value(31));
  }

  @Test
  public void testVar() {
    Var variable = new Var("x");
    assertEquals("x", variable.name());
    assertEquals("x", variable.toString());
    assertEquals(0, variable.eval(TestHelper.<String, Integer>mapOf()));
    assertEquals(42, variable.eval(mapOf("x", 42)));
  }
  @Test(expected = NullPointerException.class)
  public void testVarPrecondition() {
    new Var(null);
  }

  @Test
  public void parse() {
    Expr expr = Expressions.parse(listOf("+", "a", "*", "b", "3"));
    assertEquals(9, expr.eval(mapOf("a", 3, "b", 2)));
  }
  @Test(expected = NullPointerException.class)
  public void parsePrecondition() {
    Expressions.parse(null);
  }

  @Test
  public void parseAll() throws IOException {
    String exprsText = "+ a 4\n" +
                    "+ a b\n" +
                    "+ a * b 3\n";

    ArrayList<Expr> exprs = new ArrayList<>();
    StringReader stringReader = new StringReader(exprsText);
    BufferedReader bufferedReader = new BufferedReader(stringReader);
    String line;
    while((line = bufferedReader.readLine()) != null) {
      List<String> tokens = listOf(line.split(" "));
      Expr expr = Expressions.parse(tokens);
      exprs.add(expr);
    }

    ArrayList<Integer> results = new ArrayList<>();
    for(Expr expr: exprs) {
      int result = expr.eval(mapOf("a", 3, "b", 2));
      results.add(result);
    }

    assertEquals(listOf(7, 5, 9), results);
  }
}
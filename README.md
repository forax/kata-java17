# kata-java17
A kata to transform an existing program to use Java 17 new features

### Expression tree

The idea of this kata is to implement a simple expression tree able to represent the following expressions
- `Value` is a literal integer
- `Add` is an integer addition
- `Mul` is an integer multiplication
- `Var` is a variable (it's value will be pass when evaluating the expression).

Each expression is implemented by a Java class, here is an example of usage
```java
  Value value = new Value(13);
  Add add = new Add(new Value(4), new Add(new Value(3), new Value(7)));
  Mul mul = new Mul(new Value(4), new Add(new Value(3), new Value(7)));
  Var variable = new Var("a");
```

An expression can also be constructed from a string in prefix form, operator first followed by the operands,
(for example, `"+ 2 * 3 7"` is equivalent to `(2 + 3 * 7)`) using the method `parse(text)`.

Once an expression is constructed, we can ask for a string representation using `toString()` or an evaluation
using `eval(variableMap)`. The `variableMap` associate a name of a variable to its value.
```java
  Expr expr = Expr.parse("+ 4 * x 7");
  System.out.println(expr);  // (4 + x * 7) 
  
  int result = expr.eval(Map.of("x", 3));
  System.out.println(result);  // 25
```

### Kata Java versions

This kata has been written using different versions of Java
- Java 7 (Maven module [kata7](kata7))
- Java 8 (Maven module [kata8](kata8))
- Java 11 (Maven module [kata11](kata11))
- Java 17 (Maven module [kata17](kata17))
- Java 17 + preview feature (Maven module [kata17-preview](kata17-preview))

So you can start with a specific version and rewrite the code up to the point where you are using Java 17
with the preview features enabled.

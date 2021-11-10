# kata11
version of the Kata using Java 11 features

## Toward Java 17

1. [JEP 378](https://openjdk.java.net/jeps/378) introduces a syntax for multi-lines string named text block.
   Refactor `ExprTest.parseAll()` to use that syntax.

2. [JEP 361](https://openjdk.java.net/jeps/361) introduces two changes to the switch syntax
   - avoid fallthrough in switch by using `->` instead of ':'
   - allow a switch to be an expression (switch expression) instead of being only a statement
   Rewrite the code of `Expr.parse(Iterator)` to use a switch expression with the arrow syntax (`->`)
   
3. [JEP 394](https://openjdk.java.net/jeps/394) introduces a new form of `instanceof`.
   ```
   Object object = ...
   if (object instanceof String text) {
     // I can use 'text' here
   }
   ```
   So you can replace the `instanceof` in `Add.equals`, `Mul.equals`, `Value.equals` and `Var.equals`
   by a version with a type pattern.

4. [JEP 395](https://openjdk.java.net/jeps/395) introduces a new form of class named `record`
   that allow to declare data class, a class that defines unmodifiable data, in a simple way.
   So you can convert `Add`, `Mul`, `Value` and `Var` to records.

5. A record let you define a compact constructor that let you declare only the preconditions
   (the calls to `requireNonNull` in our case).
   Use compact constructors in `Add`, `Mul` and `Var`, to make the code simpler.

6. The current API has a serious issue, the interface can be implemented by anyone but at the same time,
   `Expr.parse()` restricts the possible implementations to `Add`, `Mul`, `Value` and `Var`.
   [JEP 409](https://openjdk.java.net/jeps/409) introduces the concept of sealed class that we can leverage
   to disallow other implementations of `Expr` than `Add`, `Mul` and `Var`.
   Add the keyword `sealed` and the list of permitted classes to `Expr`.

7. If all implementations of `Expr` are in the same Java file, then the compiler can infer the permitted
   classes automatically. Move `Add`, `Mul` and `Var` into the class `Expr` as member classes
   so you can omit the `permits` clause.

Once all these refactoring are done, you can continue with the
[migration to use Java 17 preview features](../kata17-preview/README.md).
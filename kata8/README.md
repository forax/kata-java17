# kata8
version of the Kata using Java 8 features

## Toward Java 11

1. [JEP 269](https://openjdk.java.net/jeps/269) introduces convenient factory methods to create
   unmodifiable list and map. Rewrite `ExprTest` to use `List.of(...)` and `Map.of(...)`
   and remove the class `TestHelper` which is not needed anymore.
   
2. Java 11 adds a method
   [String.lines()](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html#lines())
   to the API.
   In `ExprTests.parseAll()`, the code can be simplified leveraging `String.lines()`
   instead of creating a `StringReader` and a `BufferedReader`.

3. [JEP 213](https://openjdk.java.net/jeps/213) allow private methods to be declared in interface.
   The class `Expressions` is not necessary anymore because the code of `Expressions.parse(Iterator)`
   can be moved into `Expr`.

4. [JEP 286](https://openjdk.java.net/jeps/286) introduces local variable type inference using 'var' so
   all local variable declarations can be rewritten using 'var'.

Once all these refactoring are done, you can continue with the [migration to Java 17](../kata17/README.md).

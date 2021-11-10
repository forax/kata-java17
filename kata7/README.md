# kata7
version of the Kata using Java 7 features

### Test with JUnit 4
JUnit 5 is compatible with Java 8+, so the [testing class](src/test/java/com/github/forax/kata/ExprTest.java)
uses the previous versions of JUnit, JUnit 4.

### Toward Java 8

1. We first need to [migrate](https://junit.org/junit5/docs/current/user-guide/#migrating-from-junit4)
   all the tests from JUnit 4 to JUnit 5.
   - use @Test from `org.junit.Test` instead of `@Test` from ``org.junit.jupiter.api.Test
   - use `org.junit.jupiter.api.Assertions` instead of `org.junit.Assert` for methods like `assert*`
   - use `assertThrows(NPE.class, ...)` instead of `org.junit.Test(expected=NPE.class)`
   - group all `assert*` by wrapping them inside a `insertAll(...)`
   
2. In `ExprTest`, as part of the introduction of lambda ([JCP 335](https://www.jcp.org/en/jsr/detail?id=335)),
   the inference of method has been overhauled so  explicitly indicating the type arguments in front of
   `listOf` and `mapOf` is not necessary anymore.

3. In the method `ExprTest.parseAll`, the two loops can be re-written using
   [streams](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html).
   Note: BufferedReader has a method
   [lines()](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/BufferedReader.html#lines())
   that returns a stream.

4. In `Var.eval()`, instead of using `Map.get()`, we can use
   [Map.getOrDefault()](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html#getOrDefault(java.lang.Object,V))
   to simplify a littel bit the code.

Once all these refactoring are done, you can continue with the [migration to Java 11](../kata11/README.md).



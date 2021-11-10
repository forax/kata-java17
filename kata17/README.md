# kata17
version of the Kata using Java 17 features

## Toward Using Java 17 with the preview features enabled

1. [JEP 406](https://openjdk.java.net/jeps/406) enhances the switch to support switching on any objects.
   We can leverage that to simplify the code by transforming the polymorphic method `Exp.eval()`
   into a switch. I will make the algorithm clearer by having all bits in once place instead
   of having each parts declared in the implementation classes.
   Note: we can do that because the hierarchy is closed so we control all implementations

   There is a nice interaction between `sealed` and `switch()` if a hierarchy is sealed, the compiler
   will not allow a `default`. Which is nice because it means that if a subtype is added, the compiler
   will force you to add a new case.

2. That's all for now, soon [JEP 405](https://openjdk.java.net/jeps/405)
   will be implemented, this will avoid to use accessors in the switch, but there is no available
   prototype implementing that JEP yet.

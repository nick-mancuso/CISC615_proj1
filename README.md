# Interface Based Approach

## String

| Characteristic | b1       | b2         | b3               | b4     | b5     |
|----------------|----------|------------|------------------|--------|--------|
| null           | true     | false      |                  |        |        |
| length         | zero     | one        | greater than one |        |        |

Justification: <br>
It is trivial to see why the "null" and "length" characteristics are both pairwise
disjoint and complete. It should be noted that as of Java 9, Strings in Java are 
stored using [variable encoding](https://www.baeldung.com/java-9-compact-string),
so it is not necessary to test the encoding of the string as we mentioned in class.
The two possible encodings (`LATIN1` and `UTF16`) that an actual "String" object could
be encoded in are transparent to developers and do not need to be tested in this case.
Only if this method accepted a `byte[]` as a parameter, would we need to consider 
`UTF-8`,`US-ASCII`, etc.


## Integer

| Characteristic           | b1       | b2       | b3   |
|--------------------------|----------|----------|------|
| relation of value to zero | less | greater | zero |
| max int                  | true     | false    |      |
| min int                  | true     | false    |      |
| null                     | true     | false    |      |

Justification:<br>
As discussed in class, we can partition the values of an integer into "< 0", "0", and "> 0"
(*characteristic #1*). This set is both pairwise disjoint and complete (cover the domain). 
Specifically testing values like `Integer.MIN_VALUE` and `Integer.MAX_VALUE` help to explore the
boundries of our input domain(*characteristic #2, #3*); true and false are disjoint and complete.
We must also include `null` as an input, since this parameter is an object (as opposed to an `int`)
(*characteristic #4*).


# Functionality based approach

## Arabic
| Characteristic         | b1   | b2    | b10 |
|------------------------|------|-------|----|
| Contains "0"?          | true | false |    |
| Contains "1"?          | true | false |    |
| Contains "2"?          | true | false |    |
| Contains "3"?          | true | false |    |
| Contains "4"?          | true | false |    |
| Contains "5"?          | true | false |    |
| Contains "6"?          | true | false |    |
| Contains "7"?          | true | false |    |
| Contains "8"?          | true | false |    |
| Contains "9"?          | true | false |    |
| Number of place values | 1   | ...   | 10 |
|                        |      |       |    |

Justification:<br>
In Java, preceding zeroes in the declaration of an `int` or `Integer` are removed by the compiler
and have no effect on the value of the variable; so we do not need to test this characteristic:
```bash

➜  src cat Test.java  
public class Test {
    public static void main(String[] args) {
        Integer x = 2;
        Integer y = 000000000000000000000000000000000000000002;
        System.out.println(x.equals(y));
        System.out.println(y);

    }
}
➜  src javac Test.java
➜  src java --enable-preview ... Test
true
2

```

The number of place values characteristic is both disjoint and complete;
the maximum number of place values for a Java Integer is 10 (the number of 
place values in `Integer.MAX_VALUE`), so 0 ... 10 is a complete set, and
disjoint because no partition can have exactly the same number of place
values.

## Roman
| Characteristic          | b1    | b2    | b...  |
|-------------------------|-------|-------|-------|
| letter case             | upper | lower |       |
| is zero                 | true  | false |       |
| value of number         | 1     | ...   | 3,999 |
| invalid input           | true  | false |       |
| subtractive notation    | true  | false |       |

Justification:<br>


Some notes:
 - Roman numeral can't be negative - should throw `NoSuchElementException`
 - Zero should throw `NoSuchElementException`
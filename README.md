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
| Total Number of place values | 1   | ...   | 10 |
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
For all cases here, it is simple to see that all partitioning is disjoint and complete;
the sequence of numbers from 1 to 3,999 can also be used for the input space of the "letter case"
characteristic for each case.


Some notes:
 - Roman numeral can't be negative - should throw `NoSuchElementException`
 - Zero should throw `NoSuchElementException`

#Test Case Criterion
I have decided to generate test requirements using the "all combinations" criteria. I
chose this criterion for the following reasons:
 - There are well-defined ranges for all input values
 - Only one parameter per method, with only two methods to test. This means that we have many
   resources to use for a small amount of code.
 - Test suite still runs in under 20 seconds on an average machine.
 - In this case, it is easier to use all input values than to carefully select some.

#Modifications
###RomanConverter1
I modified the methods in `RomanConverter1` in the following ways:
 - Rewrote each method to have only one return point
 - Added ability to use inverted map in `RomanConverter1#fromRoman` method
 - Cleaned up code
 - Added exception handling to support test cases
 - Modified data structures & syntax to take advantage of Java 16
 - Extracted common data structures and methods to `Utils.java` to share
with `RomanConverter2`

###RomanConverter2
I modified the methods in `RomanConverter2` in the following ways:
 - Cleaned up code
 - Added exception handling to support test cases

#Results

 - Fully implemented test suite and coverage check can be run using the
`mvn clean verify` maven lifecycle phase:
```bash
...
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running org.project.TestFunctionalityBasedApproach2
[INFO] Tests run: 12032, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.728 s - in org.project.TestFunctionalityBasedApproach2
[INFO] Running org.project.TestFunctionalityBasedApproach1
[INFO] Tests run: 12032, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.499 s - in org.project.TestFunctionalityBasedApproach1
[INFO] Running org.project.TestInterfaceBasedApproach1
[INFO] Tests run: 14, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s - in org.project.TestInterfaceBasedApproach1
[INFO] Running org.project.TestInterfaceBasedApproach2
[INFO] Tests run: 14, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s - in org.project.TestInterfaceBasedApproach2
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 24092, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.7:report (report) @ CISC615_proj1 ---
[INFO] Loading execution data file /home/nick/IdeaProjects/CISC615_proj1/target/jacoco.exec
[INFO] Analyzed bundle 'CISC615_proj1' with 4 classes
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.7:check (check) @ CISC615_proj1 ---
[INFO] Loading execution data file /home/nick/IdeaProjects/CISC615_proj1/target/jacoco.exec
[INFO] Analyzed bundle 'CISC615_proj1' with 4 classes
[INFO] All coverage checks have been met.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  10.422 s
[INFO] Finished at: 2021-09-14T16:43:08-04:00
[INFO] ------------------------------------------------------------------------
 ```
*relevant maven output*


 - Site generation (coverage reports and test results) can be generated
by running `mvn site`
 - Published report can be found [here](https://nmancus1.github.io/RomanConverter-site/surefire-report.html).

###Description of Results
With 100% code coverage (per Jacoco), and all tests passing, I have a high
level of confidence in the functionality based reports, since they produced the
highest number of test cases. The interface based test cases really don't capture
the nuances of the Roman numeral system.

###Final Thoughts
For a mission critical setting,
I would feel fairly confident that either approach (`RomanConverter1` 
or `RomanConverter2`) would be acceptable. I think that between the interface
based approach and the functionality based appraoch for IDM, we have covered all
possible inputs of interest to the given methods.
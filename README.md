## Interface Based Approach

#### String
 - null
 - empty
 - encoding

### Integer

| Characteristic           | b1       | b2       | b3   |   |   |   |   |
|--------------------------|----------|----------|------|---|---|---|---|
| relation of side to zero | negative | positive | zero |   |   |   |   |
| max int                  | true     | false    |      |   |   |   |   |
| min int                  | true     | false    |      |   |   |   |   |
| null                     | true     | false    |      |   |   |   |   |

### Functionality based approach

#### Arabic
 - preceding zeroes: should be ignored
 - is the numeral a 0? 1? 2? ...


#### Roman
 - can't be negative - should expect exception
 - difference in case? should be tested
 - is there a max? Yes, 3,999
 - what about zero
 - is the numeral a I? V? ...
 - question about each numeral: is this numeral a valid numeral?
 - too big, too small
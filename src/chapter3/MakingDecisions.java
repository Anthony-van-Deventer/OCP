package chapter3;

import java.util.Random;

public class MakingDecisions {

    public void patternMatchingWithInstanceOf(Number number){
        //intValue is a patternVariable
        //pattern variable must be a subtype of the variable on the left side of the expression + it cannot be the same type


        //pattern matching uses flowScoping - meaning the variable is only in scope when the compiler can
        //definitely determine its type
        if(number instanceof Integer intValue && intValue.equals(5)){
            //only executes if instanceOf is true so avoid classCastException
            System.out.println(intValue.compareTo(5));
        }

    }

    void printIntegersOrNumbersGreaterThan5(Number number) {
        /*this does not compile because of flowScoping - meaning the value cannot definitely determine the type of
         * the local variable "data" - because if number isnt an instanceOf data then it cannot determine its type therefor
         * it is not in scope
         */
        if(number instanceof Integer data || data.compareTo(5)>0)
            System.out.print(data);
    }

    void printOnlyIntegers(Number number) {
        if (!(number instanceof Integer data))
            return;
        /*so you would think because the data pattern variable is only in scope for the if statement but this isnt the case because the
        compiler can determine the type of the pattern variable, it is kept in scope ( you can think of it almost like an if-else
         */

        System.out.print(data.intValue());
    }

    void genericSwitchStatement(){
        int testLocalVariable = new Random().nextInt();

        switch (testLocalVariable){ //switch variable can be all primitive dataTypes and their wrappers + enum and var if it resolves to one of the forementioned
            case 1: //case variables must be compile time constant (literals, enums, final)
                System.out.println(1);
                break;// break is optional but if not included then every following branch will be executed (including default)
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
            case 4,5: //since java 14 we can now combine case values, prior to java14 it would have been case1: case2: sout(whatever)
                System.out.println("4 or 5");
                break;
            default:
                System.out.println("default");
        }
        //this does compile, just remember this is valid
        switch (testLocalVariable){}

    }

    void showHowCaseVariablesMustBeFinal(){
        //Case variables need to be final && known at compileTime
        final int something = 2;
        final int somethingElse = getSomethingElse();
        int otherThing = 3;
        /*
        so the values for case variables need to be known at compile time, so somethingElse (even though it is final) will only be known at runtime and
        not compile time so it wont compile
        Methods are only evaluated at runtime

        expressions are allowed as case values if the value can be resolved at compileTime

        case values must be the same dataType as switch values
         */
        switch (3){
            case something:
                int ook = something;
                System.out.println(something);
                break;
            case somethingElse:
                System.out.println(somethingElse);
                break;
            case otherThing:
                System.out.println(otherThing);
                break;
            case 3*5:
                System.out.println("works");
                break;
        }

    }

    int getSomethingElse(){
        return 1;
    }
/*
RULES:
1) all branches that do not throw an exception must return consistent data type (can be void, but then you cant have one void and one int)
2) if the switch expression returns a value then every branch that isnt an expression must yield a value - so if you use it like we use test here
but if we didnt assign returnValue to the value of the switch expression then you could have not returned anything  in the case branches
 */
    void switchExpressionExample(){
        //added in java14
        //capable of returning a value - if all branches and default return a value
        int test = new Random().nextInt();
        //it is possible to use a switch expression that doesnt return a value
        int returnValue = switch (test){ //with the switch expression we remove the need for breaks
            case 1 -> 2; //need a semicolon after each switch expression
            case 4 -> 7;
            case 9 -> 12; // -> arrow operator is not a lambda in a case branches
            case 555 ->{
                System.out.println("edge case");
                yield -9; // yield necessary for {} code block
            }
            default -> 0; //necessary if all possible values of switch variable cannot be handled
        };


    }

}

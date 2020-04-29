import java.util.function.*;

/**
 * Lambda Expressions - 01
 * Functional Interface - Abstract functions
 */

public class Lambda_01 {
    public static void main(String[] args) {

        /**
         * Predicate
         * Using test() method to check the result
         */

        Predicate<String> stringLen = (s) -> s.length() < 10;
        System.out.println(stringLen.test("Apple") + " - Apple length is less than 10");

        /**
         * Consumer
         * Using accept() method
         */

        Consumer<String> strCaseChange = (s) -> System.out.println("String case change into lowerCase: " + s.toLowerCase());
        strCaseChange.accept("ABCdefGHIJklm");

        /**
         * Function
         * Using apply() method
         */

        Function<Integer,String> converter = (s) -> Integer.toString(s);
        System.out.println("Length of 26: " + converter.apply(26).length());

        /**
         * Supplier
         * Using get() method
         */

        Supplier<String> s = () -> "Return supplier function";
        System.out.println(s.get());

        /**
         * UnaryOperator
         * Using apply() method
         */

        UnaryOperator<String> str = (msg) -> msg.toUpperCase();
        System.out.println("String change into upperCase: " + str.apply("abcdEFgHiJ"));


        /**
         * BinaryOperator
         * Using apply() method
         */

        BinaryOperator<Integer> add = (a,b) -> a+b;
        System.out.println("Add 3 and 4 is : " + add.apply(3,4));


    }
}

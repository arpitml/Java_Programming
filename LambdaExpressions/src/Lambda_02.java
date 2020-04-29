import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Lambda Expressions - 02
 * Handling Inner classes
 * BiFunctions
 * Custom Function Interface
 */
public class Lambda_02 {
    public static void main(String[] args) {

        /**
         * Handling Inner classes
         */

        // Example - Runnable Interface without Lambda Expression
            Runnable runnable1 = new Runnable() {
                @Override
                public void run() {
                    System.out.println("1 run() method called ");
                }
            };

        // Example - Runnable Interface with Lambda Expression
        Runnable runnable2 = () -> System.out.println("2 run() method called");

        runnable1.run();
        runnable2.run();


        /**
         * BiFunction
         * Using apply() method and return the result with respective datatype
         */

        BiFunction<Integer,Integer,String> integerLength = (a,b) -> Integer.toString(a+b) ;
        System.out.println("Integer Sum Length 57 & 99 is: " + integerLength.apply(57,99).length());

        /**
         * Custom Function Interface
         */

        // Example - Greeting to all member without custom interface
        Consumer<String> greeting = (s) -> System.out.println("Hello " + s);
        for(String name : Arrays.asList("Duke, Martin, Hansel"))
            greeting.accept(name);

        // Example - Greeting to all member with custom interface
        GreetingFunction greeting1 = (s) -> System.out.println("Hello " + s);
        for(String name : Arrays.asList("Duke, Martin, Hansel"))
            greeting1.sayGreeting(name);

    }

    @FunctionalInterface
    interface GreetingFunction{
        void sayGreeting(String msg);
    }

}

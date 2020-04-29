/**
 * Lambda Expressions
 * Custom Function Inteface
 */

public class Lambda_04 {

    @FunctionalInterface
    interface Calculator{
        int cal(int x, int y);
    }

    public static void main(String[] args) {

        Calculator add = (a,b) -> a + b;
        Calculator mul = (a,b) -> a * b;
        Calculator sub = (a,b) -> (a - b > 0 ? a - b : b - a);
        Calculator div = (a,b) -> (b !=0 ? a / b : 0);

        System.out.println("Add 3 & 4 is: " + add.cal(3,4));

        System.out.println("Mul 3 & 4 is: " + mul.cal(3,4));

        System.out.println("Sub 3 & 8 is: " + sub.cal(3,8));
        System.out.println("Sub 8 & 3 is: " + sub.cal(8,3));


        System.out.println("Div 3 & 0 is: " + div.cal(3,0));
        System.out.println("Div 8 & 2 is: " + div.cal(8,2));
    }
}

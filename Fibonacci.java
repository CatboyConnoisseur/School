import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {

        int output = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a number");
        int number = input.nextInt();
        int[] Fibbo = {0, 1, 1, 2, 3, 4, 8, 13};
        if (number < 7) {
            for (int i = 0; i < number; i++) {
                output = Fibbo[i];
            }
            System.out.println("Your Fibbonacci number is" + output);

        }
    }
}
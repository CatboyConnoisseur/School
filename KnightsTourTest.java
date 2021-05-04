import java.util.Scanner;
import java.util.Random;

public class KnightsTourTest{

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        Random myRandom = new Random();
        KnightsTour myKnightsTour = new KnightsTour();
        int x, y;


        while( 1 == 1)
        {

            //System.out.println("Enter the x coordinate of the knight: ");
            x = myRandom.nextInt(8);
            //x = input.nextInt();
            //System.out.println("Enter the y coordinate of the knight: ");
            y = myRandom.nextInt(8);
            //y = input.nextInt();

            myKnightsTour.setX(x);
            myKnightsTour.setY(y);

            while(myKnightsTour.moveKnight(myKnightsTour.getX(),
                    myKnightsTour.getY()))
                ;

            if(myKnightsTour.getNumMoves() == 64)
                break;
            else
                myKnightsTour.reinitializeBoard();
        }

        System.out.println("YOU WON!!!!");
    }

}
import java.util.Random;

public class KnightsTour
{

    private boolean board[][];
    private int x, y;
    private int numberOfMoves;

    public KnightsTour()
    {
        board = new boolean[8][8];

        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                board[i][j] = false;

        numberOfMoves = 1;
    }

    public int 	getX(){			return x;}
    public void setX(int x){ 	this.x = x;}

    public int 	getY(){			return y;}
    public void setY(int y){	this.y = y;}

    public int getNumMoves(){	return numberOfMoves;}


    public boolean moveKnight(int x, int y)
    {
        Random myRandom = new Random();
        int moveNumber, j;
        boolean madeMove = false;
        boolean moves[] = new boolean[8];

        for(int i = 0; i < 8; i++)
            moves[i] = false;

        while(!madeMove)
        {
            moveNumber = myRandom.nextInt(8);

            switch (moveNumber)
            {
                case 0:
                    if(checkMove(x+1, y+2))
                        madeMove = makeMove(x+1, y+2);  //Move 1 up 2 right
                    break;
                case 1:
                    if(checkMove(x-1, y+2))	//Move 2 up 2 left
                        madeMove = makeMove(x-1, y+2);
                    break;
                case 2:
                    if(checkMove(x+2, y+1))	//Move 3 right 2 up
                        madeMove = makeMove(x+2, y+1);
                    break;
                case 3:
                    if(checkMove(x+2, y-1))	//Move 4 right 2 down
                        madeMove = makeMove(x+2, y-1);
                    break;
                case 4:
                    if(checkMove(x-1, y-2))	//Move 5 down 2 left
                        madeMove = makeMove(x-1, y-2);
                    break;
                case 5:
                    if(checkMove(x+1, y-2))	//Move 6 down 2 right
                        madeMove = makeMove(x+1, y-2);
                    break;
                case 6:
                    if(checkMove(x-2, y+1))	//Move 7 left 2 up
                        madeMove = makeMove(x-2, y+1);
                    break;
                case 7:
                    if(checkMove(x-2, y-1))	// Move 8 left 2 down
                        madeMove = makeMove(x-2, y-1);
            }

            if(!madeMove)
                moves[moveNumber] = true;


            for(j = 0; j < 8; j++)
                if(!moves[j])
                    break;

            if(j < 8)
                continue;
            else
                break;
        }

        if(!madeMove)
            return false;
        else
            return true;
    }

    public boolean checkMove(int x, int y)
    {
        if((y >= 0) && (y < 8) )
            if((x >= 0) && (x < 8))
                if(!board[x][y])
                    return true;

        return false;

    }

    public boolean makeMove(int x, int y)
    {
        System.out.printf("Move %d from (%d,%d) to (%d,%d)\n",
                numberOfMoves++, this.x, this.y, x, y);

        //New Knight Position
        this.x = x;
        this.y = y;

        board[x][y] = true;

        return true;
    }


    public void reinitializeBoard()
    {
        board = new boolean[8][8];

        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                board[i][j] = false;

        numberOfMoves = 1;
    }
}
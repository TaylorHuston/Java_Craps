
import java.security.SecureRandom;

/*
 * Simulation of a game of Craps.
 * Used to demonstrate random values and enums.
 * Based on "Java How To Program, 10/e, Early Objects" - Chapter 6.
 */

public class Craps {
    private static final SecureRandom randNum = new SecureRandom();
    private enum Status { //Enum of different possible statuses
        CONTINUE,
        WON,
        LOST
    };
    
    //Common slang for roll values
    private static final int SNAKE_EYES = 2;
    private static final int TREY = 3;
    private static final int SEVEN = 7;
    private static final int YO_LEVEN = 11;
    private static final int BOX_CARS = 12;
    
    public static void main(String[] args) {
        int myPoint = 0;
        Status gameStat = Status.CONTINUE;  //Can contain the enum values
        
        int diceSum = rollDice();  //First roll
        
        //Game outcome of first roll
        switch (diceSum) {
            case SEVEN:  //Win with 7 or 11 on first roll
            case YO_LEVEN:
                 gameStat = Status.WON;
                break;
            case SNAKE_EYES:  //Lose with 2,3 or 12 on first roll
            case TREY:
            case BOX_CARS:
                gameStat = Status.LOST;
                break;
            default:
               gameStat = Status.CONTINUE;
               myPoint = diceSum;
               System.out.printf("Pont is %d%n", myPoint);
               break;
        }    
        
        //While game is still going
        while (gameStat == Status.CONTINUE) {
            diceSum = rollDice();  //Roll again
            
            if (diceSum == myPoint) {
                gameStat = Status.WON;  //Matched point on new roll
            } else if (diceSum == SEVEN) {
                gameStat = Status.LOST;  //Rolled 7 before matching point
            }
        }
        
        //Outcome message
        if (gameStat == Status.WON) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose :(");
        }
    } //End of main
    
    public static int rollDice() {
        int die1 = 1 + randNum.nextInt(6);
        int die2 = 1 + randNum.nextInt(6);
        
        int sum  = die1 + die2;
        
        System.out.printf("You rolled %d + %d = %d%n", die1, die2, sum);
        
        return sum;
    } //End of rollDice
    
} //End of class Craps

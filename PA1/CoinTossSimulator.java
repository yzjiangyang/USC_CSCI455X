// Name:
// USC NetID:
// CS 455 PA1
// Spring 2021

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;
public class CoinTossSimulator {
    private int numTrials;
    private int twoHeads;
    private int twoTails;
    private int headAndTail;
   /**
      Creates a coin toss simulator with no trials done yet.
   */
    public CoinTossSimulator() {
        numTrials = 0;
        twoHeads = 0;
        twoTails = 0;
        headAndTail = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
    @param numTrials  number of trials to for simulation; must be >= 1
    */
    public void run(int numTrials) {
        this.numTrials += numTrials;
        for (int i =1; i <= numTrials; i++) {
            Random simulation = new Random();
            int firstCoin = simulation.nextInt(2);
            int secondCoin = simulation.nextInt(2);
            int result = firstCoin + secondCoin;
            if (result == 0) {
                twoTails += 1;
            } if (result == 1) {
                headAndTail += 1;
            } if (result == 2) {
                twoHeads += 1;
            }
    }
}


   /**
      Get number of trials performed since last reset.
   */
    public int getNumTrials() {
        return numTrials; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
    public int getTwoHeads() {
       return twoHeads; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
    public int getTwoTails() {
       return twoTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
    public int getHeadTails() {
       return headAndTail; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
    public void reset() {
        numTrials = 0;
        twoHeads = 0;
        twoTails = 0;
        headAndTail = 0;

   }

}
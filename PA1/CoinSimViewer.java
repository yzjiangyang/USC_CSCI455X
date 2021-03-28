import javax.swing.JFrame;
import java.util.Scanner;

/**
 * CoinSimViewer class
 * Contains main method for input of number of trials, creates JFrame containing CoinSimComponent class
 * @author Administrator
 *
 */
public class CoinSimViewer {
   public static final int WINDOW_HEIGHT = 500;
   public static final int WINDOW_WIDTH = 800;
   /**
    * Main Method
    * @param args 
    */
   public static void main(String[] args) {
      int numTrialsInput = 0;
      Scanner in = new Scanner(System.in);
      while (numTrialsInput <= 0) {   //Input error checking for negative int
         System.out.print("Enter num of trials: ");
         numTrialsInput = in.nextInt();
         if (numTrialsInput <= 0) {
            System.out.println("ERROR: Number entered must be greater than 0.");
         }
      } 
      in.close();
      // Create the simulator object and run it
      CoinTossSimulator simulator = new CoinTossSimulator();
      simulator.run(numTrialsInput);
      // Obtaining data
      int numTrials = simulator.getNumTrials();
      int twoHeads = simulator.getTwoHeads();
      int twoTails = simulator.getTwoTails();
      int headTails = simulator.getHeadTails();
      
      // Create frame and draw information
      JFrame frame = new JFrame();
      frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      frame.setTitle("CoinSim");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      CoinSimComponent component = new CoinSimComponent(numTrials);
      frame.add(component);
      
      frame.setVisible(true);
   }

}
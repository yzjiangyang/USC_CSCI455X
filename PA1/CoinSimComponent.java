import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class CoinSimComponent extends JComponent {
   private int numTrials;
   private int twoHeads;
   private int twoTails;
   private int headAndTail;
   public static final int BW = 50;
   public final int VB = 30;
   public final double PERCENT = 100;
      
   
   public CoinSimComponent(int numTrials) {
      CoinTossSimulator simulation = new CoinTossSimulator();
      simulation.run(numTrials);

      twoHeads = simulation.getTwoHeads();
      twoTails = simulation.getTwoTails();
      headAndTail = simulation.getHeadTails();
      this.numTrials = numTrials;
   }
   
   
   public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      // Get lable height
      // String label = "test";
      // Graphics2D g2 = (Graphics2D) g;
      // Font font = g2.getFont();
      // FontRenderContext context = g2.getFontRenderContext();
      // Rectangle2D labelBounds = font.getStringBounds(label, context);
      // int labelHeight = (int) labelBounds.getHeight();

      int windowWidth = getWidth();
      int windowHeight = getHeight();
      // Percentages
      int twoHeadsPercentage = (int) Math.round(twoHeads / (double) numTrials * PERCENT);
      int twoTailsPercentage = (int) Math.round(twoTails / (double) numTrials * PERCENT);
      int headAndTailPercentage = (int) Math.round(headAndTail / (double) numTrials * PERCENT);
      // Labels
      String twoHeadsLabel = "Two Heads: " + twoHeads + " (" + twoHeadsPercentage + "%)";
      String twoTailsLabel = "Two Tails: " + twoTails + " (" + twoTailsPercentage + "%)";
      String headAndTailLabel = "A Head and a Tail: " + headAndTail + " (" + headAndTailPercentage + "%)";
      // Scales
      double scale = (double) ((windowHeight - 2 * VB) / PERCENT);
      
      // Create and draw the three Bar objects
      Bar twoHeadsBar = new Bar(windowHeight - VB, windowWidth * 1/4 - BW / 2, 
            BW, twoHeadsPercentage, scale, Color.red, twoHeadsLabel);
      Bar headAndTailBar = new Bar(windowHeight - VB, windowWidth * 1/2 - BW / 2, 
            BW, headAndTailPercentage, scale, Color.green, headAndTailLabel);
      Bar twoTailsBar = new Bar(windowHeight - VB, windowWidth * 3/4 - BW / 2, 
            BW, twoTailsPercentage, scale, Color.blue, twoTailsLabel);
      twoHeadsBar.draw(g2);
      headAndTailBar.draw(g2);
      twoTailsBar.draw(g2);
   }


}
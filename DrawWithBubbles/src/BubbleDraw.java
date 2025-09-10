import javax.swing.JFrame;
import java.awt.Dimension;

public class BubbleDraw {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Dae'Loki's BubbleDraw App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BubblePanel bubblePanel = new BubblePanel();
        frame.getContentPane().add(bubblePanel);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
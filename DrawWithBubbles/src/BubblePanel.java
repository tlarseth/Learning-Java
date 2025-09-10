import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

public class BubblePanel extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    Random rand = new Random();
    ArrayList<Bubble> bubbleList;
    int size = 25;
    private int mouseX, mouseY;
    private JPanel buttonPanel;
    private int buttonPanelX, buttonPanelY;
    private JButton exitButton;

    public BubblePanel() {
        bubbleList = new ArrayList<Bubble>();
        setBackground(Color.BLACK);
        setLayout(null);
        BubbleListener listener = new BubbleListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
        addMouseWheelListener(listener);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        JButton clearButton = new JButton("Clear");
        clearButton.setFocusable(false);
        clearButton.addActionListener(e -> {
            bubbleList.clear();
            repaint();
        });
        clearButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                buttonPanelX = e.getX();
                buttonPanelY = e.getY();
            }
        });
        clearButton.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int newX = buttonPanel.getX() + e.getX() - buttonPanelX;
                int newY = buttonPanel.getY() + e.getY() - buttonPanelY;
                buttonPanel.setLocation(newX, newY);
            }
        });
        buttonPanel = new JPanel(null);
        buttonPanel.setOpaque(false);
        buttonPanel.add(clearButton);
        clearButton.setBounds(0, 0, 80, 25);
        add(buttonPanel);
        buttonPanel.setBounds(0, 0, 80, 25);
        exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        exitButton.setBounds(50, 50, 80, 25);
        exitButton.addActionListener(e -> {});
        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                int newX = rand.nextInt(BubblePanel.this.getWidth() - exitButton.getWidth());
                int newY = rand.nextInt(BubblePanel.this.getHeight() - exitButton.getHeight());
                exitButton.setLocation(newX, newY);
            }
        });
        add(exitButton);
    }
    @Override
    public void paintComponent(Graphics canvas) {
        super.paintComponent(canvas);
        for (Bubble b : bubbleList) {
            b.draw(canvas);
        }
    }
    public void testBubbles() {
        for (int n = 0; n < 100; n++) {
            int x = rand.nextInt(600);
            int y = rand.nextInt(400);
            int randomSize = rand.nextInt(50) + 10;
            bubbleList.add(new Bubble(x, y, randomSize));
        }
        repaint();
    }
    public void addBubble(int x, int y, int size) {
        bubbleList.add(new Bubble(x, y, size));
        repaint();
    }
    public void removeBubbles(int x, int y) {
        for (int i = bubbleList.size() - 1; i >= 0; i--) {
            Bubble b = bubbleList.get(i);
            double distance = Math.sqrt(Math.pow(x - b.x, 2) + Math.pow(y - b.y, 2));
            if (distance <= b.size / 2) {
                bubbleList.remove(i);
            }
        }
        repaint();
    }
    public void setMousePosition(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
    }
    public void setSize(int newSize) {
        this.size = newSize;
        if (this.size <= 2) {
            this.size = 42;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_M) {
            bubbleList.add(new Bubble(mouseX, mouseY, 50, true));
            repaint();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
}
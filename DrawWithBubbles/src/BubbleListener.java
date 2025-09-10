import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;

public class BubbleListener extends MouseAdapter {
    private BubblePanel panel;
    public BubbleListener(BubblePanel panel) {
        this.panel = panel;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        handleMouseEvent(e);
        panel.setMousePosition(e.getX(), e.getY());
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        handleMouseEvent(e);
        panel.setMousePosition(e.getX(), e.getY());
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        panel.setSize(panel.size + e.getUnitsToScroll());
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        panel.setMousePosition(e.getX(), e.getY());
    }
    private void handleMouseEvent(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            panel.addBubble(e.getX(), e.getY(), panel.size);
        } else if (SwingUtilities.isRightMouseButton(e)) {
            panel.removeBubbles(e.getX(), e.getY());
        }
    }
}
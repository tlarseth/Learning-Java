import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Bubble {
    public int x;
    public int y;
    public int size;
    public Color color;
    private static BufferedImage mafuyuImage;
    private boolean isImage;
    private static Random rand = new Random();

    static {
        try {
            InputStream is = Bubble.class.getResourceAsStream("MafuyuMom.jpg");
            if (is != null) {
                mafuyuImage = ImageIO.read(is);
            } else {
                System.err.println("Image file not found: MafuyuMom.jpg");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading image: MafuyuMom.jpg");
        }
    }

    public Bubble(int newX, int newY, int newSize) {
        this.x = newX;
        this.y = newY;
        this.size = newSize;
        this.isImage = false;
        this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    public Bubble(int newX, int newY, int newSize, boolean isImage) {
        this.x = newX;
        this.y = newY;
        this.size = newSize;
        this.isImage = isImage;
    }

    public void draw(Graphics canvas) {
        if (isImage && mafuyuImage != null) {
            canvas.drawImage(mafuyuImage, x - size, y - size, size * 2, size * 2, null);
        } else {
            canvas.setColor(color);
            canvas.fillOval(x - size / 2, y - size / 2, size, size);
        }
    }
}
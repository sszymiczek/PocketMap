import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GraphicDemo extends JPanel {
    private static int SQUARE_WIDTH = 20;
    private Graphics2D graphic2D;
    private Random random = new Random();

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        this.setBackground(Color.BLACK);
        this.graphic2D = (Graphics2D) graphic;
        random.setSeed(2137);
        fillWithSquares(500, 500);
    }

    public void fillWithSquares(int xPixels, int yPixels){
        int maxX = (int) xPixels / SQUARE_WIDTH;
        int maxY = (int) yPixels / SQUARE_WIDTH;

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                paintAtCoordinates(i, j, randomColor());
            }
        }
    }

    public void paintAtCoordinates(int x, int y, Color color){
        this.graphic2D.setColor(color);
        graphic2D.fillRect(SQUARE_WIDTH * x, SQUARE_WIDTH * y, SQUARE_WIDTH, SQUARE_WIDTH);
    }

    public Color randomColor(){
        int rnd = random.nextInt(4);
        return switch (rnd) {
            case 0 -> Color.BLUE;
            case 1 -> Color.RED;
            case 2 -> Color.GREEN;
            case 3 -> Color.YELLOW;
            default -> Color.BLACK;
        };
    }
}

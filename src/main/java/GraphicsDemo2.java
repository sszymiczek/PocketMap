import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GraphicsDemo2 extends JFrame {
    private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    private static int SQUARE_WIDTH = 20;
    private Random random = new Random();

    public JPanel fillWithSquares(int xPixels, int yPixels, JPanel panel1){
        int maxX = (int) xPixels / SQUARE_WIDTH;
        int maxY = (int) yPixels / SQUARE_WIDTH;

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                //paintAtCoordinates(i, j, randomColor());
                panel1.add(paintButton(randomColor(), i, j));
            }
        }
        return panel1;
    }
    public JButton paintButton(Color color, int x, int y){
        JButton button = new JButton();
        button.setBackground(color);
        button.setOpaque(true);
        button.setLocation(SQUARE_WIDTH * x, SQUARE_WIDTH * y);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi");
            }
        });
        button.setPreferredSize(new Dimension(SQUARE_WIDTH, SQUARE_WIDTH));
        button.setBorder(null);

        return button;
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

    public GraphicsDemo2(){
        this.setSize(500, 500);
        this.setVisible(true);
        random.setSeed(2137);
        panel1 = fillWithSquares(2000, 2000, panel1);
        this.add(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GraphicsDemo2 test = new GraphicsDemo2();
    }
}

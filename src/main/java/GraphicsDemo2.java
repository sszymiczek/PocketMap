import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;

public class GraphicsDemo2 extends JFrame {
    private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    private static int SQUARE_WIDTH = 50;

    private Random random = new Random();
    private HashMap<String, ImageIcon> mapsOfIcons = new HashMap<>();

    public GraphicsDemo2() {
        loadIcons();
        this.setVisible(true);
        jPanel = fillWithSquares(2000, 2000, jPanel);
        this.add(jPanel);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel fillWithSquares(int xPixels, int yPixels, JPanel panel1) {
        int maxX = (int) xPixels / SQUARE_WIDTH;
        int maxY = (int) yPixels / SQUARE_WIDTH;

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                panel1.add(paintedButton(randomIcon(), i, j));
            }
        }
        return panel1;
    }

    public JButton paintedButton(Icon icon, int x, int y) {
        JButton button = new JButton();
        button.setIcon(icon);
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
        button.setVisible(true);

        return button;
    }

    public ImageIcon randomIcon() {
        int rnd = random.nextInt(4);

        switch (rnd) {
            case 0 -> {
                return this.mapsOfIcons.get("green");
            }
            case 1 -> {
                return this.mapsOfIcons.get("red");
            }
            case 2 -> {
                return this.mapsOfIcons.get("blue");
            }
            case 3 -> {
                return this.mapsOfIcons.get("yellow");
            }
            default -> {
                return this.mapsOfIcons.get("black");
            }
        }
    }

    public void loadIcons() {
        this.mapsOfIcons.clear();

        ImageIcon green = new ImageIcon("src/main/resources/green.png");
        ImageIcon red = new ImageIcon("src/main/resources/red.png");
        ImageIcon blue = new ImageIcon("src/main/resources/blue.png");
        ImageIcon yellow = new ImageIcon("src/main/resources/yellow.png");
        ImageIcon black = new ImageIcon("src/main/resources/black.png");

        this.mapsOfIcons.put("green", green);
        this.mapsOfIcons.put("red", red);
        this.mapsOfIcons.put("blue", blue);
        this.mapsOfIcons.put("yellow", yellow);
        this.mapsOfIcons.put("black", black);
    }
}

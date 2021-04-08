import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class GraphicsDemo extends JFrame {
//    private JPanel jPanelMap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
//    private JPanel jPanelArrows = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

    private JPanel jPanelMap;
    private JPanel jPanelArrows;

    private static int SQUARE_WIDTH = 50;
    private static int ARROW_SIZE = 75;

    private Random random = new Random();
    private HashMap<String, ImageIcon> mapsOfIcons = new HashMap<>();

    public GraphicsDemo() {
        loadIcons();

        //jPanelMap = mainMapCreate();
        jPanelArrows = arrowPanelCreate();

        this.setVisible(true);
        //this.add(jPanelMap);
        this.add(jPanelArrows, BorderLayout.CENTER);
        //this.setSize(500, 500);
        Insets insets = this.getInsets();
        this.setSize(300 + insets.left + insets.right, 300 + insets.top + insets.bottom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jPanelMap.setLayout(null);
//        jPanelMap = fillWithSquares(2000, 2000, jPanelMap);
//        this.setVisible(true);
//        this.add(jPanelMap);
//        this.setSize(500, 500);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Insets insets = this.getInsets();
//        this.setSize(300 + insets.left + insets.right, 125 + insets.top + insets.bottom);

//        this.setVisible(true);
//        jPanelArrows.setVisible(true);
//        jPanelArrows.setLayout(null);
//
//        jPanelArrows = arrowsToNavigate(jPanelArrows);
//        this.setResizable(false);
//        this.add(jPanelArrows);
//
//        this.setSize(500, 500);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        Insets insets = this.getInsets();
//        this.setSize(3 * ARROW_SIZE + insets.left + insets.right, 3 * ARROW_SIZE + insets.top + insets.bottom);

    }

    public JPanel mainMapCreate(){
        //jPanelMap = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        //jPanelMap.setLayout(null);
        JPanel jPanelMap = new JPanel(){
            @Override
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        jPanelMap.setLayout(new OverlayLayout(jPanelMap));
        jPanelMap = fillWithSquares(2000, 2000, jPanelMap);
        jPanelArrows = arrowPanelCreate();
//        jPanelArrows.setAlignmentX(0.1f);
//        jPanelArrows.setAlignmentY(0.1f);
        jPanelMap.add(jPanelArrows);

        return jPanelMap;
    }

    public JPanel arrowPanelCreate(){
        jPanelArrows = new JPanel();
        jPanelArrows.setLayout(null);
        jPanelArrows.setOpaque(false);

        jPanelArrows.setVisible(true);
        //jPanelArrows.setMaximumSize(new Dimension(3 * ARROW_SIZE, 3 * ARROW_SIZE));
        jPanelArrows = arrowsToNavigate(jPanelArrows);
        //jPanelArrows.setLayout(null);
        //jPanelArrows.setLayout(new OverlayLayout(jPanelArrows));
        //jPanelArrows.add(jPanelMap);
        //this.setResizable(false);

        return jPanelArrows;
    }

    public JPanel arrowsToNavigate(JPanel panel) {
        panel.setBackground(null);
        //panel.setVisible(true);
        panel.setSize(new Dimension(100, 100));
        Insets insets = panel.getInsets();

        for (int i = 0; i < 4; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(ARROW_SIZE, ARROW_SIZE));
            button.setIcon(chooseIconToNavigate(i));
            button = positionOfArrows(button, insets, i);
            button.setVisible(true);
            panel.add(button);
        }

        return panel;
    }

    public ImageIcon chooseIconToNavigate(int i){
        ImageIcon iconReturned = null;
        switch (i){
            case 0 -> iconReturned = new ImageIcon("src/main/resources/iconsArrows/up.png");
            case 1 -> iconReturned = new ImageIcon("src/main/resources/iconsArrows/left.png");
            case 2 -> iconReturned = new ImageIcon("src/main/resources/iconsArrows/right.png");
            case 3 -> iconReturned = new ImageIcon("src/main/resources/iconsArrows/down.png");
        }
        return iconReturned;
    }

    public JButton positionOfArrows(JButton button, Insets insets, int i){
        Dimension size = button.getPreferredSize();
        switch (i){
            case 0 -> button.setBounds(ARROW_SIZE + insets.left, insets.top, size.width, size.height);
            case 1 -> button.setBounds( insets.left, ARROW_SIZE + insets.top, size.width, size.height);
            case 2 -> button.setBounds( 2 * ARROW_SIZE + insets.left, ARROW_SIZE + insets.top, size.width, size.height);
            case 3 -> button.setBounds( ARROW_SIZE + insets.left, 2 * ARROW_SIZE + insets.top, size.width, size.height);
        }
        return button;
    }

    public JPanel fillWithSquares(int xPixels, int yPixels, JPanel panel) {
        int maxX = (int) xPixels / SQUARE_WIDTH;
        int maxY = (int) yPixels / SQUARE_WIDTH;

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                JButton button = paintedButton(randomIcon(), i, j);
                panel.add(button);
                Insets insets = panel.getInsets();
                Dimension size = button.getPreferredSize();
                button.setBounds(i * SQUARE_WIDTH + insets.left, j * SQUARE_WIDTH + insets.top,
                        size.width, size.height);
            }
        }
        return panel;
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

        ImageIcon green = new ImageIcon("src/main/resources/iconsMap/green.png");
        ImageIcon red = new ImageIcon("src/main/resources/iconsMap/red.png");
        ImageIcon blue = new ImageIcon("src/main/resources/iconsMap/blue.png");
        ImageIcon yellow = new ImageIcon("src/main/resources/iconsMap/yellow.png");
        ImageIcon black = new ImageIcon("src/main/resources/iconsMap/black.png");

        this.mapsOfIcons.put("green", green);
        this.mapsOfIcons.put("red", red);
        this.mapsOfIcons.put("blue", blue);
        this.mapsOfIcons.put("yellow", yellow);
        this.mapsOfIcons.put("black", black);
    }
}

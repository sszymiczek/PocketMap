import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MapPanel extends JFrame implements Movable{

    private JPanel jPanelMap;
    private JPanel jPanelArrows;
    private HashMap<String, ImageIcon> mapsOfIcons = new HashMap<>();
    private MyRandom random = new MyRandom();
    private ArrowPanel arrowPanel = new ArrowPanel();

    private final int SQUARE_WIDTH = 100;
    //private final int ARROW_SIZE = 75;
    private final int FRAME_SIZE = 500;
    private final int MAP_SIZE_X = 600;
    private final int MAP_SIZE_Y = 600;

    private int startingCoordinateX = 0;
    private int startingCoordinateY = 0;

    public JPanel mainMapCreate(){
        preparePanel();
        generateMap();
        return jPanelMap;
    }

    private void preparePanel() {
        jPanelMap = new JPanel();
        jPanelMap.setLayout(null);
        jPanelMap.setBackground(null);
        jPanelMap.setVisible(true);
    }

    public void generateMap() {
        for (int i = 0; i <= getMaxCoordinateX(); i++) {
            for (int j = getMaxCoordinateY(); j >= 0; j--) {
                generateNewTileAtCoordinates(i, j);
            }
        }
    }

    private int getMaxCoordinateX() {
        return (MAP_SIZE_X / SQUARE_WIDTH) - 1;
    }

    private int getMaxCoordinateY() {
        return (MAP_SIZE_Y / SQUARE_WIDTH) - 1;
    }

    private void generateNewTileAtCoordinates(int x, int y) {
        JButton tile = new JButton();

        tile.setIcon(getRandomIcon(x, y));

        //some jButton casual stuff
        tile.setOpaque(true);
        tile.setLocation(getPixelOfCoordinateX(x), getPixelOfCoordinateY(y));
        tile.setPreferredSize(new Dimension(SQUARE_WIDTH, SQUARE_WIDTH));
        tile.setBorder(null);
        tile.setVisible(true);
        Insets insets = jPanelMap.getInsets();
        Dimension buttonSize = tile.getPreferredSize();
        tile.setBounds(getPixelOfCoordinateX(x) + insets.left, getPixelOfCoordinateY(y) + insets.top, buttonSize.width, buttonSize.height);
        //
        tile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(tile);
                System.out.println(startingCoordinateX + " " + startingCoordinateY);
            }
        });

        jPanelMap.add(tile);
    }

    private int getPixelOfCoordinateY(int y) {
        return MAP_SIZE_Y - (y + 1) * SQUARE_WIDTH;
    }

    private int getPixelOfCoordinateX(int x) {
        return x * SQUARE_WIDTH;
    }

    public ImageIcon getRandomIcon(int x, int y) {
        random.setNLehmerSeed(13*(startingCoordinateX + x) + 19*(startingCoordinateY + y));
        int rnd = random.nextLehmer32(4);

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

    public void moveUp() {
        startingCoordinateY++;
        for (int i = 0; i < jPanelMap.getComponents().length; i++) {
            JButton component = (JButton) jPanelMap.getComponent(i);
            component.setLocation(component.getX(), component.getY() + SQUARE_WIDTH);
        }
        for (int i = 0; i <= getMaxCoordinateX(); i++) {
            generateNewTileAtCoordinates(i, getMaxCoordinateY());
        }
        removeRedundantTiles();
    }

    public void moveDown() {
        startingCoordinateY--;
        for (int i = 0; i < jPanelMap.getComponents().length; i++) {
            JButton component = (JButton) jPanelMap.getComponent(i);
            component.setLocation(component.getX(), component.getY() - SQUARE_WIDTH);
        }
        for (int i = 0; i <= getMaxCoordinateX(); i++) {
            generateNewTileAtCoordinates(i, 0);
        }
        removeRedundantTiles();
        System.out.println(jPanelMap.getComponentCount());
    }

    public void moveRight() {
        startingCoordinateX++;
        for (int i = 0; i < jPanelMap.getComponents().length; i++) {
            JButton component = (JButton) jPanelMap.getComponent(i);
            component.setLocation(component.getX() - SQUARE_WIDTH, component.getY());
        }
        for (int i = 0; i <= getMaxCoordinateY(); i++) {
            generateNewTileAtCoordinates(getMaxCoordinateX(), i);
        }
        removeRedundantTiles();

    }

    public void moveLeft() {
        startingCoordinateX--;
        for (int i = 0; i < jPanelMap.getComponents().length; i++) {
            JButton component = (JButton) jPanelMap.getComponent(i);
            component.setLocation(component.getX() + SQUARE_WIDTH, component.getY());
        }
        for (int i = 0; i <= getMaxCoordinateY(); i++) {
            generateNewTileAtCoordinates(0, i);
        }
        removeRedundantTiles();

    }

    public void removeRedundantTiles(){
        ArrayList<Component> tmp = new ArrayList<>();
        for (int i = 0; i < jPanelMap.getComponents().length; i++) {
            JButton component = (JButton) jPanelMap.getComponent(i);

            if (component.getY() > MAP_SIZE_Y - SQUARE_WIDTH || component.getY() < 0 || component.getX() < 0 || component.getX() > MAP_SIZE_X - SQUARE_WIDTH) {
                tmp.add(component);
            }
        }

        for (int i = 0; i < tmp.size(); i++) {
            jPanelMap.remove(tmp.get(i));
        }

    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MapPanel extends JFrame implements Movable, Coordinates{

    private JPanel jPanelMap;
    private HashMap<String, ImageIcon> mapsOfIcons = new HashMap<>();
    private MyRandom random = new MyRandom();
    private TerrainManager terrainManager = new TerrainManager(mapsOfIcons);

    private final int SQUARE_WIDTH = 25;
    private final int MAP_SIZE_X = 1300;
    private final int MAP_SIZE_Y = 1300;

    private int realX = 0;
    private int realY = 0;

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
        jPanelMap.removeAll();
        for (int i = 0; i <= getMaxCoordinateX(); i++) {
            for (int j = getMaxCoordinateY(); j >= 0; j--) {
                generateNewTileAtCoordinates(i, j);
            }
        }
    }

    public void generateMap(int x, int y){
        jPanelMap.removeAll();
        setRealX(x);
        setRealY(y);
        generateMap();
        jPanelMap.repaint();
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

//    private ImageIcon getRandomIcon(int x, int y) {
//        int seed = 13*(realX + x) + 19*(realY + y);
//        long rnd = random.nextLehmer64(seed)%100;
//        rnd++;
//
//        if (rnd <= 25){
//            return this.mapsOfIcons.get("green");
//        } else if (rnd <= 50){
//            return this.mapsOfIcons.get("blue");
//        } else if (rnd <= 75) {
//            return this.mapsOfIcons.get("yellow");
//        } else {
//            return this.mapsOfIcons.get("red");
//        }
//    }
    private ImageIcon getRandomIcon(int x, int y){ //FUTURE ALTERNATIVE
        return terrainManager.getRandomIcon(x + realX, y + realY);
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

        terrainManager.setMapsOfIcons(mapsOfIcons);
    }

    public void moveUp() {
        realY++;
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
        realY--;
        for (int i = 0; i < jPanelMap.getComponents().length; i++) {
            JButton component = (JButton) jPanelMap.getComponent(i);
            component.setLocation(component.getX(), component.getY() - SQUARE_WIDTH);
        }
        for (int i = 0; i <= getMaxCoordinateX(); i++) {
            generateNewTileAtCoordinates(i, 0);
        }
        removeRedundantTiles();
    }

    public void moveRight() {
        realX++;
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
        realX--;
        for (int i = 0; i < jPanelMap.getComponents().length; i++) {
            JButton component = (JButton) jPanelMap.getComponent(i);
            component.setLocation(component.getX() + SQUARE_WIDTH, component.getY());
        }
        for (int i = 0; i <= getMaxCoordinateY(); i++) {
            generateNewTileAtCoordinates(0, i);
        }
        removeRedundantTiles();
    }

    private void removeRedundantTiles(){
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

    private void jPanelMapRemoveAll(){
        for (int i = 0; i < jPanelMap.getComponents().length; i++) {
            jPanelMap.remove(0);
        }
    }

    public int getRealX() {
        return realX;
    }

    public int getRealY() {
        return realY;
    }

    public void setRealX(int realX) {
        this.realX = realX;
    }

    public void setRealY(int realY) {
        this.realY = realY;
    }
}

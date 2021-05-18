import javax.swing.*;
import java.util.HashMap;

public abstract class SpritesLoader {
    private static HashMap<String, ImageIcon> mapSprites = new HashMap<>();
    private static HashMap<String, ImageIcon> arrowSprites = new HashMap<>();


    public static void loadAll(){
        loadArrowSprites();
        loadMapSprites();
    }

    public static void loadMapSprites() {
        mapSprites.clear();

        mapSprites.put("green",
                new ImageIcon("src/main/resources/iconsMap/green.png"));
        mapSprites.put("lightGreen",
                new ImageIcon("src/main/resources/iconsMap/lightGreen.png"));
        mapSprites.put("darkGreen",
                new ImageIcon("src/main/resources/iconsMap/darkGreen.png"));

        mapSprites.put("red",
                new ImageIcon("src/main/resources/iconsMap/red.png"));

        mapSprites.put("blue",
                new ImageIcon("src/main/resources/iconsMap/blue.png"));
        mapSprites.put("lightBlue",
                new ImageIcon("src/main/resources/iconsMap/lightBlue.png"));
        mapSprites.put("darkBlue",
                new ImageIcon("src/main/resources/iconsMap/darkBlue.png"));

        mapSprites.put("yellow",
                new ImageIcon("src/main/resources/iconsMap/yellow.png"));
        mapSprites.put("black",
                new ImageIcon("src/main/resources/iconsMap/black.png"));

    }

    public static void loadArrowSprites() {
        ImageIcon up = new ImageIcon("src/main/resources/iconsArrows/up.png");
        ImageIcon down = new ImageIcon("src/main/resources/iconsArrows/down.png");
        ImageIcon left = new ImageIcon("src/main/resources/iconsArrows/left.png");
        ImageIcon right = new ImageIcon("src/main/resources/iconsArrows/right.png");

        arrowSprites.put("up", up);
        arrowSprites.put("down", down);
        arrowSprites.put("left", left);
        arrowSprites.put("right", right);
    }

    public static HashMap<String, ImageIcon> getMapSprites(){
        return mapSprites;
    }

    public static HashMap<String, ImageIcon> getArrowSprites() {
        return arrowSprites;
    }
}

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

        ImageIcon green = new ImageIcon("src/main/resources/iconsMap/green.png");
        ImageIcon red = new ImageIcon("src/main/resources/iconsMap/red.png");
        ImageIcon blue = new ImageIcon("src/main/resources/iconsMap/blue.png");
        ImageIcon yellow = new ImageIcon("src/main/resources/iconsMap/yellow.png");
        ImageIcon black = new ImageIcon("src/main/resources/iconsMap/black.png");

        mapSprites.put("green", green);
        mapSprites.put("red", red);
        mapSprites.put("blue", blue);
        mapSprites.put("yellow", yellow);
        mapSprites.put("black", black);
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

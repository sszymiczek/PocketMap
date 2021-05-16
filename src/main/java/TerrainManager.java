import javax.swing.*;
import java.util.HashMap;

public class TerrainManager {
    private MyRandom random = new MyRandom();
    private HashMap<String, ImageIcon> mapsOfIcons = new HashMap<>();
    private int x;
    private int y;

    public TerrainManager(HashMap<String, ImageIcon> mapsOfIcons) {
        this.mapsOfIcons = mapsOfIcons;
    }

    public ImageIcon getRandomIcon(int x, int y){
        int xPercentage = x%10;
        int yPercentage = y%10;
        this.x = x;
        this.y = y;
        int percent = (int) (random.nextLehmer64()%100);
        if (xPercentage <= 5 && yPercentage <= 5)
            if (percent <= 80) return getForest();
            else if (percent <= 95) return getSea();
            else return getVolcano();
        else if(xPercentage <= 7 && yPercentage <= 7)
            return getVolcano();
        else if(xPercentage <= 8 && yPercentage <= 8)
            return getSea();
        else
            return getHole();

    }

    private ImageIcon getForest(){
        return mapsOfIcons.get("green");
    }

    private ImageIcon getVolcano(){
        return mapsOfIcons.get("red");
    }

    private ImageIcon getSea(){
        return mapsOfIcons.get("blue");
    }

    private ImageIcon getHole(){
        return mapsOfIcons.get("black");
    }

    public void setMapsOfIcons(HashMap<String, ImageIcon> mapsOfIcons) {
        this.mapsOfIcons = mapsOfIcons;
    }
}


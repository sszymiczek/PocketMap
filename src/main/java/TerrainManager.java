import javax.swing.*;
import java.util.HashMap;

public class TerrainManager {
    private MyRandom random = new MyRandom();
    int x; int y;
    private int seed;
    private int bigSquareWidth = 10;
    long rnd;

    public ImageIcon getRandomIcon(int x, int y){
        this.x = x; this.y = y;
        this.seed = 13 * x + 19 * + y;

        int coarseX = x - x%bigSquareWidth; int coarseY = y - y%bigSquareWidth;
        long CoarseRnd = random.nextLehmer64(19 * coarseX + 5 * coarseY)%100;
        long rnd = random.nextLehmer64(13 * x + 19 * y);

        if (CoarseRnd <= 75){
            return getForest();
        } else if (CoarseRnd <= 95){
            return getSea();
        } else {
            return getVolcano();
        }
    }

    private ImageIcon getForest(){
        long rnd = random.nextLehmer64(seed)%100;
        if (rnd <= 80) {
            return SpritesLoader.getMapSprites().get("green");
        }else if (rnd <= 95){
            return SpritesLoader.getMapSprites().get("yellow");
        }else{
            return SpritesLoader.getMapSprites().get("blue");
        }
    }

    private ImageIcon getVolcano(){
//        if (x%bigSquareWidth >= 0.1*bigSquareWidth
//        && x%bigSquareWidth <= 0.9*bigSquareWidth){
            if (rnd <= 20){
                return SpritesLoader.getMapSprites().get("red");
            } else {
                return SpritesLoader.getMapSprites().get("black");
            }
//        } else {
//            if (rnd <= 95){
//                return mapsOfIcons.get("black");
//            } else {
//                return mapsOfIcons.get("red");
//            }
//        }
    }

    private ImageIcon getSea() {
        if (rnd <= 98) {
            return SpritesLoader.getMapSprites().get("blue");
        } else {
            return SpritesLoader.getMapSprites().get("yellow");
        }
    }

    private ImageIcon getHole() {
        if (rnd <= 50) {
            return SpritesLoader.getMapSprites().get("black");
        } else {
            return SpritesLoader.getMapSprites().get("yellow");
        }
    }
}


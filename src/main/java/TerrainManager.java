import javax.swing.*;

public class TerrainManager {
    private MyRandom random = new MyRandom();
    int x; int y;
    private int seed;
    private int bigSquareWidth = 10;
    private long rnd;

    public ImageIcon getRandomIcon(int x, int y){
        this.x = x; this.y = y;
        this.seed = 13 * x + 19 * + y;
        rnd = random.nextLehmer64(seed)%100;

        int coarseX = x - x%bigSquareWidth; int coarseY = y - y%bigSquareWidth;
        long coarseRnd = random.nextLehmer64(19 * coarseX + 5 * coarseY)%100;

        if (coarseRnd <= 75){
            return getForest();
        } else if (coarseRnd <= 95){
            return getSea();
        } else {
            return getVolcano();
        }
    }

    private ImageIcon getForest(){
        if (rnd <= 27) {
            return SpritesLoader.getMapSprites().get("green");
        }else if (rnd <= 54) {
            return SpritesLoader.getMapSprites().get("darkGreen");
        }else if (rnd <= 81) {
            return SpritesLoader.getMapSprites().get("lightGreen");
        }else if (rnd <= 95){
            return SpritesLoader.getMapSprites().get("yellow");
        }else{
            return SpritesLoader.getMapSprites().get("blue");
        }
    }

    private ImageIcon getVolcano(){
//        if (x%bigSquareWidth >= 0.1*bigSquareWidth
//        && x%bigSquareWidth <= 0.9*bigSquareWidth){
        if (rnd <= 80){
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
        if (rnd <= 27) {
            return SpritesLoader.getMapSprites().get("blue");
        }else if(rnd <= 40) {
            return SpritesLoader.getMapSprites().get("lightBlue");
        }else if(rnd <= 97) {
            return SpritesLoader.getMapSprites().get("darkBlue");
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


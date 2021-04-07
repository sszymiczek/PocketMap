import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;

public class GraphicsDemo2 extends JFrame {
    private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    private static int SQUARE_WIDTH = 50;
    private Random random = new Random();

    public JPanel fillWithSquares(int xPixels, int yPixels, JPanel panel1){
        int maxX = (int) xPixels / SQUARE_WIDTH;
        int maxY = (int) yPixels / SQUARE_WIDTH;

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                panel1.add(paintButton(randomIcon(), i, j));
            }
        }
        return panel1;
    }
    public JButton paintButton(Icon icon, int x, int y){
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
        System.out.println(button);

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

    public ImageIcon randomIcon(){
        int rnd = random.nextInt(4);
        String iconName = null;
        switch (rnd) {
            case 0 -> iconName = "0.png";
            case 1 -> iconName = "1.png";
            case 2 -> iconName = "2.png";
            case 3 -> iconName = "4.png";
        }
        Path chosenPath = Paths.get(System.getProperty("user.home"),"icons", iconName);
        return new ImageIcon(String.valueOf(chosenPath));

    }

    public HashMap<String, ImageIcon> loadIcon(){
        HashMap<String, ImageIcon> mapsOfIcons= new HashMap<>();

        ImageIcon green = new ImageIcon(String.valueOf(Paths.get(System.getProperty("user.home"),"icons", "0.png")));
        ImageIcon red = new ImageIcon(String.valueOf(Paths.get(System.getProperty("user.home"),"icons", "1.png")));
        ImageIcon blue = new ImageIcon(String.valueOf(Paths.get(System.getProperty("user.home"),"icons", "2.png")));
        ImageIcon yellow = new ImageIcon(String.valueOf(Paths.get(System.getProperty("user.home"),"icons", "4.png")));


        mapsOfIcons.put("green", green);
        mapsOfIcons.put("red", red);
        mapsOfIcons.put("blue", blue);
        mapsOfIcons.put("yellow", yellow);

        return mapsOfIcons;
    }

    public GraphicsDemo2(){
        this.setVisible(true);
        panel1 = fillWithSquares(2000, 2000, panel1);
        this.add(panel1);
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        GraphicsDemo2 test = new GraphicsDemo2();
    }
}

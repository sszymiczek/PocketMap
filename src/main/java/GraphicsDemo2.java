import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
                //paintAtCoordinates(i, j, randomColor());
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

    public JPanel oneButtonTest(Icon icon, JPanel panel){
//        JButton button = new JButton();
//        button.setIcon(icon);
//        button.setOpaque(true);
//        button.setPreferredSize(new Dimension(50, 50));
//        button.setBorder(null);
//        button.setVisible(true);
//        panel.add(button);

//        System.out.println(button);
        //panel.add(new JLabel(icon));


        System.out.println(icon);
        System.out.println(new JLabel(icon));
        return panel;
    }

    public ImageIcon randomIcon(){
        int rnd = random.nextInt(4);
        String  iconName= null;
        switch (rnd) {
            case 0 -> iconName = "resources/icons/0.png";
            case 1 -> iconName = "resources/icons/1.png";
            case 2 -> iconName = "resources/icons/2.png";
            case 3 -> iconName = "resources/icons/3.png";
        }
        return createImageIcon(iconName, "test");

    }
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public GraphicsDemo2(){
        this.setSize(500, 500);
        this.setVisible(true);
        random.setSeed(2137);
        //panel1 = fillWithSquares(2000, 2000, panel1);
        panel1 = oneButtonTest(randomIcon(), panel1);
        this.add(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GraphicsDemo2 test = new GraphicsDemo2();
    }
}

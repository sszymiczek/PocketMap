import javax.swing.*;
import java.awt.*;

public class ArrowPanel{
    //private JPanel jPanelArrows;
    public final int ARROW_SIZE = 75;

//    public static void main(String[] args) {
//        ArrowPanel arrowPanel = new ArrowPanel();
//        JPanel panel = arrowPanel.createArrowPanel();
//        JFrame frame = new JFrame();
//        frame.add(panel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(new Dimension(225, 250));
//        frame.setVisible(true);
//    }

    public JPanel createArrowPanel() {
        JPanel jPanelArrows = new JPanel();
        jPanelArrows.setBackground(null);
        jPanelArrows.setVisible(true);
        jPanelArrows.setSize(new Dimension(225, 225));
        jPanelArrows.setLayout(null);
        jPanelArrows.setMaximumSize(new Dimension(3 * ARROW_SIZE, 3 * ARROW_SIZE));
        jPanelArrows.setMinimumSize(new Dimension(3 * ARROW_SIZE, 3 * ARROW_SIZE));
        jPanelArrows = arrowsToNavigate(jPanelArrows);

//        jPanelArrows.setAlignmentX(0.5f);
//        jPanelArrows.setAlignmentX(0.8f);
        return jPanelArrows;
    }

    public JPanel arrowsToNavigate(JPanel panel) {
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
}

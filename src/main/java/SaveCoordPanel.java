import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SaveCoordPanel implements Coordinates{
    public final int BUTTON_SIZE = 30;
    private List<int[]> savedLocation = new ArrayList();
    private JButton saveButton;
    private JComboBox<Point> savedLocCombobox;
    private int xToSave = 5;
    private int yToSave = 7;
    public Point pt;


    public JPanel createCoordPanel() {
        JPanel jPanelCoord = new JPanel();
        jPanelCoord.setBackground(null);
        jPanelCoord.setVisible(true);
        jPanelCoord.setSize(new Dimension(225, 3 * BUTTON_SIZE + 20));
        jPanelCoord.setLayout(null);
        jPanelCoord = getSaveButton(jPanelCoord);
        jPanelCoord = getSavedLocCombo(jPanelCoord);
        jPanelCoord = getGoToButton(jPanelCoord);

        return jPanelCoord;
    }

    public JPanel getSaveButton(JPanel panel){
        Insets insets = panel.getInsets();
        saveButton = new JButton("SAVE LOCATION");
        saveButton.setVisible(true);
        saveButton.setPreferredSize(new Dimension(225, BUTTON_SIZE));
        Dimension size = saveButton.getPreferredSize();
        saveButton.setBounds(insets.left, insets.top, size.width, size.height);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCurrentCoordinates();
            }
        });
        panel.add(saveButton);
        return panel;
    }

    public JPanel getSavedLocCombo(JPanel panel){
        savedLocCombobox = new JComboBox<>();
        Insets insets = panel.getInsets();
        savedLocCombobox.setVisible(true);
        savedLocCombobox.setPreferredSize(new Dimension(215, BUTTON_SIZE));
        Dimension size = savedLocCombobox.getPreferredSize();
        savedLocCombobox.setBounds(insets.left, BUTTON_SIZE + 5 + insets.top, size.width, size.height);
        panel.add(savedLocCombobox);
        //savedLocCombobox.addItem(new Point(2,45));
        return panel;
    }

    public JPanel getGoToButton(JPanel panel){
        Insets insets = panel.getInsets();
        saveButton = new JButton("GO TO LOCATION");
        saveButton.setVisible(true);
        saveButton.setPreferredSize(new Dimension(225, BUTTON_SIZE));
        Dimension size = saveButton.getPreferredSize();
        saveButton.setBounds(insets.left, 2 * BUTTON_SIZE + 10 + insets.top, size.width, size.height);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point tmp = (Point) savedLocCombobox.getSelectedItem();
                generateMap((int)tmp.getX(), (int)tmp.getY());
            }
        });
        panel.add(saveButton);
        return panel;
    }

    public void saveCurrentCoordinates(){
        Point point = pointString(xToSave, yToSave);
        savedLocCombobox.addItem(point);
    }

    public Point pointString(int x, int y){
        pt = new Point(x, y){
            @Override
            public String toString(){
                return "x: " + pt.x + ", " + "y: " + pt.y;
            }
        };
        return pt;
    }
}

package aplikacja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SaveCoordinatesPanel extends Thread{
    public final int BUTTON_SIZE = 30;
    private JButton saveButton;
    private JComboBox<Point> savedLocCombobox;
    private int xToSave;
    private int yToSave;
    private Coordinates map;
    JTextField cordX;
    JTextField cordY;
    JLabel locLabel;

    public JPanel createCoordinatesPanel() {
        JPanel jPanelCoordinates = new JPanel();
        jPanelCoordinates.setBackground(null);
        jPanelCoordinates.setVisible(true);
        jPanelCoordinates.setSize(new Dimension(225, 3 * BUTTON_SIZE + 20));
        jPanelCoordinates.setLayout(null);
        jPanelCoordinates = getLabelCurrentLoc(jPanelCoordinates);
        jPanelCoordinates = getSaveCoordinatesButton(jPanelCoordinates);
        jPanelCoordinates = getSavedCoordinatesCombobox(jPanelCoordinates);
        jPanelCoordinates = getGoToButton(jPanelCoordinates);
        jPanelCoordinates = getTextField(jPanelCoordinates);

        start();

        return jPanelCoordinates;
    }
    public JPanel getLabelCurrentLoc(JPanel panel){
        locLabel = new JLabel("x: " + xToSave + ", y: " + yToSave);
        Insets insets = panel.getInsets();
        locLabel.setVisible(true);
        locLabel.setPreferredSize(new Dimension(225, BUTTON_SIZE));
        Dimension size = locLabel.getPreferredSize();
        locLabel.setBounds(insets.left, insets.top, size.width, size.height);
        panel.add(locLabel);
        return panel;
    }

    public JPanel getSaveCoordinatesButton(JPanel panel){
        saveButton = new JButton("SAVE LOCATION");
        Insets insets = panel.getInsets();
        saveButton.setVisible(true);
        saveButton.setPreferredSize(new Dimension(225, BUTTON_SIZE));
        Dimension size = saveButton.getPreferredSize();
        saveButton.setBounds(insets.left, BUTTON_SIZE + 5 + insets.top, size.width, size.height);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCurrentCoordinates();
            }
        });
        panel.add(saveButton);
        return panel;
    }

    public JPanel getSavedCoordinatesCombobox(JPanel panel){
        savedLocCombobox = new JComboBox<>();
        Insets insets = panel.getInsets();
        savedLocCombobox.setVisible(true);
        savedLocCombobox.setPreferredSize(new Dimension(215, BUTTON_SIZE));
        Dimension size = savedLocCombobox.getPreferredSize();
        savedLocCombobox.setBounds(insets.left, 2*(BUTTON_SIZE + 5)+ insets.top, size.width, size.height);
        savedLocCombobox.insertItemAt(null, 0);
        panel.add(savedLocCombobox);

        return panel;
    }

    public JPanel getGoToButton(JPanel panel){
        saveButton = new JButton("GO TO LOCATION");
        Insets insets = panel.getInsets();
        saveButton.setVisible(true);
        saveButton.setPreferredSize(new Dimension(225, BUTTON_SIZE));
        Dimension size = saveButton.getPreferredSize();
        saveButton.setBounds(insets.left, 3 * (BUTTON_SIZE + 5) + insets.top, size.width, size.height);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Point tmp = (Point) savedLocCombobox.getSelectedItem();
                Point tmp = prepareCoordinatesToGo();
                if (tmp != null) {
                    System.out.println("going to x: " + tmp.getX() + ", y: " + tmp.getY());
                    map.generateMap((int) tmp.getX(), (int) tmp.getY());
                }
            }
        });
        panel.add(saveButton);
        return panel;
    }

    public JPanel getTextField(JPanel panel){
        cordX = new JTextField();
        cordX.setText("x");
        Insets insets = panel.getInsets();
        cordX.setVisible(true);
        cordX.setPreferredSize(new Dimension(105, BUTTON_SIZE));
        Dimension size = cordX.getPreferredSize();
        cordX.setBounds(insets.left, 4 * (BUTTON_SIZE + 5) + insets.top, size.width, size.height);
        cordX.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                cordX.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cordX.getText().equals(""))
                    cordX.setText("x");
            }
        });

        panel.add(cordX);

        cordY = new JTextField();
        cordY.setText("y");
        cordY.setVisible(true);
        cordY.setPreferredSize(new Dimension(105, BUTTON_SIZE));
        Dimension sizey = cordY.getPreferredSize();
        cordY.setBounds(insets.left + 110, 4 * (BUTTON_SIZE + 5) + insets.top, sizey.width, sizey.height);
        cordY.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                cordY.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cordY.getText().equals(""))
                    cordY.setText("y");
            }
        });

        panel.add(cordY);

        return panel;
    }

    public Point prepareCoordinatesToGo(){
        if (savedLocCombobox.getSelectedItem() == null && !cordX.getText().equals("x") && !cordY.getText().equals("y")){
            if (isNumeric(cordX.getText()) && isNumeric(cordY.getText()))
                return new Point(Integer.parseInt(cordX.getText()), Integer.parseInt(cordY.getText()));
            else
                JOptionPane.showMessageDialog(null, "inserted values should be ints", "alert", JOptionPane.PLAIN_MESSAGE);
        }

        else if (savedLocCombobox.getSelectedItem() != null && cordX.getText().equals("x") && cordY.getText().equals("y"))
            return (Point) savedLocCombobox.getSelectedItem();

        else {
            if (savedLocCombobox.getSelectedItem() != null && (!cordX.getText().equals("x") || !cordY.getText().equals("y")))
                JOptionPane.showMessageDialog(null, "only one option can be chosen", "alert", JOptionPane.PLAIN_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "insert values first", "alert", JOptionPane.PLAIN_MESSAGE);
        }
        return null;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);

        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void saveCurrentCoordinates(){
        xToSave = map.getRealX();
        yToSave = map.getRealY();
        Point point = pointString(xToSave, yToSave);
        if(((DefaultComboBoxModel)savedLocCombobox.getModel()).getIndexOf(point) == -1) {
            savedLocCombobox.addItem(point);
        }
    }

    public Point pointString(int x, int y){
        Point pt = new Point(x, y){
            @Override
            public String toString(){
                return "x: " + this.getX() + ", " + "y: " + this.getY();
            }
        };
        return pt;
    }

    public void refresh(){
        locLabel.setText("x: " + map.getRealX() + ", y: " + map.getRealY());

    }

    public void run(){
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            refresh();
        }
    }

    public void setMap(Coordinates map) {
        this.map = map;
    }
}

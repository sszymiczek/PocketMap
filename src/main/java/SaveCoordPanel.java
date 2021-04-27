import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveCoordPanel {
    public final int BUTTON_SIZE = 30;

    public JPanel createCoordPanel() {
        JPanel jPanelCoord = new JPanel();
        jPanelCoord.setBackground(null);
        jPanelCoord.setVisible(true);
        jPanelCoord.setSize(new Dimension(225, 2*BUTTON_SIZE));
        jPanelCoord.setLayout(null);
        jPanelCoord = getSaveButton(jPanelCoord);
        jPanelCoord = getSavedLocCombo(jPanelCoord);

        return jPanelCoord;
    }

    public JPanel getSaveButton(JPanel panel){
        Insets insets = panel.getInsets();
        JButton saveButton = new JButton("SAVE LOCATION");
        saveButton.setVisible(true);
        saveButton.setPreferredSize(new Dimension(225,BUTTON_SIZE));
        Dimension size = saveButton.getPreferredSize();
        saveButton.setBounds(insets.left, insets.top, size.width, size.height);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("saved");
            }
        });
        panel.add(saveButton);
        return panel;
    }

    public JPanel getSavedLocCombo(JPanel panel){
        JComboBox<String> savedLocCombobox = new JComboBox<>();
        Insets insets = panel.getInsets();
        savedLocCombobox.setVisible(true);
        savedLocCombobox.setPreferredSize(new Dimension(225, BUTTON_SIZE));
        Dimension size = savedLocCombobox.getPreferredSize();
        savedLocCombobox.setBounds(insets.left, BUTTON_SIZE + 5 + insets.top, size.width, size.height);
        savedLocCombobox.addItem("x: 1, y: 2");
        panel.add(savedLocCombobox);
        return panel;
    }
}

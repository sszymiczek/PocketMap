import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel jPanelMap;
    private JPanel jPanelArrows;
    private JPanel jPanelCoordinates;
    private ArrowPanel arrowPanel = new ArrowPanel();
    private MapPanel mapPanel = new MapPanel();
    private SaveCoordinatesPanel saveCoordinatesPanel = new SaveCoordinatesPanel();

    private final int FRAME_SIZE = 700;
    private int ARROW_SIZE = arrowPanel.ARROW_SIZE;


    public MainFrame() {
        Insets insets = this.getInsets();
        //mapPanel.loadIcons();                 DELETE
        SpritesLoader.loadAll();
        this.setVisible(true);
        JSplitPane splitPane = createSplitPane();
        this.add(splitPane);

        saveCoordinatesPanel.setMap(mapPanel);

        this.setSize(FRAME_SIZE + insets.left + insets.right, FRAME_SIZE + insets.top + insets.bottom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JSplitPane createSplitPane(){
        jPanelArrows = arrowPanel.createArrowPanel(mapPanel);
        jPanelMap = mapPanel.mainMapCreate();
        jPanelCoordinates = saveCoordinatesPanel.createCoordinatesPanel();

        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(null);
        tmpPanel.add(jPanelCoordinates);
        tmpPanel.add(jPanelArrows);
        Insets insets = jPanelArrows.getInsets();
        Dimension size = jPanelArrows.getPreferredSize();
        jPanelArrows.setBounds(insets.left, insets.top + 120, size.width, size.height);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPanelMap, tmpPanel){
            @Override
            public int getDividerLocation(){
                return this.getWidth() - 3 * ARROW_SIZE;
            }
        };

        splitPane.setDividerLocation(FRAME_SIZE - 3 * ARROW_SIZE );
        splitPane.setResizeWeight(1.0);

        return splitPane;
    }

}

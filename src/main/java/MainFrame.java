import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MainFrame extends JFrame {
    private JPanel jPanelMap;
    private JPanel jPanelArrows;
    private HashMap<String, ImageIcon> mapsOfIcons = new HashMap<>();
    private MyRandom random = new MyRandom();
    private ArrowPanel arrowPanel = new ArrowPanel();
    private MapPanel mapPanel = new MapPanel();

    private final int FRAME_SIZE = 500;
    private int ARROW_SIZE = arrowPanel.ARROW_SIZE;


    public MainFrame() {
        Insets insets = this.getInsets();

        mapPanel.loadIcons();
        this.setVisible(true);
        JSplitPane splitPane = createSplitPane();
        this.add(splitPane);

        this.setSize(FRAME_SIZE + insets.left + insets.right, FRAME_SIZE + insets.top + insets.bottom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public JSplitPane createSplitPane(){
        jPanelArrows = arrowPanel.createArrowPanel();
        jPanelMap = mapPanel.mainMapCreate();

        JPanel pusty = new JPanel();
        pusty.setMaximumSize(new Dimension(3 * ARROW_SIZE, 3 * ARROW_SIZE));
        pusty.setMinimumSize(new Dimension(3 * ARROW_SIZE, 3 * ARROW_SIZE));

//        JPanel opakuj = new JPanel();
//        opakuj.setLayout(new BoxLayout(opakuj, BoxLayout.PAGE_AXIS));
//        opakuj.add(pusty);
//        opakuj.add(jPanelArrows, Component.BOTTOM_ALIGNMENT);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jPanelMap, jPanelArrows){
            @Override
            public int getDividerLocation(){
                return this.getWidth() - 3 * ARROW_SIZE;
            }
        };

        splitPane.setDividerLocation(FRAME_SIZE - 3 * ARROW_SIZE );
        splitPane.setResizeWeight(1.0);


        //Provide minimum sizes for the two components in the split pane

        Dimension maximumSize = new Dimension(3 * ARROW_SIZE + 5, FRAME_SIZE);
        jPanelArrows.setMaximumSize(maximumSize);
        return splitPane;
    }

}

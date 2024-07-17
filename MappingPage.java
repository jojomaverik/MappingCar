import java.awt.*;
import javax.swing.*;

public class MappingPage extends Base {

    public MappingPage() {
        // Set up the frame
        setTitle("Mapping");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame to fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        // Create a main panel with BorderLayout
        JPanel mappingPanel = new JPanel(new BorderLayout());

        // Icon changed for the main frame
        setIconImage(GUIicon.getImage());

        // Create a panel to create a visualization of the indoor
        JPanel visualizationPanel = new JPanel();
        visualizationPanel.setPreferredSize(new Dimension(800, 800));
        visualizationPanel.setBackground(Color.black);
        visualizationPanel.add(new JLabel("This is the Mapping Frame"));

        // Center the visualization panel in the mapping panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(visualizationPanel);

        // Add the center panel to the mapping panel
        mappingPanel.add(centerPanel, BorderLayout.CENTER);

        // Add the panel to the frame
        add(mappingPanel);

        // Make the frame visible
        setVisible(true);
    }
}

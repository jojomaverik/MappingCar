import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class MappingPage extends Base {

    VisualizationPanel panel;

    public MappingPage() {
        // Set up the frame
        setTitle("Mapping");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a main panel with BoxLayout
        JPanel mappingPanel = new JPanel();
        mappingPanel.setLayout(new BoxLayout(mappingPanel, BoxLayout.Y_AXIS));
        mappingPanel.setBackground(Color.WHITE);

        // Icon changed for the main frame
        setIconImage(GUIicon.getImage());

        // Create a panel to create a visualization of the indoor
        panel = new VisualizationPanel();
        panel.setPreferredSize(new Dimension(600, 600));
        panel.setMinimumSize(new Dimension(600, 600));
        panel.setBackground(Color.BLACK);
        for (int i = 0; i < 10; i++) {
            panel.addPoint((int) (Math.random() * 600), (int) (Math.random() * 600));            
        }
                
        // Add a border to the visualization panel
        Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 5); 
        panel.setBorder(lineBorder);

        // Create a wrapper panel with GridBagLayout to center the visualization panel
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        wrapperPanel.add(panel, gbc);

        // Add the wrapper panel to the mapping panel
        mappingPanel.add(Box.createVerticalGlue());
        mappingPanel.add(wrapperPanel);
        mappingPanel.add(Box.createVerticalGlue());

        // Add the back button
        JButton backButton = createBackButton();
        mappingPanel.add(backButton);

        // Add the panel to the frame
        add(mappingPanel);

        // Make the frame visible
        setVisible(true);
    }

}

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MainPage extends Base {

    public MainPage() {
        // Set up the frame
        setTitle("Main Page");

        // Create a main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a panel to hold the buttons with BoxLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Icon changed for the main frame
        setIconImage(GUIicon.getImage());

        // Create two buttons with resized icons
        JButton button1 = new JButton("Mapping", mapIcon);
        JButton button2 = new JButton("Operation Status", statusIcon);

        // Customize button appearance
        button1.setFocusPainted(false);
        button1.setBorderPainted(true);
        button2.setFocusPainted(false);
        button2.setBorderPainted(true);

        // Set preferred size for the buttons
        button1.setPreferredSize(new Dimension(300, 60));
        button2.setPreferredSize(new Dimension(300, 60));

        // Set maximum size for the buttons to ensure they don't expand
        button1.setMaximumSize(new Dimension(300, 60));
        button2.setMaximumSize(new Dimension(300, 60));

        // Center align buttons horizontally
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set icon to the right of the text
        button1.setHorizontalTextPosition(SwingConstants.LEFT);
        button2.setHorizontalTextPosition(SwingConstants.LEFT);

        // Set gap between text and icon
        button1.setIconTextGap(135);
        button2.setIconTextGap(80);

        // Add mouse listener to change button color on hover
        addHoverEffect(button1, Color.LIGHT_GRAY, Color.WHITE);
        addHoverEffect(button2, Color.LIGHT_GRAY, Color.WHITE);

        // Add action listeners to handle button clicks
        button1.addActionListener((ActionEvent e) -> {
            new MappingPage();
            setVisible(false);
        });

        button2.addActionListener((ActionEvent e) -> {
            new OperationPage();
            setVisible(false);
            OperationPage.updateStatus("TEST111");
        });

        // Add buttons to the button panel with some spacing
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(button1);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        buttonPanel.add(button2);
        buttonPanel.add(Box.createVerticalGlue());

        // Add button panel to the center of the main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Add main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the GUI code on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            new MainPage();
        });
    }
}

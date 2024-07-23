import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class OperationPage extends Base {

    private static final List<String> statusMessages = new ArrayList<>();
    private static JTextArea statusTextArea;

    public OperationPage() {
        // Set up the frame
        setTitle("Operation Status");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a main panel with BoxLayout
        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new BoxLayout(operationPanel, BoxLayout.Y_AXIS));

        // Icon changed for the main frame
        setIconImage(GUIicon.getImage());

        // Create the text area for status updates
        statusTextArea = new JTextArea(10, 30); // Use the class-level statusTextArea
        statusTextArea.setEditable(false); // Make it read-only
        JScrollPane scrollPane = new JScrollPane(statusTextArea); // Add scroll functionality

        // Add the back button
        JButton backButton = createBackButton();
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to the panel
        operationPanel.add(Box.createRigidArea(new Dimension(0, 200))); // Add some space
        operationPanel.add(scrollPane);
        operationPanel.add(Box.createVerticalGlue());
        operationPanel.add(Box.createVerticalGlue());
        operationPanel.add(backButton);

        // Add the panel to the frame
        add(operationPanel);

        // Load existing messages
        loadStatusMessages();

        // Make the frame visible
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame to full screen
        setVisible(true);
    }
    
    // Method to update the status text area
    public static void updateStatus(String status) {
        statusMessages.add(status);
        if (statusTextArea != null) {
            statusTextArea.append(status + "\n");
        }
    }

    // Method to load existing status messages into the text area
    private void loadStatusMessages() {
        for (String message : statusMessages) {
            statusTextArea.append(message + "\n");
        }
    }
}

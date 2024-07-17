import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainPage extends JFrame {

    public MainPage() {
        // Set up the frame
        setTitle("Main Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame to fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        // Create a main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a panel to hold the buttons with BoxLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Load and resize images
        ImageIcon mapIcon = resizeImageIcon("Img/Map.png", 50, 50);
        ImageIcon statusIcon = resizeImageIcon("Img/Status.png", 50, 50);

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

        // Add action listeners to handle button clicks
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mapping button clicked");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Operation Status button clicked");
            }
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

    // Image resizing method
    private ImageIcon resizeImageIcon(String path, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            int newWidth, newHeight;

            if (originalImage.getWidth() > originalImage.getHeight()) {
                newWidth = width;
                newHeight = (originalImage.getHeight() * newWidth) / originalImage.getWidth();
            } else {
                newHeight = height;
                newWidth = (originalImage.getWidth() * newHeight) / originalImage.getHeight();
            }

            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Run the GUI code on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainPage();
            }
        });
    }
}

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Base extends JFrame {

    // Load and resize images
    ImageIcon mapIcon = resizeImageIcon("Img/Map.png", 50, 50);
    ImageIcon statusIcon = resizeImageIcon("Img/Status.png", 50, 50);
    ImageIcon GUIicon = resizeImageIcon("Img/Car.png", 599, 599);
    
    public Base() {
        // Set up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
    }

    // Back button method
    protected JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(330, 60));
        backButton.setMinimumSize(new Dimension(330, 60));
        backButton.addActionListener((ActionEvent e) -> {
            //noinspection InstantiationOfUtilityClass
            new MainPage();
            setVisible(false);
        });
        return backButton;
    }

    // Background color changer method for buttons
    protected void addHoverEffect(JButton button, Color hoverColor, Color normalColor) {
        button.setBackground(normalColor);
        button.setOpaque(true);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
            }
        });
    }

    // Image resizing method
    protected ImageIcon resizeImageIcon(String path, int width, int height) {
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
            return null;
        }
    }
}

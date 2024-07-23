import com.fazecast.jSerialComm.SerialPort;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class MappingPage extends Base {
    VisualizationPanel panel;
    Timer timer;
    SerialPort comPort;
    BufferedReader input;

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

        // Testing paint
        simulateDataGeneration();

        // Set up serial communication
        setupSerial();
    }

    private void setupSerial() {
        comPort = SerialPort.getCommPorts()[0]; // Select the first available COM port
        comPort.setBaudRate(9600);
        comPort.openPort();
        input = new BufferedReader(new InputStreamReader(comPort.getInputStream()));

        // Start a new thread to read data from the serial port
        new Thread(() -> {
            while (true) {
                try {
                    if (input.ready()) {
                        String line = input.readLine();
                        System.out.println("Received: " + line);
                        if (line.startsWith("Obstacle at: ")) {
                            String[] parts = line.substring(13).split(",");
                            int x = Integer.parseInt(parts[0].trim());
                            int y = Integer.parseInt(parts[1].trim());
                            SwingUtilities.invokeLater(() -> panel.addPoint(x, y));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void simulateDataGeneration() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Generate random data for testing
                int x = (int) (Math.random() * 600);
                int y = (int) (Math.random() * 600);
                panel.addPoint(x, y);
            }
        }, 0, 1000); // Add a point every second
    }

    @Override
    public void dispose() {
        if (timer != null) {
            timer.cancel();
        }
        super.dispose();
    }
}



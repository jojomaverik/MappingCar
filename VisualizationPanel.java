import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class VisualizationPanel extends JPanel {
    private final List<Point> points; 

    public VisualizationPanel() {
        points = new ArrayList<>(); 
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y)); 
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Ensure the parent class's method is called
        Graphics2D g2d = (Graphics2D) g; 
        g2d.setColor(Color.WHITE); 

        for (Point point : points) {
            g2d.fillOval(point.x, point.y, 5, 5); 
        }
    }
}

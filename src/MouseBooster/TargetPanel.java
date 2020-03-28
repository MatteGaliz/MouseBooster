package MouseBooster;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class TargetPanel extends JPanel {
    private Point center;
    private int size;
    private Color color;

    public TargetPanel(Point center, int size, Color color) {
        super();
        this.center = center;
        this.size = size;
        this.color = color;
        setBackground(new Color(26, 26, 26));
    }

    public TargetPanel(){
        super();
        this.center = new Point(0,0);
        setBackground(new Color(26,26,26));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xf = (int) center.getX();
        int yf = (int) center.getY();
        g.setColor(color);
        g.fillOval(xf, yf, size, size);
        g.setColor(Color.BLACK);
        g.drawOval(xf, yf, size, size);
        repaint();
    }

    public void move(Point newCenter) {
        this.center = newCenter;
    }

    public int getTargetSize() {
        return size;
    }

    public void setTargetSize(int m) {
        this.size = m;
    }

    public Color getColor() {
        return color;
    }

    public void changeColor(Color newColor) {
        this.color = newColor;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public boolean checkIfClicked(Point p) {
        // size is divided by 2 because the algorithm checks the distance from the center of the circle
        int range = size / 2;
        Point trueCenter = new Point((int) center.getX() + range, (int) center.getY() + range);
        // calculating if the distance between the clicked point and the center is more or less than the radius
        int distance = (int) Math.sqrt(Math.pow(p.getX() - trueCenter.getX(), 2) + Math.pow(p.getY() - trueCenter.getY(), 2));
        return distance <= range;
    }

}

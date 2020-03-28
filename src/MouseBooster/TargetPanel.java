package MouseBooster;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class TargetPanel extends JPanel {
    private Point center;
    private int size;
    private Color color;
    private String shape;

    public TargetPanel(Point center, int size, Color color, String shape) {
        super();
        this.center = center;
        this.size = size;
        this.color = color;
        this.shape = shape.toUpperCase();
        setBackground(new Color(26, 26, 26));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xf = (int) center.getX();
        int yf = (int) center.getY();
        g.setColor(color);
        switch (shape) {
            case "SQUARE":
                g.fillRect(xf, yf, size, size);
                g.setColor(Color.BLACK);
                g.drawRect(xf, yf, size, size);
                break;
            case "CIRCLE":
                g.fillOval(xf, yf, size, size);
                g.setColor(Color.BLACK);
                g.drawOval(xf, yf, size, size);
                break;
        }
        repaint();
    }

    public void move(Point newCenter) {
        this.center = newCenter;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape.toUpperCase();
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

    public boolean isPuntoInterno(Point p) {
        int range = size / 2;
        Point centroReale = new Point((int) center.getX() + range, (int) center.getY() + range);
        // calcolo se la distanza tra punto cliccato e centro del cerchio e' minore o uguale alla variabile dimensione
        if (shape.equals("CIRCLE")) {
            int distance = (int) Math.sqrt(Math.pow(p.getX() - centroReale.getX(), 2) + Math.pow(p.getY() - centroReale.getY(), 2));
            if (distance <= range)
                return true;
            return false;
        } else {
            // se la distanza tra il valore assoluto dei due punti sull asse x && il valore assoluto dei due punti sull asse y e' < della variabile dimensione allora e' valido
            if ((Math.abs(centroReale.getX() - p.getX()) < range) && (Math.abs(centroReale.getY() - p.getY()) < range))
                return true;
            return false;
        }
    }

}

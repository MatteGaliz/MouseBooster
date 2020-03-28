
package MouseBooster;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class MyLabel extends JLabel {

    private double value;

    public MyLabel(double value, Color color) {
        this.value = value;
        this.setForeground(color);
        this.setFont(new Font("Calibri", Font.BOLD, 20));
        showLabel();
    }

    public void setValue(double value) {
        this.value = value;
        showLabel();
    }

    public double getValue() {
        return value;
    }

    public void increment(double value) {
        this.value++;
        showLabel();
    }

    public void decrement(double value) {
        this.value--;
        showLabel();
    }

    private void showLabel() {
        this.setText(String.valueOf(value));
    }

}

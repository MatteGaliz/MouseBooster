
package MouseBooster;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class MyLabel extends JLabel {

    private int value;

    public MyLabel(int value, Color color) {
        this.value = value;
        this.setForeground(color);
        this.setFont(new Font("Calibri", Font.BOLD, 20));
        showLabel();
    }

    public void setValue(int valore) {
        this.value = valore;
        showLabel();
    }

    public int getValue() {
        return value;
    }

    public void increment(int value) {
        this.value++;
        showLabel();
    }

    public void decrement(int value) {
        this.value--;
        showLabel();
    }

    private void showLabel() {
        this.setText(String.valueOf(value));
    }

}

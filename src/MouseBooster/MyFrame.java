
package MouseBooster;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Random;
import javax.swing.*;

class MyFrame extends JFrame {

    private Container primary;
    private TargetPanel targetPanel;
    private JPanel pNord;
    private JPanel pSouth;
    private JPanel pNordTop;
    private JPanel pNordBottom;

    private ButtonGroup radioMode;
    private JRadioButton radioEasy;
    private JRadioButton radioHard;
    private ButtonGroup radioSpeed;
    private JRadioButton radioSlow;
    private JRadioButton radioFast;

    private MyLabel lblHit;
    private MyLabel lblMiss;
    private MyLabel lblAccuracy;

    private JButton btnReset;
    private JButton btnClose;
    private JButton btnStart;

    private Color darkBG = new Color(26, 26, 26);
    private int targetCounter;

    Timer myTimer;
    Random rand = new Random();

    public MyFrame(String title) {
        super(title);
        setBounds(200, 50, 1280, 960);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        prepareControls();
        preparePanels();

        setVisible(true);
        targetPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                /*
                 TODO: IMPORTANTE, SE L OGGETTO VIENE COLPITO SE NE CREA UN ALTRO E SI RESETTA IL CLOCK DEL TIMER INTERNO
                */
                // verifica che il cerchio sia colpito
                Point clickedPoint = new Point(e.getX(), e.getY());
                System.out.println(clickedPoint);
                System.out.println(targetPanel.checkIfClicked(clickedPoint));
                if (targetPanel.checkIfClicked(clickedPoint)) {
                    lblHit.increment(1);
                } else {
                    lblMiss.increment(1);
                }
            }
        });
        btnClose.addActionListener((ActionEvent e) -> {
            System.exit(1);
        });
        btnReset.addActionListener((ActionEvent e) -> {
            lblHit.setValue(0);
            lblMiss.setValue(0);
            lblAccuracy.setValue(0);
        });
        btnStart.addActionListener((ActionEvent e) -> {
            if (myTimer.isRunning()) {
                myTimer.stop();
                btnStart.setText("Riprendi");
            } else {
                myTimer.restart();
                btnStart.setText("Pausa");
            }
        });
        myTimer = new Timer(500, new TimerAction());
    }

    private void preparePanels() {
        pNordTop = new JPanel();
        pNordTop.setBackground(darkBG);
        pNordTop.add(new JLabel("MODE: ")).setForeground(Color.WHITE);
        pNordTop.add(radioEasy);
        pNordTop.add(radioHard);
        pNordTop.add(new JLabel("  SPEED: ")).setForeground(Color.WHITE);
        pNordTop.add(radioSlow);
        pNordTop.add(radioFast);

        pNordBottom = new JPanel();
        pNordBottom.setBackground(darkBG);
        pNordBottom.add(new JLabel("SCORE  HIT:")).setForeground(Color.WHITE);
        pNordBottom.add(lblHit);
        pNordBottom.add(new JLabel("  MISS:")).setForeground(Color.WHITE);
        pNordBottom.add(lblMiss);
        pNordBottom.add(new JLabel("  ACCURACY:")).setForeground(Color.WHITE);
        pNordBottom.add(lblAccuracy);

        pNord = new JPanel(new GridLayout(2, 1));
        pNord.add(pNordTop);
        pNord.add(pNordBottom);

        // punto centrale
        targetPanel = new TargetPanel();

        pSouth = new JPanel();
        pSouth.setBackground(darkBG);
        pSouth.add(btnReset);
        pSouth.add(btnClose);
        pSouth.add(btnStart);

        primary = getContentPane();
        primary.add(pNord, BorderLayout.NORTH);
        primary.add(targetPanel, BorderLayout.CENTER);
        primary.add(pSouth, BorderLayout.SOUTH);
    }

    private void prepareControls() {

        /* RadioButton */
        /* Difficulty */
        radioMode = new ButtonGroup();
        radioEasy = new JRadioButton("Easy");
        radioEasy.setBackground(darkBG);
        radioEasy.setForeground(Color.WHITE);
        radioEasy.setSelected(true);
        radioMode.add(radioEasy);
        radioHard = new JRadioButton("Hard");
        radioHard.setBackground(darkBG);
        radioHard.setForeground(Color.WHITE);
        radioMode.add(radioHard);
        /* Speed */
        radioSpeed = new ButtonGroup();
        radioSlow = new JRadioButton("Slow");
        radioSlow.setBackground(darkBG);
        radioSlow.setForeground(Color.WHITE);
        radioSlow.setSelected(true);
        radioSpeed.add(radioSlow);
        radioFast = new JRadioButton("Fast");
        radioFast.setBackground(darkBG);
        radioFast.setForeground(Color.WHITE);
        radioSpeed.add(radioFast);

        /* Other buttons */
        btnReset = new JButton("Reset");
        btnClose = new JButton("Close");
        btnStart = new JButton("Start");

        /* custom hit/miss labels */
        lblHit = new MyLabel(0, Color.GREEN);
        lblMiss = new MyLabel(0, Color.RED);
        lblAccuracy = new MyLabel(0, Color.MAGENTA);
    }

    public class TimerAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (radioEasy.isSelected()) {
                targetPanel.changeColor(Color.RED);
                targetPanel.move(getRandomCenter());
                targetPanel.setTargetSize(120);
            } else {
                targetPanel.changeColor(getRandomColor());
                targetPanel.move(getRandomCenter());
                targetPanel.setTargetSize(60);
            }
            targetCounter++;
            lblAccuracy.setValue((lblHit.getValue() / targetCounter) * 100);
            targetPanel.repaint();
        }
    }

    private Color getRandomColor() {
        Random gen = new Random();
        int alpha[] = {0, 255};
        short r = (short) gen.nextInt(2);
        short g = (short) gen.nextInt(2);
        short b = (short) gen.nextInt(2);
        return new Color(alpha[r], alpha[g], alpha[b]);
    }

    private Point getRandomCenter() {
        Random gen = new Random();
        if (radioEasy.isSelected()) {
            int x = gen.nextInt(targetPanel.getWidth() - 120);
            int y = gen.nextInt(targetPanel.getHeight() - 120);
            return new Point(x, y);
        } else {
            int x = gen.nextInt(targetPanel.getWidth() - 60);
            int y = gen.nextInt(targetPanel.getHeight() - 60);
            return new Point(x, y);
        }
    }
}


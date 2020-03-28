
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

    private ButtonGroup buttonGroup;
    private JRadioButton radioEasy;
    private JRadioButton radioHard;

    private MyLabel lblHit;
    private MyLabel lblMiss;
    private MyLabel lblAccuracy;

    private JButton btnReset;
    private JButton btnClose;
    private JButton btnStart;

    private Color darkBG = new Color(26, 26, 26);

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
                // controllare se e' stato colpito qualcosa
                // uso e.getX & e.getY perche' e.getPositionOnScreen ritorna la posizione assoluta sullo schermo del puntatore
                // mostrare una nuova forma con cambio forma e colore
                // TODO: IMPORTANTE, SE L OGGETTO VIENE COLPITO SE NE CREA UN ALTRO E SI RESETTA IL CLOCK DEL TIMER INTERNO
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
            // start targets generation
            myTimer.restart();
        });
        myTimer = new Timer(1000, new TimerAction());
    }

    private void preparePanels() {
        Random rand = new Random();
        pNordTop = new JPanel();
        pNordTop.setBackground(darkBG);
        pNordTop.add(new JLabel("MODE: ")).setForeground(Color.WHITE);
        pNordTop.add(radioEasy);
        pNordTop.add(radioHard);

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
        int xf = rand.nextInt(100);
        int yf = rand.nextInt(100);
        targetPanel = new TargetPanel(new Point(xf, yf), 120, getRandomColor(), "cricle");

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
        buttonGroup = new ButtonGroup();
        radioEasy = new JRadioButton("Easy");
        radioEasy.setBackground(darkBG);
        radioEasy.setForeground(Color.WHITE);
        buttonGroup.add(radioEasy);
        radioHard = new JRadioButton("Hard");
        radioHard.setBackground(darkBG);
        radioHard.setForeground(Color.WHITE);
        buttonGroup.add(radioHard);

        /* Other buttons */
        btnReset = new JButton("Reset");
        btnClose = new JButton("Close");
        btnStart = new JButton("Start");
        /*
         * custom hit/miss labels
         */
        lblHit = new MyLabel(0, Color.GREEN);
        lblMiss = new MyLabel(0, Color.RED);
        lblAccuracy = new MyLabel(0, Color.MAGENTA);
    }

    public class TimerAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            targetPanel.setShape("CIRCLE");
            targetPanel.changeColor(getRandomColor());
            targetPanel.move(getRandomCenter());
            targetPanel.setTargetSize(120);
            targetPanel.repaint();
        }}

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
        int x = gen.nextInt(targetPanel.getWidth());
        int y = gen.nextInt(targetPanel.getHeight());
        return new Point(x, y);
    }
}


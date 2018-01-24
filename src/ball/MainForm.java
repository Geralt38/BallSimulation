package ball;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class MainForm {
    private static JFrame frame;
    private JPanel panel1;
    private JTextField radiusField;
    private JTextField xField;
    private JTextField yField;
    private JTextField dxField;
    private JTextField dyField;
    private JTextField kField;
    private JTextField timeField;
    private JTextField dtField;
    private JTextArea logArea;
    private JButton simulateButton;


    public MainForm() {
        simulateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                simulate();
            }
        });
    }

    public void simulate() {

        BufferedImage ball = null;
        BufferedImage background = null;
        try {
            ball = ImageIO.read(new File("src\\ball\\ball.png"));
            background = ImageIO.read(new File("src\\ball\\background.png"));
        } catch (IOException ex) {
            System.out.println("fuck me");
        }

        Graphics g = panel1.getGraphics();

        g.drawImage(ball, 600, 100, 10, 10, null);



        logArea.setText("");

        float radius = Float.parseFloat(radiusField.getText());
        float x = Float.parseFloat(xField.getText());
        float y = Float.parseFloat(yField.getText());
        float dx = Float.parseFloat(dxField.getText());
        float dy = Float.parseFloat(dyField.getText());
        float time = Float.parseFloat(timeField.getText()) * 1000;
        float dt = Float.parseFloat(dtField.getText()) * 1000;
        float k = Float.parseFloat(kField.getText());


        float leftBorder = 0;
        float rightBorder = 10;

        float dtActual = 20;
        float multiplier = dtActual/1000;

        float timeTillNextLog = 0;
        int counter = 1;

        int pixelsPerMeter = 50;

        for (int i = 0; i <= time; i+=dtActual) {

            g.drawImage(background, 420, 100, 500, 500, null);
            g.drawImage(ball, (int) (420 + (x-radius)*pixelsPerMeter), (int) (600 - (y + radius) * pixelsPerMeter), (int) (radius*2*pixelsPerMeter), (int) (radius*2*pixelsPerMeter), null);

            if(timeTillNextLog < 1) {
                timeTillNextLog = dt;
                logArea.append(String.format("t%d: height = %.2f , speed = %.2f \n",counter,y-radius,Math.sqrt(dx*dx + dy*dy)));
                counter++;
            }
            timeTillNextLog -= dtActual;

            dy -= 9.8*multiplier;

            x += dx*multiplier;
            y += dy*multiplier;

            if (y - radius < 0) {
                y = radius;
                dy *= -k;
                if (Math.abs(dy) < 0.01) {
                    dy = 0;
                }
            }

            if (x - radius < leftBorder) {
                x = radius;
                dx *= -k;
            }

            if (x + radius > rightBorder) {
                x = rightBorder - radius;
                dx *= -k;
            }

            try {
                Thread.sleep((long)dtActual);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        frame = new JFrame("Ball Simulation");

        frame.setContentPane(new MainForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);



    }
}

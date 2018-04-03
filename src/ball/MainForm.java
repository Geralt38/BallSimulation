package ball;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

                Simulation simulation = new Simulation(logArea, panel1.getGraphics(), Float.parseFloat(radiusField.getText()),
                        Float.parseFloat(xField.getText()), Float.parseFloat(yField.getText()), Float.parseFloat(dxField.getText()), Float.parseFloat(dyField.getText()),
                        Float.parseFloat(timeField.getText()) * 1000, Float.parseFloat(dtField.getText()) * 1000, Float.parseFloat(kField.getText()));
                simulation.start();

            }
        });
    }



    public static void main(String[] args) {
        frame = new JFrame("Ball Simulation");

        frame.setContentPane(new MainForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);



    }
}

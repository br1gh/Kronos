import javax.swing.*;


public class Main {

    public static void main(String [] args)
    {
        JFrame frame = new JFrame("JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JButton button = new JButton("Button");
        frame.getContentPane().add(button);
        button.addActionListener(new ButtonPress());

        frame.setVisible(true);
    }
}

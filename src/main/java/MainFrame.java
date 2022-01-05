import javax.swing.*;


public class MainFrame {

    public static void show()
    {
        JFrame frame = new JFrame("Kronos");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Button");
        frame.getContentPane().add(button);
        button.addActionListener(new ButtonPress());

        frame.setVisible(true);
    }

}

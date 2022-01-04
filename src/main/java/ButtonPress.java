import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonPress implements ActionListener {

    public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null, "Test");
    }

}

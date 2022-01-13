import javax.swing.*;
import java.awt.*;


public class MainFrameButton extends JButton
{
    public MainFrameButton(String text, Color color)
    {
        super(text);
        this.setBackground(color);
        this.setForeground(new Color(168,182,191));
        this.setFont(new Font("Dialog",Font.BOLD,14));
    }
}

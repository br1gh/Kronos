import javax.swing.*;
import java.awt.*;


public class MainFrameComboBox extends JComboBox
{
    public MainFrameComboBox(Object[] items)
    {
        super(items);
        this.setBackground(new Color(43, 43, 43));
        this.setForeground(new Color(168,182,191));
    }
}

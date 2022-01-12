import javax.swing.*;
import java.awt.*;

public class MainFrameText extends JLabel{
    public MainFrameText(String text) {
        super(text);
        this.setForeground(new Color(168,182,191));
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
